package com.zhangyfvir.jarticle.userservice.components;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;

/**
 * 密码工具类
 * 密码由3部分组成，即： option，salt，hexString，之间用分隔符隔开
 * 加密时：参数1：明文密码字符串；参数2：可指定一种 option，或者随机一个option。
 * 加密后的输出：包含了加密方法描述和密码串的字符串。
 * 验证密码时：传入 待检验的明文字符串，和已存储的完整密码串。
 * 验证密码处理：它从"已存储的完整密码串"中读取到 加密方法描述，用同样的方式再次处理加密得到加密串，再最后对比。
 **/
public class PasswordUtils {
    private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
    private static final String SEPARATOR = "::";
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final Map<String, BinaryOperator<String>> supportedHashFunctions = new ConcurrentHashMap<>();

    private static final String PBKDF2 = "0";
    private static final BinaryOperator<String> pbkdf2Function = (password, salt) -> {
        int iteration = 65536;
        int strength = 128;
        String algorithm = "PBKDF2WithHmacSHA1";
        KeySpec spec = new PBEKeySpec(password.toCharArray(), hexStringToBytes(salt), iteration, strength);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return bytesToHexString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    };

    static {
        // add more hash functions if necessary
        supportedHashFunctions.put(PBKDF2, pbkdf2Function);
    }

    private PasswordUtils() {
    }

    /**
     * 根据密码明文使用随机加盐生成密码密文
     *
     * @param plainPassword 用户密码明文
     * @param hashOption    哈希函数选项，可不填
     * @return 随机加盐后的密码密文
     */
    public static String hashPassword(String plainPassword, String... hashOption) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String option;
        if (hashOption != null && hashOption.length > 0 && StringUtils.isNotBlank(hashOption[0])) {
            // user specified hash function
            option = hashOption[0];
        } else {
            // pick a random hash function
            option = String.valueOf(ThreadLocalRandom.current().nextInt(supportedHashFunctions.size()));
        }
        if (!supportedHashFunctions.containsKey(option)) {
            throw new IllegalArgumentException("there is no such hash option: " + option);
        }
        return option + SEPARATOR + bytesToHexString(salt) + SEPARATOR + supportedHashFunctions.get(option)
                .apply(plainPassword, bytesToHexString(salt));
    }

    /**
     * 校验用户输入的明文密码是否正确
     *
     * @param plainPassword 用户输入的明文密码
     * @param dbPassword    来自数据库的密码密文
     * @return 密码校验是否通过
     */
    public static boolean validatePassword(String plainPassword, String dbPassword) {
        String[] optionSaltAndPass = StringUtils.split(dbPassword, SEPARATOR);
        if (optionSaltAndPass == null || optionSaltAndPass.length != 3) {
            throw new IllegalStateException("split db password array should be of length 3");
        }
        String option = optionSaltAndPass[0];
        if (!supportedHashFunctions.containsKey(option)) {
            throw new IllegalStateException("hash function not found by option: " + option);
        }
        String salt = optionSaltAndPass[1];
        String encryptedPassword = optionSaltAndPass[2];
        return StringUtils.equals(supportedHashFunctions.get(option).apply(plainPassword, salt), encryptedPassword);

    }

    private static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}



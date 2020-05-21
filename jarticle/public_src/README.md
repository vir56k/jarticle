# 当前文件夹是一些公共类库的文件夹

# 目标
可以将 本文件下的源代码 附加到你的项目中。

# 使用方法：

修改 POM.xml ，将下面的代码置入。
主要修改 build - plugins - plugin，在这个下面加入了一个插件，这个插件负责完成”增加源代码目录 ” 的功能。

```
<build>
       <plugins>
           <plugin>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-maven-plugin</artifactId>
           </plugin>
           <plugin>
               <groupId>org.codehaus.mojo</groupId>
               <artifactId>build-helper-maven-plugin</artifactId>
               <version>1.7</version>
               <executions>
                   <execution>
                       <id>add-source</id>
                       <phase>generate-sources</phase>
                       <goals>
                           <goal>add-source</goal>
                       </goals>
                       <configuration>
                           <sources>
                               <!-- 我们可以通过在这里添加多个source节点，来添加任意多个源文件夹 -->
                               <source>${basedir}/../public_src/base_class</source>
                               <source>${basedir}/../public_src/common_for_service</source>
                           </sources>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
       </plugins>

   </build>
```

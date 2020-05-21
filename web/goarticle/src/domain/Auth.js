
import HTTP from '../common/HttpUtil'

const URL_LOGIN = "/user/auth/login";

class Service {

  login(username, password){
    return HTTP.post(HTTP.getHostURL(URL_LOGIN),{username, password});
  }

}

let service = new Service();

const Auth = {
  isAuthenticated(){
    let token = global.storage.getItem("Authorization");
    return ! ( !token || token === '' || token === null);
  },
  authenticate(username,password,cb){
    if(!username || username==='' || !password || password===''){
      cb(undefined,new Error("请输入账户密码"))
      return;
    }
    service.login(username,password).then((res)=>{
      let { code,data,message } = res.data;
      if(code === 200){
        console.log("## login code="+code);
        let { token } = data;
        global.storage.setItem("Authorization",''+token);
          setTimeout(()=>{
            cb(token,undefined);
          }, 100);
      }else{
        cb(undefined,''+message);
      }
    }).catch((err)=>{
      console.log("## login err="+err);
      cb(undefined,''+err);
    });
  },
  signout(cb){
    global.storage.removeItem('Authorization');
    setTimeout(cb, 100);
  }
};

export default Auth;

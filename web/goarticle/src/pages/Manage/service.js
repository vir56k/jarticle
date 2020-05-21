import HTTP from '../../common/HttpUtil'

//获得文章信息，文章内容是 原始字符
const URL_ARTICLE_ORIGIN = "/article/protected/articles/{id}";
const URL_ARTICLE_NAMES = "/article/protected/articleNameList";
const URL_ARTICLE_SAVE = "/article/protected/articles";


var service = {


  articleNameList: ()=>{
    // if(!appName)
    //   throw "缺少必须的参数appName";
    return HTTP.get(HTTP.getHostURL(URL_ARTICLE_NAMES),{  });
  },

  saveArticle: ({id,title,body})=>{
    let url = URL_ARTICLE_SAVE;
    return HTTP.post(HTTP.getHostURL(url),{id,title,body});
  },

  getArticleOrigin: (title)=>{
    let url = URL_ARTICLE_ORIGIN.replace("{id}",""+title);
    return HTTP.get(HTTP.getHostURL(url),{  });
  },
}

export default service;

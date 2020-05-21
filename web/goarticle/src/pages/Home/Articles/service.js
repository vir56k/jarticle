import HTTP from '../../../common/HttpUtil'

const URL_ARTICLE_LIST = "/article/public/articles";
// 获得文章信息，文章内容是html
const URL_ARTICLE_GET = "/article/public/articles/{id}";
const URL_ARTICLE_NAMES = "/article/public/articleNameList";


var service = {

  articleList: ()=>{
    // if(!appName)
    //   throw "缺少必须的参数appName";
    return HTTP.get(HTTP.getHostURL(URL_ARTICLE_LIST),{  });
  },

  articleNameList: ()=>{
    // if(!appName)
    //   throw "缺少必须的参数appName";
    return HTTP.get(HTTP.getHostURL(URL_ARTICLE_NAMES),{  });
  },

  getArticle: (title)=>{
    let url = URL_ARTICLE_GET.replace("{id}",""+title)
    return HTTP.get(HTTP.getHostURL(url),{  });
  },

}

export default service;

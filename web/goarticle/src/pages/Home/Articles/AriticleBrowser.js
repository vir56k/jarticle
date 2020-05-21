import React from 'react';
import './AriticleBrowser.css';
import { message } from 'antd';
import service from './service.js';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useParams,
} from "react-router-dom";
import { useState,  useEffect } from 'react';
import ArticleView from './ArticleView.js';

export default function Page() {
  return (
    <Router>
      <div class="ariticleBrowser">
        <div class="left">
          <Left/>
        </div>
        <div class="right">
          <Switch>
            <Route path="/browser/:Title" children={<Child />} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

function Left(){
  const [list, setList] = useState([]);

  useEffect(() => {
    service.articleNameList().then((response)=>{
        let { code,message,data } = response.data;
        if(code !== 200){
          message.error(message);
          return;
        }
        setList(data)
    }).catch((err)=>{
        message.error(err+'');
      }
    );
 },[]);

  return (
    <div>
      <TheList datalist={list} />
    </div>
  )
}

function TheList({datalist}){
  const theItems = datalist.map((item,i)=>{
    let { id,title,body,Url } = item;
    Url = "/browser/"+title;
    return(
      <li key={id}>
        <Link to={Url}>
            {title}
        </Link>
      </li>
    );
  });
  return (
    <ul>{theItems}</ul>
  );
}

function Child() {
  // We can use the `useParams` hook here to access
  // the dynamic pieces of the URL.
  let { Title } = useParams();

  return (
    <div>
      <ArticleView Title={Title}/>
    </div>
  );
}

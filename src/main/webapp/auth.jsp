<%--
  Created by IntelliJ IDEA.
  User: 28906
  Date: 2017/12/12
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <title>授权</title>
</head>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    li{
        list-style-type: none;
    }
    div{
        box-sizing: border-box;
    }
    button,input{
        outline: none;
    }
    .authorize-box{
        border: 1px solid #eaeaea;
        width: 30%;
        padding: 10px;
        margin: 20px auto;
        border-radius: 5px;
    }
    .authorize-box >div{
        margin-bottom: 10px;
    }
    .authorize-content{
        margin-left: 10px;
    }
    .authorize-content > p{
        margin-bottom: 5px;
    }
    .detail-authorize{
        margin-bottom: 5px;
    }
    .detail-authorize ul{
        margin-left: 10px;
    }
    .detail-authorize  li{
        margin-bottom: 5px;
    }
    .detail-authorize span{
        margin-left: 5px;
    }
    .go-authorize button{
        border: none;
        color: #fff;
        width: 100%;
        margin-bottom: 10px;
        text-align: center;
        padding: 5px 0;
        font-size: 14px;
        border-radius: 7px;

    }
    .go-authorize .sure{
        background-color: #06C003;
    }
    .go-authorize .cancel{
        background-color: #D9534F;
    }
    /*适配手机*/
    @media only screen and (max-width: 768px) {
        .authorize-box{
            width: 100%;
            margin: 0 auto;
        }
    }
</style>
<body>
<div class="authorize-box">
    <div class="user-name">
        <span>Dear All:</span>
    </div>
    <div class="authorize-content">
        <p>允许访问的数据 :</p>
        <div class="detail-authorize">
            <ul>
                <li><input type="checkbox" checked /><span>身份证号码</span></li>
                <li><input type="checkbox" checked /><span>手机号</span></li>
                <li><input type="checkbox" checked /><span>住址</span></li>
            </ul>
        </div>
    </div>
    <div class="tip"><p>是否授权 :</p></div>
    <div class="go-authorize">
        <button class="sure">确定</button>
        <button class="cancel">取消</button>
    </div>
</div>
</body>
</html>

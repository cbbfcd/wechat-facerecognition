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
    <title>登录</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.2.1.js"></script>
</head>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    li{
        list-style-type: none;
    }
    div,input{
        box-sizing: border-box;
    }
    button,input{
        outline: none;
    }
    .login-box{
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        overflow: auto;
        padding: 10px;
    }
    .login-box > div{
        margin-bottom: 10px;
        text-align: center;
    }
    .user-name,
    .user-id{
        text-align: left !important;
        height: 32px;
        border-radius: 16px;
        border: 1px solid #EAEAEA;
    }
    .user-name{
        background: url(${pageContext.request.contextPath}/user.png) no-repeat;
        background-size: 20px 20px;
        background-position: 20px 20%;
    }
    .user-id{
        background: url(${pageContext.request.contextPath}/idcard.png) no-repeat;
        background-size: 25px 20px;
        background-position: 20px 50%;
    }
    .login-box > div > input{
        border: none;
        height: 30px;
        padding-left: 5px;
        width: calc(100% - 70px);
        margin-left: 45px;
        color: #06C003;
    }
    .id-view{
        border: 1px solid #EAEAEA;
        padding: 10px 0;
    }
    .id-view p{
        margin-bottom: 10px;
    }
    .id-view #id-face,
    .id-view #id-conface{
        height: 100px;
    }
    .live-view{
        height: 200px;
        border: 1px solid #D9534F;
    }
    .register button{
        border: 1px solid rgba(6,192,3,.5);
        width: 100%;
        padding: 8px 5px;
        background: rgba(6,192,3,.5);
        color: #EAEAEA;
        border-radius: 5px;
    }
    .action-reg{
        background: #06C003 !important;
    }
    .bottom-box{
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background-color: rgba(0,0,0,.3);
        display: none;
    }
    .bottom-camera{
        position: absolute;
        bottom: 0;
        left: 10%;
        width: 80%;
        background: #fff;
        border-radius: 5px 5px 0 0;
        text-align: center;
        padding: 5px;
    }
    .bottom-camera li{
        height: 30px;
        line-height: 30px;
        margin: 10px 0;
        border-bottom: 1px #EAEAEA solid;
    }
    .bottom-camera li:last-child{
        border: none;
        margin-bottom: 0;
    }
    #id-face{
        position: relative;
    }
    .showCrb{
        position: absolute;
        left: 50%;
        top: 50%;
        -webkit-transform: translate(-50%,-50%);
        -moz-transform: translate(-50%,-50%);
        -ms-transform: translate(-50%,-50%);
        transform: translate(-50%,-50%);
    }
    @keyframes showBox{
        from{
            height: 0;
        }
        to{
            height: 91px;
        }
    }
    @keyframes hideBox{
        from{
            height: 91px;
        }
        to{
            height: 0;
        }
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #06C003; opacity:1;
    }

    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #06C003;opacity:1;
    }

    input:-ms-input-placeholder{
        color: #06C003;opacity:1;
    }

    input::-webkit-input-placeholder{
        color: #06C003;opacity:1;
    }
</style>
<body>
<div class="login-box">
    <div class="user-name"><input placeholder="姓名"/></div>
    <div class="user-id"><input placeholder="身份证"/></div>
    <div class="id-view" id="id-view">
        <p>请上传身份证正面照</p>
        <div>
            <div id="id-face"><img class="showCrb" src="${pageContext.request.contextPath}/add.png" /></div>
        </div>
    </div>
    <div class="live-view"></div>
    <div class="register">
        <button class="action-reg">注册</button>
    </div>
</div>
<div class="bottom-box">
    <div class="bottom-camera">
        <ul>
            <li id="uploadLocal">照片上传</li>
            <li id="cancelBox">取消</li>
        </ul>
    </div>
</div>
<script>
    window.onload = function(){
        jsSDK();
        //显示弹窗
        function showCemeraBox(){
            document.querySelector(".bottom-box").style.display = "block";
            document.querySelector(".bottom-camera").style.animation = "showBox ease .5s";
        }
        //隐藏弹窗
        function hideCemeraBox(e){
            e.stopPropagation();
            document.querySelector(".bottom-camera").style.animation = "hideBox ease .5s";
            var timer = setTimeout(function(){
                document.querySelector(".bottom-box").style.display = "none";
            },500)
        }
        //本地上传
        function uploadLocalFun(e){
            e.stopPropagation();
            //CODE
        }
        //添加蒙层node
        var showBox = document.querySelector(".showCrb");
        //取消蒙层node
        var hideBox = document.querySelector("#cancelBox");
        //本地上传node
        var uploadLocal = document.querySelector("#uploadLocal");
        //蒙层node
        var model = document.querySelector(".bottom-box");
        //UI动画效果实现
        showBox.onclick = showCemeraBox;
        hideBox.onclick = hideCemeraBox;
        model.onclick = hideCemeraBox
        //具体操作实现
        uploadLocal.onclick = uploadLocalFun;
    };
    /**
     * jssdk
     */
    function jsSDK() {
        $.ajax({
            url: "wechat/jssdk",
            type: "post",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                url: location.href.split("#")[0]
            },
            success: function (data) {
                wx.config({
                    debug: false,
                    appId: data.data.appId,
                    timestamp: data.data.timestamp,
                    nonceStr: data.data.nonceStr,
                    signature: data.data.signature,
                    jsApiList: [
                        'chooseImage', 'previewImage', 'uploadImage', 'downloadImage'
                    ]
                });
                
                wx.ready(function () {
                    console.log('weixin is ready');

                    // 上传图片
                    var images = {
                        localId: [],
                        serverId: []
                    };

                    // 本地上传
                    $("#uploadLocal").click(function(){
                        wx.chooseImage({
                            count: 1,
                            sizeType: ['original'],
                            success: function (res) {
                                if(res.localIds.length === 1){
                                    uploadImg(res);
                                }else{
                                    alert('只能上传一张照片');
                                    return;
                                }
                            }
                        });
                    });
                    // 上传图片
                    function uploadImg(res) {
                        images.localId = res.localIds;
                        images.serverId = [];
                        function uploads() {
                            wx.uploadImage({
                                localId: res.localIds[0],
                                isShowProcess: 1,
                                success: function(res){
                                    images.serverId.push(res.serverId);
                                    $.ajax({
                                        type: 'POST',
                                        url: 'http://1895r385n5.imwork.net/wechat/upload',
                                        data: {
                                            serverId: res.serverId
                                        },
                                        dataType: 'text',
                                        success: function(data){
                                            alert("上传成功");
                                            document.querySelector(".bottom-box").style.display = "none";
                                        },
                                        error: function(err){
                                            console.log(err);
                                        }
                                    })
                                },
                                fail: function(err){
                                    console.log(JSON.stringify(err));
                                }
                            })
                        }

                        uploads();
                    }

                });

                wx.error(function (err) {
                    alert('wx-err: '+ JSON.stringify(err));
                });
            }
        })
    }


    /**
     * 判断微信版本
     */
    function isWeiXin5(){
        var wnu =  window.navigator.userAgent.toLowerCase();
        var reg = /MicroMessenger\/[5-9]/i;
        return reg.test(wnu);
    }
    
//    window.onload = function () {
////        if(isWeiXin5() == false){
////            alert("微信版本过低！")
////        }
//        jsSDK();
//    }
</script>
</body>
</html>



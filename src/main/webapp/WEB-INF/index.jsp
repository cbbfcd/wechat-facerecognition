<%--
  Created by IntelliJ IDEA.
  User: 28906
  Date: 2017/11/20
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试截图</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts-all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/html2canvas.js"></script>
    <style>
        .cap{
            padding: 10px 20px;
            border-radius: 4px;
            border: 1px solid #e8e8e8;
            color: steelblue;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div>
        <h1>测试截图</h1>
        <div id="root">
            <div id="echarts" style="height: 400px">

            </div>
            <h1>测试文字</h1>
            <h1>测试文字</h1>
            <h1>测试文字</h1>
        </div>
    </div>
    <div>
        <a class="cap" onclick="screenshot()">截图</a>
        <div id="new" style="height: 500px"></div>
    </div>
    <script>
        (function(){
            var myBar = echarts.init(document.querySelector('#echarts'))
            var option = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['销量']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"销量",
                        "type":"bar",
                        "data":[5, 20, 40, 10, 10, 20]
                    }
                ]
            };
            myBar.setOption(option);
        })();

        // 截图
        function  screenshot() {
            html2canvas(document.querySelector('#root'),{
                allowTaint: true,
                taintTestL: false,
                onrendered: function(canvas){
                    canvas.id= 'mycanvas';
                    var dataUrl = canvas.toDataURL();
                    var newImg = document.createElement("img");
                    newImg.src =  dataUrl;
                    document.querySelector('#new').appendChild(newImg);
                }
            })
        }
    </script>
</body>
</html>

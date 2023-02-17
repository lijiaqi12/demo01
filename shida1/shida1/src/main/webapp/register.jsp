<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  
    <!-- Custom Theme files -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/snow.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#uname").blur(function () {
                //获取页面传过来的用户名
                  var username=  $(this).val()
                  var userspan= $("#userspan")
                //把用户名传到后台，在后台的数据库中去查找该用户是否存在
               $.get("/ajaxUser",{username:username},function (data) {
                    if(data.userExit){
                        //代码走到这里 说明存在
                        userspan.css("color","red")
                        userspan.html("该用户已经存在")
                    }else{
                        //代码走到这里 说明用户不存在
                        userspan.css("color","green")
                        userspan.html("该用户可以使用")
                    }
               })
            })
        })
    </script>
</head>
<body>
    <div class="snow-container">
        <div class="snow foreground"></div>
        <div class="snow foreground layered"></div>
        <div class="snow middleground"></div>
        <div class="snow middleground layered"></div>
        <div class="snow background"></div>
        <div class="snow background layered"></div>
    </div>

    <div class="top-buttons-agileinfo">
        <a href="login.jsp">登录</a><a href="register.jsp" class="active">注册</a>
    </div>
    <h1>人力资源系统</h1>
    <div class="main-agileits">
        <!--form-stars-here-->
        <div class="form-w3-agile">
            <h2 class="sub-agileits-w3layouts">注册</h2>
            <form action="userRegister" method="post">
                <input type="text" name="username" placeholder="Username" required="" id="uname"/>
                <span id="userspan"></span>
                <input type="password" name="password" placeholder="Password" required="" id="passwd"/>
                <input type="text" name="email" placeholder="Email" required="" id="emails"/>
                <div class="submit-w3l">
                    <input type="submit" value="注册">
                </div>
            </form>
        </div>
    </div>
    <!--//form-ends-here-->
    <!-- copyright -->
    <div class="copyright w3-agile">
        <p> © 2019 带下雪背景的登录注册页面 . All rights reserved | Design by <a href="http://www.bootstrapmb.com/" title="bootstrapmb">Reserved</a></p>
    </div>
    <!-- //copyright -->


</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>密码找回页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  
    <!-- Custom Theme files -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/snow.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
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
    <h1>人力资源检索系统</h1>
    <div class="main-agileits">
        <!--form-stars-here-->
        <div class="form-w3-agile">
            <h2 class="sub-agileits-w3layouts">密码找回</h2>
            <form action="findByEmail" method="post">
                <input type="email" name="email" placeholder="Email" required="" id="emails"/>
                <span id="email_span"></span>
                <div class="submit-w3l">
                    <input type="submit" value="提交">
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
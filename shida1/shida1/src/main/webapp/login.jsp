<!DOCTYPE html>
<html>
<head>
<title>登录页面 </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<!-- Custom Theme files -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
<link href="css/snow.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->

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
<a href="login.jsp" class="active">登录</a><a href="register.jsp">注册</a>
</div>
<h1>我的网盘</h1>
<div class="main-agileits">
<!--form-stars-here-->
		<div class="form-w3-agile">
			<h2 class="sub-agileits-w3layouts">登录</h2>
			<form action="userlogin" method="post">
					<input type="text" name="username" placeholder="Username" required="" />
					<input type="password" name="password" placeholder="Password" required="" />
					<a href="findEmail" class="forgot-w3layouts" target="_self">忘记密码 ?</a>
				<div class="submit-w3l">
                    <input type="submit" value="登录">
				</div>
				<p class="p-bottom-w3ls"><a href="register.jsp">这里注册</a>如果你还没有一个账号.</p>
			</form>
		</div>
		</div>
<!--//form-ends-here-->
<!-- copyright -->
    <div class="copyright w3-agile">
        <p> © 2019 带下雪背景的登录注册页面 . All rights reserved | Design by <a href="http://www.bootstrapmb.com/" title="bootstrapmb">Reserved</a></p>
    </div>
	<!-- //copyright --> 
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>注册/登录</title>
    <link rel="stylesheet" href="css/L_S_style.css">
</head>

<body>
    <div class="content">
        <div class="form sign-in">
            <h2>欢迎回来</h2>
            <label>
                <span>账户</span>
                <input type="text" class="account" required/>
            </label>
            <label>
                <span>密码</span>
                <input type="password" maxlength="16" class="password" required/>
            </label>
            <label>
				<span>验证码</span>
				<input type="text" name="verification" id="vericode" required>
				<img title="看不清，点击换一张" src="CodeServlet" onclick="this.src = this.src+'?';">
            </label>
            <p class="forgot-pass"></p>
            <button type="button" class="submit">登 录</button>
        </div>
        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                    <h2>还未注册？</h2>
                    <p>立即注册，发现大量鲜美！</p>
                </div>
                <div class="img__text m--in">
                    <h2>已有帐号？</h2>
                    <p>有帐号就登录吧，好久不见了！</p>
                </div>
                <div class="img__btn">
                    <span class="m--up">注 册</span>
                    <span class="m--in">登 录</span>
                </div>
            </div>
            <div class="form sign-up">
                <h2>立即注册</h2>
                <label>
                    <span>账户</span>
                    <input type="text" class="account"/>
                </label>
                <label>
                    <span>密码</span>
                    <input type="password" maxlength="16" class="password"/>
                </label>
                <p class="forgot-pass"></p>
                <button type="button" class="submit">注 册</button>
            </div>
        </div>
    </div>
</body>
<script>
    document.querySelector('.img__btn').addEventListener('click', function () {
        document.querySelector('.content').classList.toggle('s--signup')
    })
    //0登录 1注册
    var submit = document.getElementsByClassName("submit");
    var account = document.getElementsByClassName("account");
    var password = document.getElementsByClassName("password");
    var vericode= document.getElementById("vericode");
    var xmlHttp;
    var info = document.getElementsByClassName("forgot-pass");
    function createXMLHttpRequest() {
    	if(window.ActiveXObject) {//如果是IE
    		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    	} else if(window.XMLHttpRequest) {//非IE
    		xmlHttp = new XMLHttpRequest();
    	}
    }
    function sendLoginRequest() {
    	if(account[0].value!=""&&password[0].value!=""&&vericode.value!=""){
    		createXMLHttpRequest();
    		var url = "LoginServlet?current="+(new Date().getTime())+"&account="+account[0].value
    				+"&password="+password[0].value+"&vericode="+vericode.value;
    		xmlHttp.open("GET", url, true);//建立连接 默认异步处理
    		xmlHttp.onreadystatechange = getResults;//状态改变事件
    		xmlHttp.send();//发起请求
    	}else{
    		info[0].innerHTML = "表单项不能为空";
    	}
    }
    function getResults() {
    	if (xmlHttp.readyState == 4) {
    		if(xmlHttp.status == 200) {
    			if (xmlHttp.responseText == "vericodeError") {
					info[0].innerHTML = "验证码不正确！";
					vericode.value = "";
				} else if(xmlHttp.responseText == "warning") {
					info[0].innerHTML = "用户名或密码不正确";
				} else {
					window.location.href = "/ShoppingMarket/index.jsp";
				}
    		}
    	}
    }
    function sendSignupRequest() {
    	if(account[1].value!=""&&password[1].value!=""){
    		createXMLHttpRequest();
    		var url = "SignupServlet?current="+(new Date().getTime())+"&account="+account[1].value+"&password="+password[1].value;
    		xmlHttp.open("GET", url, true);//建立连接 默认异步处理
    		xmlHttp.onreadystatechange = getSResults;//状态改变事件
    		xmlHttp.send();//发起请求
    	}else{
    		info[1].innerHTML = "表单项不能为空";
    	}
    }
    function getSResults() {
    	if (xmlHttp.readyState == 4) {
    		if(xmlHttp.status == 200) {
				if(xmlHttp.responseText == "warning") {
					info[1].innerHTML = "当前用户名已存在";
				} else {
					window.location.href = "/ShoppingMarket/index.jsp";
				}
    		}
    	}
    }
    function sendCheckRequest() {
    	createXMLHttpRequest();
    	var url = "CheckUserServlet?current="+(new Date().getTime())+"&account="+account[1].value;
    	xmlHttp.open("GET", url, true);//建立连接 默认异步处理
    	xmlHttp.onreadystatechange = function () {
    		if (xmlHttp.readyState == 4) {
        		if(xmlHttp.status == 200) {
    				info[1].innerHTML = xmlHttp.responseText;
        		}
        	}
		};//状态改变事件
    	xmlHttp.send();//发起请求
    }
    submit[0].onclick = sendLoginRequest;
    account[1].onblur = sendCheckRequest;
    submit[1].onclick = sendSignupRequest;
</script>

</html>
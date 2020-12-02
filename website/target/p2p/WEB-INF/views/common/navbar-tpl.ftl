<div class="navbar navbar-default el-navbar">
	<div class="container">
		<div class="navbar-header">
			<a href="/"> <img alt="Brand" src="/images/logo.png">
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li id="index"><a href="/index.do">首页</a></li>
			<li id="invest"><a href="/invest.do">我要投资</a></li>
			<li id="borrow"><a href="/borrow.do">我要借款</a></li>
			<li id="personal"><a href="/personal.do">个人中心</a></li>
			<li><a href="#">新手指引</a></li>
			<li><a href="#">关于我们</a></li>
		</ul>
	</div>
</div>


<!--  判断个人中心页面中分配的按钮是否在当前页面中存在。因为这个单独的导航页面已经被个人中心页面加载进去了 -->
<#if currentNav??>
    <script type="text/javascript">
        $("#" + "${currentNav}").addClass("active");
    </script>
</#if>

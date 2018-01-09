<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>Trivia</title>
<script type="text/javascript">
	function checkUser() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;

		if (!username) {
			alert("用户名不能为空");
			return false;
		} else if (!password) {
			alert("密码不能为空");
			return false;
		} else {
			return true;
		}

		//document.getElementById("myForm").submit();
	}
</script>


<link rel="stylesheet" href="css/Login.css">

</head>

<body>

	<main>
	<div class="box index">
		<form class="form" action="user/login" method="post" id="myForm"
			onSubmit="return checkUser()">
			<div class="form__cover"></div>
			<div class="form__loader">
				<div class="spinner active">
					<svg class="spinner__circular" viewBox="25 25 50 50">
                        <circle class="spinner__path" cx="50" cy="50"
							r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle>
                    </svg>
				</div>
			</div>
			<div class="form__content">
				<h1>Trivia</h1>
				<h5>${param.msg}</h5>
				<div class="styled-input">
					<input id="username" type="text" class="styled-input__input"
						name="username">
					<div class="styled-input__placeholder">
						<span class="styled-input__placeholder-text">Username</span>
					</div>
					<div class="styled-input__circle"></div>
				</div>
				<div class="styled-input">
					<input id="password" type="text" class="styled-input__input"
						name="password">
					<div class="styled-input__placeholder">
						<span class="styled-input__placeholder-text">Password</span>
					</div>
					<div class="styled-input__circle"></div>
				</div>
				<button type="submit" class="styled-button" id="submit">
					<span class="styled-button__real-text-holder"> <span
						class="styled-button__real-text">Submit</span> <span
						class="styled-button__moving-block face"> <span
							class="styled-button__text-holder"> <span
								class="styled-button__text">Submit</span>
						</span>
					</span> <span class="styled-button__moving-block back"> <span
							class="styled-button__text-holder"> <span
								class="styled-button__text">Submit</span>
						</span>
					</span>
					</span>
				</button>
			</div>
		</form>
	</div>
	</main>

	<script src="js/Login.js" charset="utf-8"></script>

</body>
</html>


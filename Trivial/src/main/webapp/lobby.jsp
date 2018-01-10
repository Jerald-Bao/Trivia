
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Welcome to Trivia Lobby!</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!-- Custom styles for this template -->
<link href="dashboard.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="lobby" id="lobby">
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#">Trivia</a> <a class="navbar-brand"
			href="#"></a>
		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Settings</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Help</a></li>
			</ul>
			<form class="form-inline mt-2 mt-md-0"></form>
		</div>
		</nav>
		<div class="container-fluid lobby-content">
			<div class="row">
				<main class="m-sm-auto pt-3 col-sm-auto col-md-auto" role="main">
				<section class="row text-center placeholders">
				<div class="col-6 col-sm-3 col-lg-3 placeholder lobby-section">
					<h4>Username</h4>
					<div class="text-muted" id="gender">${user.username }</div>
				</div>
				<div class="col-6 col-sm-3 col-lg-3 placeholder lobby-section">
					<h4>Gender</h4>
					<div class="text-muted" id="gender">${user.gender }</div>
				</div>
				<div class="col-6 col-sm-3 col-lg-3 placeholder lobby-section">
					<h4>Total Games</h4>
					<span class="text-muted" id="totalGame">${user.totalGame }</span>
				</div>
				<div class="col-6 col-sm-3 col-lg-3 placeholder lobby-section">
					<h4>Total Wins</h4>
					<span class="text-muted" id="totalWin">${user.totalWin }</span>
				</div>
				</section>
				<h2>Room List</h2>
				<div class="table-responsive">
					<table class="table table-striped" id="roomList">
						<thead>
							<tr>
								<th>RoomID</th>
								<th>Number</th>
								<th>Host</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
					<div class="pageInfo">
						<a id="previous">previous</a> <span id="page"><B>1</B></span> <a
							id="next">next</a>
					</div>
					<span class="createRoom"><B>Create Room</B></span>
				</div>
				</main>
			</div>
		</div>
	</div>
	<main>
	<div class="box main">
		<div class="diceGif" />
		<div class="left">
			<div id="players">
				<div class="player p0">
					<img class="coin" src="images/coin.png">
					<div class="coinNum">x 1</div>
					</img>
					<div class="halo">
						<div class="username"></div>
					</div>
					<div class="boss _1">
						<div class="div"></div>
					</div>
					<ul class="content_1">
					</ul>
				</div>
				<div class="player p1">
					<img class="coin" src="images/coin.png">
					<div class="coinNum">x 1</div>
					</img>
					<div class="halo">
						<div class="username"></div>
					</div>
					<div class="boss _2">
						<div class="div"></div>
					</div>
					<ul class="content_2">
					</ul>
					<div class="player p2">
						<img class="coin" src="images/coin.png">
						<div class="coinNum">x 1</div>
						</img>
						<div class="halo">
							<div class="username"></div>
						</div>
						<div class="boss _3">
							<div class="div"></div>
						</div>
						<ul class="content_3">
						</ul>
					</div>
					<div class="player p3">
						<img class="coin" src="images/coin.png">
						<div class="coinNum">x 1</div>
						</img>
						<div class="halo">
							<div class="username"></div>
						</div>
						<div class="boss _4">
							<div class="div"></div>
						</div>
						<ul class="content_4">
						</ul>
					</div>
				</div>

				<div class="question" style="display: none">
					<img src="images/btn.png" class="qMainBg"></img> <img
						src="images/btn.png" id="btn1" class="qs1Bg"></img> <img
						src="images/btn.png" id="btn2" class="qs2Bg"></img> <img
						src="images/btn.png" id="btn3" class="qs3Bg"></img> <img
						src="images/btn.png" id="btn4" class="qs4Bg"></img> <span
						id="description">4</span> <span id="choice1">2</span> <span
						id="choice2">3</span> <span id="choice3">4</span> <span
						id="choice4">4</span>
				</div>
			</div>
		</div>
		<div class="dice">
			<span></span>
		</div>
		<div class="exit">
			<span></span>
		</div>
	</main>

	<script src="js/main.js" charset="utf-8"></script>



	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/lobby.js"></script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/popper.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
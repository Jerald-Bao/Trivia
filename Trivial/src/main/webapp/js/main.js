var userNames=['1','2','3','4'];
var coins=[0,0,0,0];
var n=-1;
var timer=null;
var roomId;
var main = document.getElementsByClassName('main')[0];
var players_ = document.getElementById('players');
var players=players_.getElementsByClassName('player');
var tops=new Array(120,330,540,750);
var coinTop=[200,410,620,830];
var bosses = players_.getElementsByClassName('boss');
var contents=players_.getElementsByTagName('ul');
var rollBtn=main.getElementsByClassName('dice')[0];
var ms=[0,0,0,0];
var ids=[0,0,0,0];
var currentPlayer=0;
var questionHTML=main.getElementsByClassName('question')[0];
var description=document.getElementById('description');
var choice1=document.getElementById('choice1');
var choice2=document.getElementById('choice2');
var choice3=document.getElementById('choice3');
var choice4=document.getElementById('choice4');
var exit=document.getElementsByClassName('exit')[0];
var coinsHTML=document.getElementsByClassName('coin');
var coinNum=document.getElementsByClassName('coinNum');
var userId;
var nextPlayer;
function enterRoom()
{
	if (json.result)
	{
		for (var i=0;i<4;i++)
		{
			userNames[i]=null;
		}
		if (page=="lobby")
		{
			trans(lobby,initMain);
		}
		for (var i=0;i<json.players.length;i++)
		{
			ids[json.players[i].position]=json.players[i].playerId;
			userNames[json.players[i].position]=json.players[i].playerName;
		}
		if (n==-1)
			n=json.user.position;
		userId=ids[n];
		mainFn();
		if (json.gamestart)
			gameStart();
		roomId=json.roomId;
	}
	else
	{
		alert('房间人数已满!');
	}
}
function initMain()
{
}
function requestRoll()
{
	var jsonSend={};
	jsonSend.request="Roll";
	jsonSend.roomId=roomId;
	websocket.send(JSON.stringify(jsonSend));
	rollBtn.onclick=null;
}
function requestExitRoom()
{
	var jsonSend={};
	jsonSend.request="ExitRoom";
	jsonSend.roomId=roomId;
	websocket.send(JSON.stringify(jsonSend));
}

function sendAnswer(ans)
{
	var jsonSend={};
	jsonSend.request="PlayersAnswer";
	jsonSend.playersAnswer=ans;
	jsonSend.roomId=roomId;
	websocket.send(JSON.stringify(jsonSend));
}
function receiveCreateRoom()
{
	for (var i=0;i<4;i++)
	{
		userNames[i]=null;
	}
	if (page=="lobby")
	{
		trans(lobby,initMain);
	}
	n=json.host.position;
	ids[n]=json.host.playerId;
	userNames[n]=json.host.playerName;
	n=n;
	userId=ids[n];
	mainFn();
	roomId=json.roomId;
}
function receiveExitRoom()
{
	if (userId==json.exitUserId)
		trans(main,lobbyFn);
	else
	{
		for (var i=0;i<4;i++)
			if (ids[i]==json.exitUserId)
				userNames[i]=null;
		mainFn();
	}
	
}

function gameStart()
{
	exit.style.display='none';
	currentPlayer=0;
	nextPlayer=0;
	prepareToRoll();
}
function prepareToRoll()
{
	var username=players[currentPlayer].getElementsByClassName('username')[0];
	username.style.color='#bbbbbb';
	username.style.fontWeight='normal';
	rollBtn.style.display='block';
	currentPlayer=nextPlayer;
	username=players[currentPlayer].getElementsByClassName('username')[0];
	username.style.color='#ffffff';
	username.style.fontWeight='bold';
	if (currentPlayer==n)
		rollBtn.onclick=requestRoll;
}
function playerMove(num,step)
{
	jump(num,step);
}
function displayAnswer()
{
	switch (json.playersAnswer)
	{
		case 1:choice1.style.color='#ff0000';break;
		case 2:choice2.style.color='#ff0000';break;
		case 3:choice3.style.color='#ff0000';break;
		case 4:choice4.style.color='#ff0000';break;
	}
	switch (json.correctAnswer)
	{
		case 1:choice1.style.color='#00ff00';break;
		case 2:choice2.style.color='#00ff00';break;
		case 3:choice3.style.color='#00ff00';break;
		case 4:choice4.style.color='#00ff00';break;
	}
	if (json.result==false)
	{
		block(currentPlayer);
	}
	coins[json.ansUserPos]=json.point;
	displayCoins();
	
	setTimeout('questionHTML.style.display=\'none\';',2000);

	
	if (json.gameOver)
	{
		n=-1;
		alert(userNames[currentPlayer]+' Wins');
		trans(main,lobbyFn);
	}
	else
	{
		prepareToRoll();
	}
}
function displayCoins()
{
	for(var i=0;i<4;i++)
		coinNum[i].innerHTML='x '+coins[i];
}
function displayQuestion()
{
	questionHTML.style.display='block';
	choice1.style.color='#000000';
	choice2.style.color='#000000';
	choice3.style.color='#000000';
	choice4.style.color='#000000';
	description.innerHTML=json.question.description;
	choice1.innerHTML=json.question.choiceA;
	choice2.innerHTML=json.question.choiceB;
	choice3.innerHTML=json.question.choiceC;
	choice4.innerHTML=json.question.choiceD;
	if (currentPlayer==n)
	{
		choice1.onclick=function(){
			sendAnswer('1'); 
			choice1.onclick=null;
			choice2.onclick=null;
			choice3.onclick=null;
			choice4.onclick=null;
			};
		choice2.onclick=function(){
			sendAnswer('2');
			choice1.onclick=null;
			choice2.onclick=null;
			choice3.onclick=null;
			choice4.onclick=null;
			};
		choice3.onclick=function(){
			sendAnswer('3');
			choice1.onclick=null;
			choice2.onclick=null;
			choice3.onclick=null;
			choice4.onclick=null;
		};
		choice4.onclick=function(){
			sendAnswer('4');
			choice1.onclick=null;
			choice2.onclick=null;
			choice3.onclick=null;
			choice4.onclick=null;
			};
	}
	else
	{
		choice1.onclick=null;
		choice2.onclick=null;
		choice3.onclick=null;
		choice4.onclick=null;
	}
}


function playerRoll(player,num)
{
	
	nextPlayer=json.preRollPos;
	var span=rollBtn.getElementsByTagName('span')[0];
	switch (num)
	{
		case 1:
			span.style.background="url(images/001.gif) no-repeat center";
			break;
		case 2:
			span.style.background="url(images/002.gif) no-repeat center";
			break;
		case 3:
			span.style.background="url(images/003.gif) no-repeat center";
			break;
		case 4:
			span.style.background="url(images/004.gif) no-repeat center";
			break;
		case 5:
			span.style.background="url(images/005.gif) no-repeat center";
			break;
		case 6:
			span.style.background="url(images/006.gif) no-repeat center";
			break;
	}
	if (json.question!=null)
	setTimeout(playerRoll2,2000);
	function playerRoll2()
	{
		var lis = contents[player].getElementsByTagName('li');
		lis[2].innerHTML='<div><span class="y"></span><i class="haloS"></i></div>';
		setTimeout('jump('+player+','+num+')',0);
	}
}

var target;
var targetNext;

function createPlatform(num,p,objNum){
	while (objNum < num ) {
			var li = document.createElement('li');
			li.innerHTML = '<div><span class="y"></span><i class="haloS"></i></div>';
			li.style.left = objNum * 100 + 300 + 'px';
			objNum++;
			contents[p].append(li);
	}
	var lis = contents[p].getElementsByTagName('li');
	var span = contents[p].getElementsByTagName('span');
	for (var i = 0; i < lis.length; i++) {
			lis[i].height = getLiHeight(i);
			lis[i].style.height = lis[i].height + 'px';
			lis[i].t = lis[i].getBoundingClientRect().top;
		}
	
	for (var i = 0; i < span.length; i++) {
		span[i].b = parseFloat(getComputedStyle(span[i]).bottom);
	}
	function getLiHeight(i) {
		var h = 0;
		var span = lis[i].getElementsByTagName('span')[0];
		var strong = lis[i].getElementsByTagName('strong')[0];
		var span_h = span.getBoundingClientRect().height;
		if (strong) {
			var strong_h = strong.getBoundingClientRect().height;
			h = span_h + strong_h - 15;
		} else {
			h = span_h;
		}
		return h;
	}
}
function jump(player,num)
{
	var lis = contents[player].getElementsByTagName('li');
	var span = contents[player].getElementsByTagName('span');
	if (span.length>1)
	{
		if (span[1].nextElementSibling!=null)
			span[1].nextElementSibling.style.display = 'block';
		if (span[0].nextElementSibling!=null)
			span[0].nextElementSibling.style.display = 'none';
		switch (player){
			case 0: span[1].classList.add('bHover');
					break;
			case 1: span[1].classList.add('rHover');
					break;
			case 2: span[1].classList.add('gHover');
					break;
			case 3: span[1].classList.add('wHover');
					break;
				}
	}
	
	if (num<=0)
	{
		displayQuestion();
		return;
	}
	num--;
	createPlatform(ms[player]+11,player,ms[player]+10);
	for (var i = 0; i < lis.length; i++) {
			if (lis[i].offsetLeft - 250 <= -contents[player].offsetLeft) {
				contents[player].removeChild(lis[0]);
			}
		}
		
		
	var old = lis[0];
		ms[player]++;
	var New = lis[0];
	targetNext = tops[player];
	target = tops[player]-60;
	down = false;
	timer = setInterval(function() {
		move(player,{
			obj: bosses[player],
			speedTop: 5,
			tagMaxTop: -((ms[player] - 1) * 100) - 50,
			speedBottom: Math.abs(target - targetNext) / 15,
			tagMaxBottom: -ms[player] * 100,
			boardspeed: 3.1,
			target:tops[player]-60,
			targetNext:tops[player],
			callback: function() {
				jump(player, num)
			}
		})
	}, 18);
}
function block(player){
	var lis = contents[player].getElementsByTagName('li');
	lis[2].innerHTML='<div><em class="y"></em></div>';
}	
function move(player,init) {
		//球
		var t = init.obj.offsetTop;
		init.obj.style.top = t - init.speedTop + 'px';
		//板
		var l = parseFloat(getComputedStyle(contents[player]).left);
		var tag = l - init.boardspeed;
		if (tag <= init.tagMaxTop) {
			tag = init.tagMaxTop;
		}
		
		contents[player].style.left = tag + 'px';

		var span = contents[player].getElementsByTagName('span');
		if (t < init.target) {
			clearInterval(timer);
			//下落过程
			down = true;
			timer = setInterval(function() {

				//球
				var t = init.obj.offsetTop;
				init.obj.style.top = t + init.speedBottom + 'px';
				//板
				var l = parseFloat(getComputedStyle(contents[player]).left);
				var tag = l - init.boardspeed;
				if (tag <= init.tagMaxBottom) {
					tag = init.tagMaxBottom;
				}
				contents[player].style.left = tag + 'px';
				if (t >= init.targetNext) {
					span[n].style.bottom = span[n].b + 'px';
					clearInterval(timer);
					typeof init.callback == 'function' && init.callback();
				}
			}, 18)
		}
	}
		
function mainFn() {
    //主窗口
	coins=[0,0,0,0];
	displayCoins();
	main.style.display='block';
	exit.style.display='block';
	exit.onclick=requestExitRoom;
	for (var p = 0; p < players.length; p++) {
		var player=players[p];
		var halo=player.getElementsByClassName('halo')[0];
		var userName=player.getElementsByClassName('username')[0];
		if (userNames[p]!=null)
			userName.innerHTML=userNames[p];
		else
			userName.innerHTML='';
		var content = player.getElementsByTagName('ul')[0];
		content.innerHTML = '';
		content.style.cssText = '';
		var boss = player.getElementsByClassName('boss')[0];
		boss.style.cssText = '';
		var character = boss.getElementsByTagName('div')[0];
		if (userNames[p]!=null)
		{
			character.style.backgroundImage = 'url("images/white.png")';
			boss.style.display = 'block';
		}
		else
		{
			boss.style.display='none';
		}
		var objNum=0;
		content.style.width = 100 * 100 + 'px';
		createPlatform(10,p,objNum);
		var span = content.getElementsByTagName('span');
		var lis=content.getElementsByTagName('li');
		var down = false;
		if (userNames[p]!=null)
		{
			coinsHTML[p].style.display='block';
			coinNum[p].style.display='block';
			coinsHTML[p].style.top=''+coinTop[p]+'px';
			coinNum[p].style.top=''+coinTop[p]+'px';
			span[0].nextElementSibling.style.display = 'block';
			halo.style.display='block';
			switch (p){
				case 0: halo.style.backgroundImage = 'url("images/bbg.png")';
						halo.style.top = '120px';
						span[0].classList.add('bHover');
						break;
				case 1: halo.style.backgroundImage = 'url("images/rbg.png")';
						halo.style.top = '330px';
						span[0].classList.add('rHover');
						break;
				case 2: halo.style.backgroundImage = 'url("images/gbg.png")';
						halo.style.top = '540px';
						span[0].classList.add('gHover');
						break;
				case 3: halo.style.backgroundImage = 'url("images/wbg.png")';
						halo.style.top = '750px';
						span[0].classList.add('wHover');
						break;
					}
		}
		else
		{
			coinsHTML[p].style.display='none';
			coinNum[p].style.display='none';
			halo.style.display='none';
			span[0].nextElementSibling.style.display = 'none';
		}
	}
	
}

    

var wsUri ="ws://localhost:8080/ssm/ws";
var websocket;
var page;
var json;
var roomList;
var lobby;
var pageNum=1;
function init() {
    initWebSocket(); 
	page="lobby";
	
}  
function initWebSocket() { 
    websocket=new WebSocket(wsUri);
    websocket.onopen = function(evt) { 
        onOpen(evt); 
		websocket.state="connected";
    }; 
    websocket.onclose = function(evt) { 
        onClose(evt); 
    }; 
    websocket.onmessage = function(evt) { 
        onMessage(evt); 
    }; 
    websocket.onerror = function(evt) { 
        onError(evt);
    }; 
}  

function onOpen(evt) { 
	requestUser();
	requestRoomList(0,9);
}  

function onClose(evt) { 
	
}  

function onMessage(evt) { 
	json = JSON.parse(evt.data);
	alert('Message Recieved');
	if (json.type=="user")
		displayUser();
	if (json.type=="RoomList")
		displayRoomList();
	if (json.type=="EnterRoom")
		enterRoom();
	if (json.type=="RoomInfo")
		displayRoomInfo();
	if (json.type=="PrepareToRoll")
		prepareToRoll();
	if (json.type=="Roll")
	{
		playerRoll(json.curRollPos,json.rollNum);
	}
	if (json.type=="Question")
		displayQuestion();
	if (json.type=="PlayersAnswer")
		displayAnswer();
	if (json.type=="ExitRoom")
		receiveExitRoom();
	if (json.type=="CreateRoom")
		receiveExitRoom();
		
}   

function onError(evt) { 
}


function trans(obj,fn)
{
	obj.style.display='none';
	fn();
}
function displayUser()
{
	if (page=="lobby")
	{
		var gender=document.getElementById('gender');
		var totalWin=document.getElementById('totalWin');
		var totalGame=document.getElementById('totalGame');
		var winningRate=document.getElementById('winningRate');
		var username=document.getElementById('username');
		username.innerHTML=json.user.username;
		gender.innerHTML=json.user.gender;
		totalWin.innerHTML=json.user.totalWin;
		totalGame.innerHTML=json.user.totalGame;
		winningRate.innerHTML=Math.round(json.user.totalWin/json.user.totalGame*100)+'%';
	}
}
function displayRoomList()
{
	if (page=="lobby")
	{
		
		var trs=roomList.getElementsByTagName('tr');
		for (i=0;i<10;i++)
		{
			var tr=trs[i+1];
			tr.onclick=null;
			var tds=tr.getElementsByTagName('td');
			tds[0].innerHTML='';
			tds[1].innerHTML='';
			tds[2].innerHTML='';
		}
		for (var i=0;i<json.roomList.length;i++)
		{
			var tr=trs[i+1];
			(function(i){
				tr.onclick = function(){ 
					requestEnterRoom(json.roomList[i].roomId);
				}
				})(i);
			var tds=tr.getElementsByTagName('td');
			tds[0].innerHTML=json.roomList[i].roomId;
			tds[1].innerHTML=json.roomList[i].players;
			tds[2].innerHTML=json.roomList[i].host;
		}
		
		pageNum=parseInt(json.from/10)+1;
		var pageNum_=document.getElementById('page');
		pageNum_.innerHTML='<B>'+pageNum+'</B>';
	}
}
function requestEnterRoom(roomId)
{
	var jsonSend={};
	jsonSend.roomId=roomId;
	jsonSend.request="EnterRoom";
	websocket.send(JSON.stringify(jsonSend));
}
function requestCreateRoom()
{
	
	var jsonSend={};
	jsonSend.request="CreateRoom";
	websocket.send(JSON.stringify(jsonSend));
}

function requestRoomList(_from,to)
{
	var jsonSend={};
	jsonSend.from=_from;
	jsonSend.to=to;
	jsonSend.request="RoomList";
	websocket.send(JSON.stringify(jsonSend));
}
function requestUser()
{
	var jsonSend={};
	jsonSend.request="User";
	websocket.send(JSON.stringify(jsonSend));
}
function previousPage()
{
	if (pageNum>1)
		pageNum--;
	requestRoomList((pageNum-1)*10,(pageNum*10)-1);
}
function NextPage()
{
	pageNum++;
	requestRoomList((pageNum-1)*10,(pageNum*10)-1);
}
function lobbyFn(){
	init();
	pageNum=1;
	lobby=document.getElementById("lobby");
	lobby.style.display='block';
	var createRoom=lobby.getElementsByClassName('createRoom')[0];
	createRoom.onclick=function(){
		requestCreateRoom();
	}
	var previous=document.getElementById('previous');
	previous.onclick=function(){
		previousPage();
	}
	var next=document.getElementById('next');
	next.onclick=function(){
		NextPage();
	}
	roomList=document.getElementById('roomList');
	if (websocket.state=="Connected")
	{
		requestUser();
		requestRoomList(0,9);
	}
}
lobbyFn();
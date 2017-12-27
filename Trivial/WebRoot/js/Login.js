
'use strict';

var placeholders = document.querySelectorAll('.styled-input__placeholder-text'),
    inputs = document.querySelectorAll('.styled-input__input');

placeholders.forEach(function (el, i) {
    var value = el.innerText,
        html = '';
    for (var _iterator = value, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator]();;) {
        var _ref;

        if (_isArray) {
            if (_i >= _iterator.length) break;
            _ref = _iterator[_i++];
        } else {
            _i = _iterator.next();
            if (_i.done) break;
            _ref = _i.value;
        }

        var w = _ref;

        if (!value) value = '&nbsp;';
        html += '<span class="letter">' + w + '</span>';
    }
    el.innerHTML = html;
});

inputs.forEach(function (el) {
    var parent = el.parentNode;
    el.addEventListener('focus', function () {
        parent.classList.add('filled');
        placeholderAnimationIn(parent, true);
    }, false);
    el.addEventListener('blur', function () {
        if (el.value.length) return;
        parent.classList.remove('filled');
        placeholderAnimationIn(parent, false);
    }, false);
});

function placeholderAnimationIn(parent, action) {
    var act = action ? 'add' : 'remove';
    var letters = parent.querySelectorAll('.letter');
    letters = [].slice.call(letters, 0);
    if (!action) letters = letters.reverse();
    letters.forEach(function (el, i) {
        setTimeout(function () {
            var contains = parent.classList.contains('filled');
            if (action && !contains || !action && contains) return;
            el.classList[act]('active');
        }, 50 * i);
    });
}

setTimeout(function () {
    document.body.classList.add('on-start');
}, 100);

setTimeout(function () {
    document.body.classList.add('document-loaded');
}, 1800);

//WebSocket
var websocket;
var wsUri ="ws://localhost:8080/Trivial/websocket"; 
var output;  
function init() { 
    output = document.getElementById("output"); 
    testWebSocket(); 
}  
function testWebSocket() { 
    websocket = new WebSocket(wsUri); 
    
    websocket.onopen = function(evt) { 
        onOpen(evt); 
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
}  

function onClose(evt) { 
	
}  

function onMessage(evt) { 
	var json = JSON.parse(evt.data);
	if (json.result=='Success')
		alert('lll');
	else
		alert(json.message||'404');
}   

function onError(evt) { 
}  

var submitBtn=document.getElementById("submit");
submitBtn.onclick=function (){

	var password=document.getElementById('password').value||0;
	var username=document.getElementById('username').value||0;
	var messageObject = {username: username, password : password};  
	websocket.send(JSON.stringify(messageObject));
};
window.addEventListener("load", init, false); 

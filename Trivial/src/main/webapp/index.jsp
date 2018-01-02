<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
  <meta charset="UTF-8">
  <title>Trivial</title>
      <link rel="stylesheet" href="css/Login.css">

</head>

<body>

   <main><div class="box index">
        <form class="form">
            <div class="form__cover"></div>
            <div class="form__loader">
                <div class="spinner active">
                    <svg class="spinner__circular" viewBox="25 25 50 50">
                        <circle class="spinner__path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle>
                    </svg>
                </div>
            </div>
            <div class="form__content">
                <h1>Trivial</h1>
                <div class="styled-input">
                    <input id="username" type="text" class="styled-input__input" name="nickname">
                    <div class="styled-input__placeholder">
                        <span class="styled-input__placeholder-text">Username</span>
                    </div>
                    <div class="styled-input__circle"></div>
                </div><div class="styled-input">
                     <input id="password" type="text" class="styled-input__input">
                    <div class="styled-input__placeholder">
                        <span class="styled-input__placeholder-text">Password</span>
                    </div>
                    <div  class="styled-input__circle"></div>
                </div>
                <button type="button" class="styled-button" id="submit">
                    <span class="styled-button__real-text-holder">
                        <span class="styled-button__real-text">Submit</span>
                        <span class="styled-button__moving-block face">
                            <span class="styled-button__text-holder">
                                <span class="styled-button__text">Submit</span>
                            </span>
                        </span><span class="styled-button__moving-block back">
                            <span class="styled-button__text-holder">
                                <span class="styled-button__text">Submit</span>
                            </span>
                        </span>
                    </span>
                </button>
            </div>

        </form>
        </div>
        <div class="box lobby" >
	        <form class="roomlist" >
	                <h1 class='title'></h1>
		        <div class="roomlist__content" id="roomList">
		        		<div class="roomlist__ele"></div>
		        		<div class="roomlist__ele"></div>
		        		<div class="roomlist__ele"></div>
		        		<div class="roomlist__ele"></div>
		        		<div class="roomlist__ele"></div>
	        		
	            </div>
	        </form>
        </div>
    </main>
  
    <script  src="js/Login.js" charset="utf-8"></script>

</body>
</html>


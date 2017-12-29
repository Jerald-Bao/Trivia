<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
  <meta charset="UTF-8">
  <title>ssm</title>
      <link rel="stylesheet" href="css/Login.css">

</head>

<body>
   <main>
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
    </main>
  
    <script  src="js/Login.js" charset="utf-8"></script>

</body>
</html>


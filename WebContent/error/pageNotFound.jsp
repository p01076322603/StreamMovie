<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
  <title>Page Not Found - StreamMovie</title>
</head>
<style>
  body {
    background-color: rgb(225, 165, 165);
    margin: 0;
  }

  #Container {
    position: relative;
    top: 150px;
    background-color: rgb(255, 255, 255);
    border-radius: 25px;
    height: 320px;
    width: 300px;
    margin: auto;
    text-align: center;
  }

  .fas {
    position: relative;
    top: 25px;
    font-size: 400%;
    color: rgb(70, 70, 70);
  }

  h1 {
    font-size: 80px;
    margin: 25px 0;
    color:rgb(50, 50, 50);
  }

  button {
    padding: 10px;
    border-radius: 10px;
    border: 1px solid #aaa;
    background-color: rgb(193, 76, 61);
    color: white;
    font-weight: 500;
    cursor: pointer;
    transition: .3s;
  }

  button:hover {
    background-color: tomato;
  }
</style>
<body>
  <div id="Container">
    <i class="fas fa-frown-open"></i>
    <h1>404</h1>
    <p>요청하신 페이지를 찾을 수 없습니다.</p>
    <a href="/StreamMovie"><button>메인 페이지로 돌아가기</button></a>
  </div>
</body>
</html>

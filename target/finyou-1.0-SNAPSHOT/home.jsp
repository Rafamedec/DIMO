<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DIMO - Login</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome para ícones -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }

    .login-container {
      border: 2px solid #192c78;
      border-radius: 25px;
      background-color: white;
      padding: 0;
      overflow: hidden;
      box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
      max-width: 1000px;
      margin: 40px auto;
    }

    .login-form {
      padding: 40px;
    }

    .logo {
      font-size: 36px;
      font-weight: bold;
      color: #192c78;
      margin-bottom: 20px;
    }

    .logo span {
      color: #ff6b00;
    }

    .form-label {
      font-weight: 500;
      color: #555;
    }

    .input-group {
      margin-bottom: 20px;
    }

    .input-group-text {
      background-color: #fff;
      border-color: #ced4da;
    }

    .form-control {
      border-radius: 0 5px 5px 0;
      padding: 12px;
    }

    .login-btn {
      background-color: #ff6b00;
      border: none;
      color: white;
      padding: 12px;
      border-radius: 5px;
      width: 100%;
      font-weight: bold;
      cursor: pointer;
      margin-bottom: 15px;
      transition: background-color 0.3s;
    }

    .login-btn:hover {
      background-color: #e05f00;
    }

    .signup-btn {
      background-color: white;
      border: 1px solid #ced4da;
      color: #333;
      padding: 12px;
      border-radius: 5px;
      width: 100%;
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .signup-btn:hover {
      background-color: #f1f1f1;
    }

    .forgot-password {
      text-align: right;
      margin-bottom: 20px;
    }

    .forgot-password a {
      color: #192c78;
      text-decoration: none;
      font-size: 14px;
    }

    .divider {
      display: flex;
      align-items: center;
      margin: 25px 0;
      color: #999;
    }

    .divider::before, .divider::after {
      content: "";
      flex: 1;
      border-bottom: 1px solid #ddd;
    }

    .divider span {
      padding: 0 10px;
    }

    .icon-orange {
      color: #ff6b00;
    }

    .illustration-container {
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #f7f7f7;
      padding: 20px;
    }

    .illustration {
      max-width: 100%;
      height: auto;
    }

    @media (max-width: 768px) {
      .illustration-container {
        display: none;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <div class="login-container row g-0">
    <div class="col-md-6 login-form">
      <div class="logo">
        DIM<span>O</span>
      </div>
      <h5 class="mb-4">Faça login em sua conta DIMO</h5>

      <form action="login" method="post">
        <!-- Campo Email -->
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <div class="input-group">
            <input type="email" class="form-control" id="email" name="email" placeholder="nome@email.com" required>
            <span class="input-group-text">
              <i class="fas fa-envelope"></i>
            </span>
          </div>
        </div>

        <!-- Campo Senha -->
        <div class="mb-3">
          <label for="senha" class="form-label">Senha</label>
          <div class="input-group">
            <input type="password" class="form-control" id="senha" name="senha" placeholder="Insira sua senha" required>
            <span class="input-group-text" onclick="togglePassword()">
              <i class="fas fa-eye" id="toggleIcon"></i>
            </span>
          </div>
        </div>


        <c:if test="${tentativa}">
        <span class="navbar-text text-danger" style="margin-right:10px">
          ${erro}
        </span>
        </c:if>

        <div class="divider">
        </div>

        <button type="submit" class="login-btn">Entre</button>

        <div class="divider">
          <span>OU</span>
        </div>

        <a href="cadastro.jsp" class="signup-btn btn">Cadastre-se</a>

      </form>
    </div>

    <div class="col-md-6 illustration-container">
      <img src="${pageContext.request.contextPath}/resources/imgs/login.jpg" alt="Login illustration" class="illustration" />
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
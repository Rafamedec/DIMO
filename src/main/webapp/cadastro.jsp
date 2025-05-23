<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DIMO - Cadastro</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        .main-container {
            max-width: 480px;
            margin: 0 auto;
            position: relative;
        }

        .top-nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding: 0 10px;
        }

        .nav-logo {
            font-size: 18px;
            font-weight: bold;
            color: #192c78;
        }

        .nav-email {
            color: #ff6b00;
            text-decoration: none;
            font-size: 14px;
        }

        .nav-email:hover {
            color: #ff6b00;
            text-decoration: underline;
        }

        .cadastro-container {
            border: 2px solid #192c78;
            border-radius: 25px;
            background-color: white;
            padding: 40px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }

        .logo {
            text-align: center;
            font-size: 48px;
            font-weight: bold;
            color: #192c78;
            margin-bottom: 10px;
        }

        .logo span {
            color: #ff6b00;
        }

        .subtitle {
            text-align: center;
            color: #666;
            font-size: 14px;
            margin-bottom: 30px;
        }

        .form-label {
            font-weight: 500;
            color: #555;
            margin-bottom: 8px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .form-control {
            border: 1px solid #ced4da;
            border-radius: 5px 0 0 5px;
            padding: 12px 15px;
            font-size: 14px;
        }

        .form-control:focus {
            border-color: #ff6b00;
            box-shadow: 0 0 0 0.2rem rgba(255, 107, 0, 0.25);
        }

        .input-group-text {
            background-color: #ff6b00;
            border: 1px solid #ff6b00;
            border-radius: 0 5px 5px 0;
            color: white;
            padding: 12px 15px;
            cursor: pointer;
        }

        .cadastrar-btn {
            background-color: #ff6b00;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            width: 100%;
            font-weight: bold;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            margin-top: 10px;
        }

        .cadastrar-btn:hover {
            background-color: #e05f00;
        }

        .form-control::placeholder {
            color: #adb5bd;
        }

        @media (max-width: 480px) {
            body {
                padding: 10px;
            }

            .cadastro-container {
                padding: 30px 25px;
            }

            .logo {
                font-size: 40px;
            }
        }
    </style>
</head>
<body>
<div class="main-container">
    <div class="cadastro-container">
        <div class="logo">
            DIM<span>O</span>
        </div>
        <div class="subtitle">
            Cadastre-se já!
        </div>

        <form action="cadastrar" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <div class="input-group">
                    <input type="email" class="form-control" id="email" name="email" placeholder="nome@email.com" required>
                    <span class="input-group-text">
                        <i class="fas fa-envelope"></i>
                    </span>
                </div>
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="senha" name="senha" placeholder="Insira sua senha" required>
                    <span class="input-group-text" onclick="togglePassword()">
                        <i class="fas fa-eye" id="toggleIcon"></i>
                    </span>
                </div>
            </div>

            <c:if test="${erro}">
            <span class="navbar-text text-danger" style="margin-right:10px">
                ${erroMensagem}
            </span>
            </c:if>

            <button type="submit" class="cadastrar-btn">Cadastrar</button>
        </form>
    </div>
</div>

<script>
    function togglePassword() {
        const passwordField = document.getElementById('senha');
        const toggleIcon = document.getElementById('toggleIcon');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            toggleIcon.classList.remove('fa-eye');
            toggleIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            toggleIcon.classList.remove('fa-eye-slash');
            toggleIcon.classList.add('fa-eye');
        }
    }
</script>
</body>
</html>

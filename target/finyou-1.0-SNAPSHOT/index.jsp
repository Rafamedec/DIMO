<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DIMO - Seu dinheiro, seu movimento</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        .logo span {
            color: #ff6b00;
        }

        .content {
            background-color: white;
            padding: 40px 20px;
            text-align: center;
        }

        .big-logo {
            font-size: 60px;
            font-weight: bold;
            color: #192c78;
            margin-bottom: 10px;
        }

        .big-logo span {
            color: #ff6b00;
        }

        .slogan {
            font-size: 18px;
            margin-bottom: 40px;
            color: #192c78;
        }

        .slogan span {
            color: #ff6b00;
        }

        .info-box {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            margin: 20px auto;
            max-width: 500px;
            text-align: center;
        }

        .info-text {
            color: #192c78;
            font-size: 14px;
            margin-bottom: 15px;
        }

        .cta-btn {
            background-color: #ff6b00;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            width: 100%;
            max-width: 250px;
            margin: 10px auto;
        }

        .cta-btn:hover {
            background-color: #ff6b00;
            color: white;
        }
    </style>
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="content">
        <div class="big-logo">DIM<span>O</span></div>
        <p class="slogan">Seu dinheiro, seu m<span>o</span>vimento.</p>

        <div class="info-box">
            <p class="info-text">Na DIMO, você encontra tudo o que precisa para organizar sua vida financeira de forma simples e inteligente. Acompanhe seus gastos de forma segura.</p>
            <p class="info-text">Comece hoje a transformar sua relação com o dinheiro — com DIMO, seu dinheiro em movimento!</p>

            <a href="transacao?acao=listar">
                <button class="cta-btn">Começar agora!</button>
            </a>
        </div>
    </div>


    <!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
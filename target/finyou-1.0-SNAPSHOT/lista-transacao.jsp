<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DIMO - Controle Financeiro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e8a;
            --orange-color: #ff6b00;
            --light-bg: #f8f9fa;
        }

        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .dimo-container {
            max-width: 800px;
            margin: 20px auto;
            border: 2px solid var(--primary-color);
            border-radius: 20px;
            overflow: hidden;
            background-color: white;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .header {
            background-color: var(--primary-color);
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand {
            font-size: 24px;
            font-weight: bold;
        }

        .logout-btn {
            background-color: var(--orange-color);
            color: white;
            border: none;
            padding: 5px 15px;
            border-radius: 5px;
            font-size: 14px;
        }

        .nav-tabs {
            background-color: #f8f9fa;
            border-bottom: none;
            padding: 10px 15px 0;
        }

        .nav-tabs .nav-link {
            border: none;
            margin-right: 5px;
            color: black;
            border-radius: 5px 5px 0 0;
            font-size: 14px;
        }

        .nav-tabs .nav-link.active {
            background-color: var(--orange-color);
            color: white;
        }

        .summary-boxes {
            display: flex;
            justify-content: space-between;
            padding: 15px;
            gap: 10px;
        }

        .summary-box {
            flex: 1;
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .summary-title {
            background-color: var(--orange-color);
            color: white;
            padding: 5px;
            margin: -10px -10px 10px -10px;
            font-weight: bold;
        }

        .negative-value {
            color: red;
            font-size: 22px;
            font-weight: bold;
        }

        .neutral-value {
            color: black;
            font-size: 22px;
            font-weight: bold;
        }

        .positive-value {
            color: green;
            font-size: 22px;
            font-weight: bold;
        }

        .transactions-table {
            padding: 15px;
        }

        .table {
            border: 1px solid #ddd;
        }

        .table th {
            background-color: #f8f9fa;
            font-weight: bold;
        }

        .table td, .table th {
            padding: 10px;
            text-align: center;
            vertical-align: middle;
        }

        .expense {
            color: red;
        }

        .income {
            color: green;
        }

        .action-btn {
            background-color: var(--orange-color);
            color: white;
            border: none;
            padding: 3px 10px;
            margin: 2px;
            border-radius: 5px;
            font-size: 12px;
        }

        .add-transaction-btn {
            background-color: var(--orange-color);
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 15px auto;
            display: block;
            width: 80%;
            border-radius: 5px;
            font-weight: bold;
            text-transform: uppercase;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
            </div>

    <!-- Tab Content -->
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="dados" role="tabpanel" aria-labelledby="dados-tab">
            <!-- Summary Boxes -->
            <div class="summary-boxes">
                <div class="summary-box">
                    <div class="summary-title">SALDO</div>
                    <div class="neutral-value">
                        <c:if test="${not empty saldo }"><fmt:formatNumber value="${saldo}" type="currency" currencySymbol="R$ " /></c:if>
                    </div>
                </div>
                <div class="summary-box">
                    <div class="summary-title">TOTAL SAÍDAS</div>
                    <div class="negative-value">
                        <c:if test="${not empty totalSaida }"><fmt:formatNumber value="${totalSaida}" type="currency" currencySymbol="- R$ " /></c:if>
                    </div>
                </div>
                <div class="summary-box">
                    <div class="summary-title">TOTAL ENTRADAS</div>
                    <div class="positive-value">
                        <c:if test="${not empty totalEntrada }"><fmt:formatNumber value="${totalEntrada}" type="currency" currencySymbol="+ R$ " /></c:if>
                    </div>
                </div>
            </div>

            <!-- Transactions Table -->
            <c:if test="${not empty msg }">
                <div class="alert alert-success">${msg}</div>
            </c:if>
            <c:if test="${not empty erro }">
                <div class="alert alert-danger">${erro}</div>
            </c:if>
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th class="text-end">Valor</th>
                    <th class="text-center">Categoria</th>
                    <th class="text-end">Tipo</th>
                    <th>Data</th>
                    <th class="text-center">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${transacao}" var="transacao">
                    <tr>
                        <td class="text-end">
                            <fmt:formatNumber value="${transacao.valor}" type="currency" currencySymbol="R$ " />
                        </td>
                        <td class="text-center">${transacao.categoria.nome}</td>
                        <td class="text-end">${transacao.tipo.nome}</td>
                        <td>
                            <fmt:parseDate value="${transacao.data}" pattern="yyyy-MM-dd" var="Data"/>
                            <fmt:formatDate value="${Data}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="text-center">
                            <c:url value="transacao" var="link">
                                <c:param name="acao" value="abrir-form-edicao"/>
                                <c:param name="codigo" value="${transacao.codigo}"/>
                            </c:url>
                            <a href="${link}" class="btn btn-primary">Editar</a>

                            <button type="button"
                                    class="btn btn-danger"
                                    data-bs-toggle="modal"
                                    data-bs-target="#excluirModal"
                                    onclick="codigoExcluir.value = ${transacao.codigo}">
                                Excluir
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

        <a
                href="transacao?acao=abrir-form-cadastro"
                class="add-transaction-btn btn btn-primary">
        Adicionar Transação
        </a>

        </div>

            <!-- Modal -->
            <div
                    class="modal fade"
                    id="excluirModal"
                    tabindex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1
                                    class="modal-title fs-5"
                                    id="exampleModalLabel">
                                Confirmar Exclusão
                            </h1>
                            <button
                                    type="button"
                                    class="btn-close"
                                    data-bs-dismiss="modal"
                                    aria-label="Close">
                            </button>
                        </div>
                        <div class="modal-body">
                            <h4>Você confirma a exclusão desta transação?</h4>
                            <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
                        </div>
                        <div class="modal-footer">

                            <form action="transacao" method="post">
                                <input
                                        type="hidden"
                                        name="acao"
                                        value="excluir">
                                <input
                                        type="hidden"
                                        name="codigoExcluir"
                                        id="codigoExcluir">
                                <button
                                        type="button"
                                        class="btn btn-secondary"
                                        data-bs-dismiss="modal">
                                    Não
                                </button>
                                <button
                                        type="submit"
                                        class="btn btn-danger">
                                    Sim
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>



            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>

</body>
</html>
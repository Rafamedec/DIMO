package br.com.fiap.finyou.controller;

import br.com.fiap.finyou.dao.UsuarioDao;
import br.com.fiap.finyou.factory.DaoFactory;
import br.com.fiap.finyou.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {

    private UsuarioDao dao;

    public CadastroServlet() {
        dao = DaoFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);

        boolean sucesso = dao.cadastrarUsuario(usuario);

        if (sucesso) {
            request.setAttribute("mensagem", "Cadastro realizado com sucesso!");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.setAttribute("erro", true);
            request.setAttribute("erroMensagem", "Erro ao realizar cadastro!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }
    }
}

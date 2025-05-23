package br.com.fiap.finyou.controller;

import br.com.fiap.finyou.dao.CategoriaDao;
import br.com.fiap.finyou.dao.TipoDao;
import br.com.fiap.finyou.dao.TransacaoDao;
import br.com.fiap.finyou.dao.impl.OracleTipoDao;
import br.com.fiap.finyou.exception.DBException;
import br.com.fiap.finyou.factory.DaoFactory;
import br.com.fiap.finyou.model.Categoria;
import br.com.fiap.finyou.model.Tipo;
import br.com.fiap.finyou.model.Transacao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {

    private TransacaoDao dao;
    private CategoriaDao categoriaDao;
    private TipoDao tipoDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        dao = DaoFactory.getTransacaoDAO();
        categoriaDao = DaoFactory.getCategoriaDAO();
        tipoDao = DaoFactory.getTipoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao){
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
        }

    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.remover(codigo);
            req.setAttribute("msg", "Transação removida com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }
        listar(req,resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            double preco = Double
                    .parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate
                    .parse(req.getParameter("data"));

            int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));

            Categoria categoria = new Categoria();
            categoria.setCodigo(codigoCategoria);

            int codigoTipo = Integer.parseInt(req.getParameter("tipo"));

            Tipo tipo = new Tipo();
            tipo.setCodigo(codigoTipo);

            HttpSession session = req.getSession();
            String user = (String) session.getAttribute("user");

            Transacao transacao = new Transacao(
                    0,
                    preco,
                    categoria,
                    tipo,
                    data,
                    user
            );

            transacao.setCategoria(categoria);
            transacao.setTipo(tipo);

            dao.cadastrar(transacao);

            req.setAttribute("mensagem", "Transação cadastrada!");

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        //req.getRequestDispatcher("cadastro-transacao.jsp").forward(req, resp);
        abrirFormCadastro(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            double preco = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate
                    .parse(req.getParameter("data"));

            int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));
            Categoria categoria = new Categoria();
            categoria.setCodigo(codigoCategoria);

            int codigoTipo = Integer.parseInt(req.getParameter("tipo"));
            Tipo tipo = new Tipo();
            tipo.setCodigo(codigoTipo);

            Transacao transacao = new Transacao(codigo, preco, categoria, tipo, data);
            transacao.setCategoria(categoria);
            transacao.setTipo(tipo);

            dao.atualizar(transacao);

            req.setAttribute("msg", "Transação atualizada!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        switch (acao) {
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;
            case "abrir-form-cadastro":
                abrirFormCadastro(req, resp);
                break;
        }

    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarOpcoesCategoria(req);
        carregarOpcoesTipo(req);
        req.getRequestDispatcher("cadastro-transacao.jsp").forward(req, resp);
    }

    private void carregarOpcoesCategoria(HttpServletRequest request) {
        List<Categoria> lista = categoriaDao.listar();
        request.setAttribute("categorias", lista);
    }

    private void carregarOpcoesTipo(HttpServletRequest request) {
        List<Tipo> lista = tipoDao.listar();
        request.setAttribute("tipos", lista);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        Transacao transacao = dao.buscar(id, user);
        req.setAttribute("transacao", transacao);
        carregarOpcoesCategoria(req);
        carregarOpcoesTipo(req);
        req.getRequestDispatcher("editar-transacao.jsp")
                .forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        List<Transacao> lista = dao.listar(user);

        double totalEntrada = 0;
        double totalSaida = 0;
        double saldo = 0;

        for(Transacao transacao : lista) {
            if (Objects.equals(transacao.getTipo().getNome(), "Entrada")) {
                totalEntrada += transacao.getValor();
                saldo += transacao.getValor();
            } else {
                totalSaida += transacao.getValor();
                saldo -= transacao.getValor();
            }
        }

        req.setAttribute("transacao", lista);
        req.setAttribute("totalEntrada", totalEntrada);
        req.setAttribute("totalSaida", totalSaida);
        req.setAttribute("saldo", saldo);
        req.getRequestDispatcher("lista-transacao.jsp")
                .forward(req, resp);
    }
}
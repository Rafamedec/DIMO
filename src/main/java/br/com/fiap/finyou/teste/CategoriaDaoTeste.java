package br.com.fiap.finyou.teste;

import br.com.fiap.finyou.dao.CategoriaDao;
import br.com.fiap.finyou.factory.DaoFactory;
import br.com.fiap.finyou.model.Categoria;

import java.util.List;

public class CategoriaDaoTeste {

    public static void main(String[] args) {
        CategoriaDao dao = DaoFactory.getCategoriaDAO();

        List<Categoria> lista = dao.listar();
        for (Categoria categoria : lista) {
            System.out.println(categoria.getCodigo() + " " + categoria.getNome());
        }
    }
}
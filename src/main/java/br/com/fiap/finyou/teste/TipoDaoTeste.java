package br.com.fiap.finyou.teste;

import br.com.fiap.finyou.dao.TipoDao;
import br.com.fiap.finyou.factory.DaoFactory;
import br.com.fiap.finyou.model.Tipo;

import java.util.List;

public class TipoDaoTeste {

    public static void main(String[] args) {
        TipoDao dao = (TipoDao) DaoFactory.getCategoriaDAO();

        List<Tipo> lista = dao.listar();
        for (Tipo categoria : lista) {
            System.out.println(categoria.getCodigo() + " " + categoria.getNome());
        }
    }
}
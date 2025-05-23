package br.com.fiap.finyou.teste;

import br.com.fiap.finyou.dao.TransacaoDao;
import br.com.fiap.finyou.dao.CategoriaDao;
import br.com.fiap.finyou.dao.TipoDao;
import br.com.fiap.finyou.exception.DBException;
import br.com.fiap.finyou.factory.DaoFactory;
import br.com.fiap.finyou.model.Categoria;
import br.com.fiap.finyou.model.Tipo;
import br.com.fiap.finyou.model.Transacao;

import java.time.LocalDate;
import java.util.List;

public class TransacaoDaoTeste {
    public static void main(String[] args) {

        TransacaoDao dao = DaoFactory.getTransacaoDAO();
        CategoriaDao categoriaDao = DaoFactory.getCategoriaDAO();
        TipoDao tipoDao = DaoFactory.getTipoDAO();

        try {
            // Buscar uma categoria existente
            List<Categoria> categorias = categoriaDao.listar();
            if (categorias.isEmpty()) {
                System.out.println("Erro: Nenhuma categoria cadastrada no sistema.");
                return;
            }
            Categoria categoria = categorias.get(0);

            // Buscar um tipo existente
            List<Tipo> tipos = tipoDao.listar();
            if (tipos.isEmpty()) {
                System.out.println("Erro: Nenhum tipo cadastrado no sistema.");
                return;
            }
            Tipo tipo = tipos.get(0);

            // Cadastrar uma transação
            Transacao transacao = new Transacao(
                    0,                           // código (será gerado pelo banco)
                    77.49,                       // valor
                    categoria,                   // categoria
                    tipo,                        // tipo
                    LocalDate.of(2024, 10, 21),   // data
                    "email@email.com" // email usuario
            );

            try {
                dao.cadastrar(transacao);
                System.out.println("Transação cadastrada.");
            } catch (DBException e) {
                e.printStackTrace();
            }

            // Buscar e atualizar uma transação
            Transacao transacaoBuscada = dao.buscar(1, "email@email.com");
            if (transacaoBuscada != null) {
                transacaoBuscada.setValor(891.99);
                try {
                    dao.atualizar(transacaoBuscada);
                    System.out.println("Transação atualizada.");
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Transação com ID 1 não encontrada.");
            }

            // Listar todas as transações
            List<Transacao> lista = dao.listar("email@email.com");
            System.out.println("Lista de transações:");
            for (Transacao item : lista) {
                System.out.println(
                        "ID: " + item.getCodigo() +
                                " | Valor: " + item.getValor() +
                                " | Data: " + item.getData() +
                                " | Categoria: " + item.getCategoria().getNome() +
                                " | Tipo: " + item.getTipo().getNome()
                );
            }

            // Remover uma transação
            try {
                dao.remover(1);
                System.out.println("Transação removida.");
            } catch (DBException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Erro ao executar teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
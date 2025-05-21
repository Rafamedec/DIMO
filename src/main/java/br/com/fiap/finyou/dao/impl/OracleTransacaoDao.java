package br.com.fiap.finyou.dao.impl;

import br.com.fiap.finyou.dao.ConnectionManager;
import br.com.fiap.finyou.dao.TransacaoDao;
import br.com.fiap.finyou.exception.DBException;
import br.com.fiap.finyou.model.Categoria;
import br.com.fiap.finyou.model.Tipo;
import br.com.fiap.finyou.model.Transacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDao implements TransacaoDao {

    private Connection conexao;

    @Override
    public void cadastrar(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO TB_TRANSACAO " +
                    "(COD_TRANSACAO, " +
                    "VALOR_TRANSACAO, DATA_TRANSACAO, COD_CATEGORIA, COD_TIPO) " +
                    "VALUES (SQ_TB_TRANSACAO.NEXTVAL, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, transacao.getValor());
            stmt.setDate(2, Date.valueOf(transacao.getData()));
            stmt.setInt(3, transacao.getCategoria().getCodigo());
            stmt.setInt(4, transacao.getTipo().getCodigo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void atualizar(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_TRANSACAO SET " +
                    "VALOR_TRANSACAO = ?, " +
                    "DATA_TRANSACAO = ?, " +
                    "COD_CATEGORIA = ?, " +
                    "COD_TIPO = ? " +
                    "WHERE COD_TRANSACAO = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, transacao.getValor());
            stmt.setDate(2, Date.valueOf(transacao.getData()));
            stmt.setInt(3, transacao.getCategoria().getCodigo());
            stmt.setInt(4, transacao.getTipo().getCodigo());
            stmt.setInt(5, transacao.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remover(int codigo) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM TB_TRANSACAO WHERE COD_TRANSACAO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Transacao buscar(int id) {

        Transacao transacao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_TRANSACAO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_TRANSACAO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA " +
                    "INNER JOIN TB_TIPO " +
                    "ON TB_TRANSACAO.COD_TIPO = TB_TIPO.COD_TIPO " +
                    "WHERE TB_TRANSACAO.COD_TRANSACAO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("COD_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO")
                        .toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                int codigoTipo = rs.getInt("COD_TIPO");
                String nomeTipo = rs.getString("TIPO_TRANSACAO");

                Categoria categoria = new Categoria(
                        codigoCategoria, nomeCategoria
                );

                Tipo tipo = new Tipo(
                        codigoTipo, nomeTipo
                );

                transacao = new Transacao(
                        codigo, valor, categoria, tipo, data
                );
                transacao.setCategoria(categoria);
                transacao.setTipo(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transacao;
    }

    @Override
    public List<Transacao> listar() {

        List<Transacao> lista = new ArrayList<Transacao>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_TRANSACAO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_TRANSACAO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA " +
                    "INNER JOIN TB_TIPO " +
                    "ON TB_TRANSACAO.COD_TIPO = TB_TIPO.COD_TIPO";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO")
                        .toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                int codigoTipo = rs.getInt("COD_TIPO");
                String nomeTipo = rs.getString("TIPO_TRANSACAO");

                Categoria categoria = new Categoria(
                        codigoCategoria, nomeCategoria
                );

                Tipo tipo = new Tipo(
                        codigoTipo, nomeTipo
                );

                Transacao transacao = new Transacao(
                        codigo, valor, categoria, tipo, data
                );
                transacao.setCategoria(categoria);
                transacao.setTipo(tipo);

                lista.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
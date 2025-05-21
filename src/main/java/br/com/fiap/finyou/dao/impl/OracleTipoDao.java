package br.com.fiap.finyou.dao.impl;

import br.com.fiap.finyou.dao.TipoDao;
import br.com.fiap.finyou.dao.ConnectionManager;
import br.com.fiap.finyou.dao.TipoDao;
import br.com.fiap.finyou.model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTipoDao implements TipoDao {

    private Connection conexao;

    @Override
    public List<Tipo> listar() {
        List<Tipo> lista = new ArrayList<Tipo>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            stmt = conexao.prepareStatement("SELECT * FROM TB_TIPO");
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_TIPO");
                String nome = rs.getString("TIPO_TRANSACAO");
                Tipo tipo = new Tipo(codigo, nome);
                lista.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
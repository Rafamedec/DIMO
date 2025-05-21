package br.com.fiap.finyou.factory;

import br.com.fiap.finyou.dao.CategoriaDao;
import br.com.fiap.finyou.dao.TransacaoDao;
import br.com.fiap.finyou.dao.UsuarioDao;
import br.com.fiap.finyou.dao.impl.OracleCategoriaDao;
import br.com.fiap.finyou.dao.impl.OracleTipoDao;
import br.com.fiap.finyou.dao.impl.OracleTransacaoDao;
import br.com.fiap.finyou.dao.impl.OracleUsuarioDao;

public class DaoFactory {

    public static TransacaoDao getTransacaoDAO() {
        return new OracleTransacaoDao();
    }

    public static CategoriaDao getCategoriaDAO() {
        return new OracleCategoriaDao();
    }

    public static OracleTipoDao getTipoDAO() {
        return new OracleTipoDao();
    }

    public static UsuarioDao getUsuarioDAO() {
        return new OracleUsuarioDao();
    }

}
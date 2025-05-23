package br.com.fiap.finyou.dao;

import br.com.fiap.finyou.exception.DBException;
import br.com.fiap.finyou.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void cadastrar(Transacao transacao) throws DBException;
    void atualizar(Transacao transacao) throws DBException;
    void remover(int codigo) throws DBException;
    Transacao buscar(int id, String emailUsuario);
    List<Transacao> listar(String emailUsuario);
}
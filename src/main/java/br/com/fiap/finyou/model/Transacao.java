package br.com.fiap.finyou.model;

import java.time.LocalDate;

public class Transacao {

    private int codigo;
    private double valor;
    private LocalDate data;
    private Categoria categoria;
    private Tipo tipo;

    // Constructor for new transactions
    public Transacao(int codigo, double valor, Categoria categoria, Tipo tipo, LocalDate data) {
        this.codigo = codigo;
        this.valor = valor;
        this.categoria = categoria;
        this.tipo = tipo;
        this.data = data;
    }

    // Default constructor
    public Transacao() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao [codigo=" + codigo + ", valor=" + valor + ", data=" + data
                + ", categoria=" + (categoria != null ? categoria.getNome() : "null")
                + ", tipo=" + (tipo != null ? tipo.getNome() : "null") + "]";
    }
}
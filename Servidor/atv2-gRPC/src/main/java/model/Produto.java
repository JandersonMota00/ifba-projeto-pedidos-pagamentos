package model;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome;
    private double preco;
    private int estoque;
    private int mediaTempoPreparo;

    public Produto(String nome, double preco, int estoque, int mediaTempoPreparo) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.mediaTempoPreparo = mediaTempoPreparo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getMediaTempoPreparo() {
        return mediaTempoPreparo;
    }

    public void setMediaTempoPreparo(int mediaTempoPreparo) {
        this.mediaTempoPreparo = mediaTempoPreparo;
    }

    @Override
    public String toString() {
        return "Produto: " +
                "\nNome: " + nome +
                "\nPreço: " + preco +
                "\nEstoque: " + estoque +
                "\nTempo preparo: " + mediaTempoPreparo;
    }
}

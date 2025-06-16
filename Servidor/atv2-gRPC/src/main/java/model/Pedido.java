package model;

import java.util.Map;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private String nomeCliente;
    private Map<Integer, Produto> produtos;
    private boolean ativo;

    public Pedido(String nomeCliente, Map<Integer, Produto> produtos) {
        this.id = UUID.randomUUID();
        this.nomeCliente = nomeCliente;
        this.produtos = produtos;
        this.ativo = true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Map<Integer, Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<Integer, Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getValorTotal() {
        double valorTotal = 0;
        for (Map.Entry<Integer, Produto> produto : produtos.entrySet()) {
            valorTotal += produto.getValue().getPreco() * produto.getKey();
        }
        return valorTotal;
    }
}

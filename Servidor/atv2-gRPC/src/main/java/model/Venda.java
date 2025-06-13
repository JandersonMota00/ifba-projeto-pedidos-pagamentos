package model;

import java.time.LocalDate;
import java.util.UUID;

public class Venda {
    private UUID id;
    private Pedido pedido;
    private LocalDate horarioCompra;

    public Venda(Pedido pedido) {
        this.id = UUID.randomUUID();
        this.pedido = pedido;
        this.horarioCompra = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDate getHorarioCompra() {
        return horarioCompra;
    }

    public void setHorarioCompra(LocalDate horarioCompra) {
        this.horarioCompra = horarioCompra;
    }
}

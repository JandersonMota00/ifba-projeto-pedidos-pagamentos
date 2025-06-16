package com.atv2gRPC.sitemas_distribuidos_atv2.dtos;

import java.util.List;

public record PedidoDTO(String nomeCliente, List<String> produtos, Double valorPedido, String idPedido) {
}

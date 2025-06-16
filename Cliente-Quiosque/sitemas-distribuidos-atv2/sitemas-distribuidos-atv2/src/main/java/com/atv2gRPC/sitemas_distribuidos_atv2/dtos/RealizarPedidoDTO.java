package com.atv2gRPC.sitemas_distribuidos_atv2.dtos;

import java.util.List;

public record RealizarPedidoDTO(String nomeCliente, double valorPago, List<ProdutoPedidoDTO> produtos) {
}

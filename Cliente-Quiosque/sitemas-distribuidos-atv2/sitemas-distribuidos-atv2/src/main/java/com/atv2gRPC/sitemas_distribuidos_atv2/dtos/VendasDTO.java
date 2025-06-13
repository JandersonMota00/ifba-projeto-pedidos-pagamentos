package com.atv2gRPC.sitemas_distribuidos_atv2.dtos;

import java.util.List;

public record VendasDTO(String nomeCliente, Double valorVenda, List<String> produtos) {
}

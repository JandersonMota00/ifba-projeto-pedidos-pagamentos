package com.atv2gRPC.sitemas_distribuidos_atv2.controllers;

import com.atv2gRPC.proto.conexoes.*;
import com.atv2gRPC.sitemas_distribuidos_atv2.dtos.ProdutoDTO;
import com.atv2gRPC.sitemas_distribuidos_atv2.dtos.RealizarPedidoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ConexoesServiceGrpc.ConexoesServiceBlockingStub stub;

    public ClienteController(ConexoesServiceGrpc.ConexoesServiceBlockingStub stub) {
        this.stub = stub;
    }

    @GetMapping("/produtos")
    public List<ProdutoDTO> visualizarProdutos() {
        VisualizarProdutosRequest request = VisualizarProdutosRequest.newBuilder()
                .build();
        VisualizarProdutosResponse response = stub.visualizarProdutos(request);
        List<ProdutoDTO> produtos = new ArrayList<>();

        response.getProdutoList().stream().forEach(produto -> {
            produtos.add(new ProdutoDTO(
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getEstoque(),
                    produto.getTempoPreparo()
            ));
        });
        return produtos;
    }

    @PostMapping("/realizarPedido")
    public String realizarPedido(@RequestBody RealizarPedidoDTO pedidoDTO) {
        RealizarPedidoRequest.Builder request = RealizarPedidoRequest.newBuilder()
                .setNomeCliente(pedidoDTO.nomeCliente())
                .setValorPago(pedidoDTO.valorPago());

        pedidoDTO.produtos().forEach(produto -> {
            RealizarPedidoProdutos prod = RealizarPedidoProdutos.newBuilder()
                    .setQuantidade(produto.quantidade())
                    .setNome(produto.nome())
                    .build();

            request.addProdutos(prod);
        });

        RealizarPedidoResponse response = stub.realizarPedido(request.build());

        return response.toString();
    }
}
package com.atv2gRPC.sitemas_distribuidos_atv2.controllers;

import com.atv2gRPC.proto.conexoes.*;
import com.atv2gRPC.sitemas_distribuidos_atv2.dtos.PedidoDTO;
import com.atv2gRPC.sitemas_distribuidos_atv2.dtos.ProdutoDTO;
import com.atv2gRPC.sitemas_distribuidos_atv2.dtos.VendasDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiosque")
public class QuiosqueController {

    private final ConexoesServiceGrpc.ConexoesServiceBlockingStub stub;

    public QuiosqueController(ConexoesServiceGrpc.ConexoesServiceBlockingStub stub) {
        this.stub = stub;
    }

    @PostMapping("/adicionarProduto")
    public String iniciar(@RequestBody ProdutoDTO produtoDTO) {
        AdicionarProdutoRequest request = AdicionarProdutoRequest.newBuilder()
                .setNome(produtoDTO.nome())
                .setPreco(produtoDTO.preco())
                .setEstoque(produtoDTO.estoque())
                .setTempoPreparo(produtoDTO.mediaTempo())
                .build();
        AdicionarProdutoResponse response = stub.adicionarProduto(request);
        return response.getConfirmacao();
    }

    @PostMapping("/removerProduto")
    public String removerProduto(@RequestParam String nomeProduto) {
        RemoverProdutoRequest request = RemoverProdutoRequest.newBuilder()
                .setNome(nomeProduto)
                .build();
        RemoverProdutoResponse response = stub.removerProduto(request);
        return response.getConfirmacao();
    }

    @PostMapping("/finalizarPedido")
    public String finalizarPedido(@RequestParam String idPedido) {
        FinalizarPedidoRequest request = FinalizarPedidoRequest.newBuilder()
                .setIdPedido(idPedido)
                .build();
        FinalizarPedidoResponse response = stub.finalizarPedido(request);
        return response.getMensagem();
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

    @GetMapping("/vendas")
    public List<VendasDTO> visualizarVendas() {
        VisualizarVendasRequest request = VisualizarVendasRequest.newBuilder()
                .build();
        VisualizarVendasResponse response = stub.visualizarVendas(request);
        List<VendasDTO> vendas = new ArrayList<>();

        response.getVendasList().stream().forEach(venda -> {
            vendas.add(new VendasDTO(
                    venda.getNomeCliente(),
                    venda.getValorVenda(),
                    venda.getProdutosList().stream().map(VisualizarProdutoVenda::getNome).toList()
            ));
        });
        return vendas;
    }

    @GetMapping("/pedidos")
    public List<PedidoDTO> visualizarPedidos() {
        VisualizarPedidosRequest request = VisualizarPedidosRequest.newBuilder()
                .build();
        VisualizarPedidosResponse response = stub.visualizarPedidos(request);
        List<PedidoDTO> pedidos = new ArrayList<>();

        response.getPedidosList().stream().forEach(pedido -> {
            pedidos.add(new PedidoDTO(
                    pedido.getNomeCliente(),
                    pedido.getProdutosList().stream().map(VisualizarProdutoPedido::getNome).toList(),
                    pedido.getValorPedido(),
                    pedido.getIdPedido()
            ));
        });
        return pedidos;
    }


}

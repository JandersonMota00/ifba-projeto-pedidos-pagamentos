package service;

import com.proto.conexoes.*;
import io.grpc.stub.StreamObserver;
import model.Pedido;
import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConexoesServiceImpl extends ConexoesServiceGrpc.ConexoesServiceImplBase {
    private List<Produto> estoque;
    private List<Pedido> pedidos;
    private List<Venda> vendas;

    public ConexoesServiceImpl() {
        estoque = new ArrayList<>();
        pedidos = new ArrayList<>();
        vendas = new ArrayList<>();

        /*
        Produto produto = new Produto("Pastel", 10.0, 40, 10);
        Produto produto2 = new Produto("Coxinha", 10.0, 40, 10);
        estoque.add(produto);
        estoque.add(produto2);

        HashMap<Integer, Produto> produtos = new HashMap<>();
        produtos.put(1, produto);

        Pedido pedido = new Pedido("Sarah", produtos);
        pedidos.add(pedido);
        */
    }

    public void adicionarProduto(AdicionarProdutoRequest request, StreamObserver<AdicionarProdutoResponse> responseObserver) {
        boolean existe = false;
        for (Produto produto : estoque) {
            if (produto.getNome().equals(request.getNome())) {
                AdicionarProdutoResponse response = AdicionarProdutoResponse.newBuilder()
                        .setConfirmacao("Produto já cadastrado no sistema!")
                        .build();
                responseObserver.onNext(response);
                existe = true;
            }
        }
        if (!existe) {
            Produto novoProduto = new Produto(request.getNome(), request.getPreco(), request.getEstoque(), request.getTempoPreparo());
            estoque.add(novoProduto);
            AdicionarProdutoResponse response = AdicionarProdutoResponse.newBuilder()
                    .setConfirmacao("Produto cadastrado!")
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    public void removerProduto(RemoverProdutoRequest request, StreamObserver<RemoverProdutoResponse> responseObserver) {
        boolean existe = false;
        Produto produtoRemover = null;
        for (Produto produto : estoque) {
            if (produto.getNome().equals(request.getNome())) {
                produtoRemover = produto;
                RemoverProdutoResponse response = RemoverProdutoResponse.newBuilder()
                        .setConfirmacao("Produto removido!")
                        .build();
                responseObserver.onNext(response);
                existe = true;
                break;
            }
        }

        estoque.remove(produtoRemover);

        if (!existe) {
            RemoverProdutoResponse response = RemoverProdutoResponse.newBuilder()
                    .setConfirmacao("Produto não existe no sistema!")
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    public void visualizarVendas(VisualizarVendasRequest request, StreamObserver<VisualizarVendasResponse> responseObserver) {
        VisualizarVendasResponse.Builder response = VisualizarVendasResponse.newBuilder();

        for (Venda venda : vendas) {
            ArrayList<Produto> produtos = new ArrayList<>(venda.getPedido().getProdutos().values());
            VisualizarVenda.Builder vendaResponse = VisualizarVenda.newBuilder()
                    .setValorVenda(venda.getPedido().getValorTotal())
                    .setNomeCliente(venda.getPedido().getNomeCliente())
                    .setDataHora(venda.getHorarioCompra().toString());

            for (Produto produto : produtos) {
                VisualizarProdutoVenda prod = VisualizarProdutoVenda.newBuilder()
                        .setNome(produto.getNome())
                        .build();

                vendaResponse.addProdutos(prod);
            }
            response.addVendas(vendaResponse);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void visualizarPedidos(VisualizarPedidosRequest request, StreamObserver<VisualizarPedidosResponse> responseObserver) {
        VisualizarPedidosResponse.Builder response = VisualizarPedidosResponse.newBuilder();
        for (Pedido pedido : pedidos) {
            if (pedido.isAtivo()) {
                double valorTotal = 0;
                ArrayList<Produto> produtos = new ArrayList<>(pedido.getProdutos().values());
                VisualizarPedidos.Builder pedidoResponse = VisualizarPedidos.newBuilder()
                        .setNomeCliente(pedido.getNomeCliente())
                        .setValorPedido(pedido.getValorTotal())
                        .setIdPedido(pedido.getId().toString());

                for (Produto produto : produtos) {
                    VisualizarProdutoPedido prod = VisualizarProdutoPedido.newBuilder()
                            .setNome(produto.getNome())
                            .build();

                    pedidoResponse.addProdutos(prod);

                }
                response.addPedidos(pedidoResponse);
            }
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void finalizarPedido(FinalizarPedidoRequest request, StreamObserver<FinalizarPedidoResponse> responseObserver) {
        boolean existe = false;
        StringBuilder mensagemResposta = new StringBuilder("Retorno\n");

        for (Pedido pedido : pedidos) {
            if (request.getIdPedido().equals(pedido.getId().toString()) && pedido.isAtivo()) {
                pedido.setAtivo(false);
                Venda vendaNova = new Venda(pedido);
                vendas.add(vendaNova);
                mensagemResposta.append("Pedido finalizado!");
                existe = true;
            }
        }

        if (!existe) {
            mensagemResposta.append("Pedido não existe ou já foi finalizado!\n");
        }

        FinalizarPedidoResponse response = FinalizarPedidoResponse.newBuilder()
                .setMensagem(mensagemResposta.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void visualizarProdutos(VisualizarProdutosRequest request, StreamObserver<VisualizarProdutosResponse> responseObserver) {
        VisualizarProdutosResponse.Builder response = VisualizarProdutosResponse.newBuilder();
        for (Produto produto : estoque) {
            VisualizarProduto prod = VisualizarProduto.newBuilder()
                    .setNome(produto.getNome())
                    .setPreco(produto.getPreco())
                    .setEstoque(produto.getEstoque())
                    .setTempoPreparo(produto.getMediaTempoPreparo())
                    .build();

            response.addProduto(prod);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void realizarPedido(RealizarPedidoRequest request, StreamObserver<RealizarPedidoResponse> responseObserver) {
        StringBuilder mensagemResposta = new StringBuilder();
        mensagemResposta.append("Retorno\n");
        boolean naoPodeAceitar = false;
        int mediaPreparo = 0;
        Map<Integer, Produto> produtos = new HashMap<>();

        for (RealizarPedidoProdutos produto : request.getProdutosList()) {
            for (Produto produtoEstoque : estoque) {
                if (!produto.getNome().equals(produtoEstoque.getNome())) {
                    mensagemResposta.append("Produto: " + produto.getNome() + " não existe no sistema!\n");
                    naoPodeAceitar = true;
                } else {
                    if (produto.getQuantidade() > produtoEstoque.getEstoque()) {
                        mensagemResposta.append("Produto: " + produto.getNome() + " sem estoque para atender seu pedido!\n");
                        naoPodeAceitar = true;
                    } else {
                        produtos.put(produto.getQuantidade(), produtoEstoque);
                        //Redução do estoque
                        produtoEstoque.setEstoque(produtoEstoque.getEstoque() - produto.getQuantidade());
                        mediaPreparo += produtoEstoque.getMediaTempoPreparo();
                    }
                }
            }
        }

        if (!naoPodeAceitar) {
            Pedido novoPedido = new Pedido(request.getNomeCliente(), produtos);
            pedidos.add(novoPedido);
            mediaPreparo = mediaPreparo / produtos.size();
            mensagemResposta.append("Pedido realizado com sucesso!\n");
        }
        RealizarPedidoResponse response = RealizarPedidoResponse.newBuilder()
                .setConfirmacao(mensagemResposta.toString())
                .setPrevisaoTempo(mediaPreparo)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

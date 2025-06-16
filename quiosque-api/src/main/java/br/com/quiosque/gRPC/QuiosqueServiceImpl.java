package br.com.quiosque.gRPC;

import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;

// Importe as novas classes geradas pelo protobuf
//import br.com.quiosque.grpc.*;

@GrpcService
public class QuiosqueServiceImpl /*extends QuiosqueServiceGrpc.QuiosqueServiceImplBase*/ {

    @Override
    public void getProducts(Empty request, StreamObserver<ProductList> responseObserver) {
        // Lógica para buscar produtos do banco de dados (exemplo)
        Product product1 = Product.newBuilder()
            .setId("p1")
            .setName("Hambúrguer")
            .setPrice(25.50)
            .build();
        Product product2 = Product.newBuilder()
            .setId("p2")
            .setName("Refrigerante")
            .setPrice(8.00)
            .build();
        ProductList productList = ProductList.newBuilder()
            .addProducts(product1)
            .addProducts(product2)
            .build();

        responseObserver.onNext(productList);
        responseObserver.onCompleted();
    }

    @Override
    public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        // Lógica para calcular o total e salvar o pedido no banco (exemplo)
        double total = request.getItemsList().stream()
            .mapToDouble(item -> 25.50 * item.getQuantity()) // Preço fixo para exemplo
            .sum();

        OrderResponse response = OrderResponse.newBuilder()
            .setOrderId("pedido-abc-123")
            .setTotalAmount(total)
            .setStatus("PENDING_PAYMENT")
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void processPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        // Lógica de pagamento (exemplo)
        PaymentResponse response = PaymentResponse.newBuilder()
            .setTransactionId("transacao-xyz-789")
            .setSuccess(true)
            .setMessage("Pagamento com PIX aprovado!")
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
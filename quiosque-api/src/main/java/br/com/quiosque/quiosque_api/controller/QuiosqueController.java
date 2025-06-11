package br.com.quiosque.quiosque_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Importe as novas classes gRPC
import br.com.quiosque.grpc.*;

@RestController
// A rota base da API foi alterada para "/api/quiosque"
@RequestMapping("/api/quiosque")
public class QuiosqueController {

    // O Spring injetará o bean configurado no GrpcClientConfig
    @Autowired
    private QuiosqueServiceGrpc.QuiosqueServiceBlockingStub quiosqueServiceStub;

    @GetMapping("/products")
    public List<Product> getProducts() {
        ProductList productList = quiosqueServiceStub.getProducts(Empty.newBuilder().build());
        return productList.getProductsList();
    }

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return quiosqueServiceStub.createOrder(request);
    }
    
    @PostMapping("/payments")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
        return quiosqueServiceStub.processPayment(request);
    }
}
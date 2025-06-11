package br.config;

import org.springframework.context.annotation.Configuration;
import net.devh.boot.grpc.client.inject.GrpcClient;
import br.com.quiosque.grpc.QuiosqueServiceGrpc;

@Configuration
public class GrpcClientConfig {
    
    // Injeta um cliente gRPC que se conecta ao serviço "quiosque-service"
    // configurado no application.properties
    @GrpcClient("quiosque-service")
    public QuiosqueServiceGrpc.QuiosqueServiceBlockingStub quiosqueServiceStub;
}
package com.atv2gRPC.sitemas_distribuidos_atv2.configs;

import com.atv2gRPC.proto.conexoes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcConfig {

    @Bean
    ConexoesServiceGrpc.ConexoesServiceBlockingStub stubCatalogo (GrpcChannelFactory channels) {
        return ConexoesServiceGrpc.newBlockingStub(channels.createChannel("conexao-service"));
    }
}

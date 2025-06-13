package servidor;

import service.ConexoesServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ServidorGRPC {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Servidor no ar!");

        ConexoesServiceImpl service = new ConexoesServiceImpl();
        Server server = ServerBuilder.forPort(3051)
                .addService(service)
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Servidor sendo finalizado!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            server.shutdown();
            System.out.println("Servidor finalizado!");
        }));
        server.awaitTermination();
    }
}

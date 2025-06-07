# IFBA Projeto de Pedidos e Pagamentos

- **Instituição:** IFBA - Instituto Federal da Bahia
- **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
- **Disciplina:** Sistemas Distribuídos
- **Projeto:** Criar um sistema web (Sistema de pedidos e pagamentos para quiosques autônomos de praça de alimentação)
- **Professor:** Felipe de Souza Silva
- **Semestre:** 5
- **Ano:** 2025.1

## Integrantes do Projeto

<table>
  <tr>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/80362674?v=4" width="100px;" alt="Foto do Integrante Janderson"/><br />
      <sub><b><a href="https://github.com/JandersonMota">Janderson Mota</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/110790276?v=4" width="100px;" alt="Foto da Integrante Sarah"/><br />
      <sub><b><a href="https://github.com/">Sarah Pithon</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/114778311?v=4" width="100px;" alt="Foto d0 Integrante Salvador"/><br />
      <sub><b><a href="https://github.com/">Salvador Cerqueira</a></b></sub>
    </td>
  </tr>
</table>

## Descrição do projeto

### Objetivo

Desenvolver um sistema distribuído completo em equipe, combinando:

- Definição arquitetural (modelo de distribuição e protocolos)
- Justificativa técnica (por que usar determinado modelo)
- Implementação funcional (com uso de sockets ou RPC/gRPC)
- Documentação técnica (diagramas, padrões, desafios, decisões)

### Descrição

Sua equipe (até 3 alunos por equipe) foi contratada para desenvolver uma solução de software distribuída para um dos seguintes desafios (à escolha do grupo): 
- Sistema de pedidos e pagamentos para quiosques autônomos de praça de alimentação

### Etapas Obrigatórias

1. Escolha e Justificativa do Modelo Arquitetural 
- Escolher entre Cliente-Servidor, P2P, Microservices, Edge/Fog Computing. 
- Justificar tecnicamente a escolha (latência, escalabilidade, dependência, etc.).

2. Definição da Comunicação Remota 
- Escolher entre sockets TCP/UDP, RPC clássico ou gRPC. 
- Mostrar os motivos da escolha.

3. Diagrama Arquitetural 
- Diagramar a arquitetura com: 
  - Componentes 
  - Fluxo de comunicação 
  - Padrões e protocolos usados 
  - Detalhes de segurança se aplicável

4. Implementação Técnica (mínima obrigatória) 
- Implementar ao menos: 
  - 1 servidor central 
  - 2 clientes que se comuniquem simultaneamente 
  - 1 troca de dado funcional (mensagem, pedido, etc.) entre cliente e servidor 
- A comunicação pode ser feita via: 
  - Sockets TCP ou UDP 
  - RPC com Java RMI ou Pyro5 
  - gRPC com Protobuf (em Python, Go, Java, etc.)

5. Documentação Técnica 
- Explicação de: 
  - Modelo escolhido 
  - Protocolo de comunicação e bibliotecas usadas 
  - Estratégias de sincronização e segurança (TLS, autenticação) 
  - Desafios enfrentados e como foram resolvidos

## Ferramentas

- Java, Spring.

# 📌 Solução Proposta

## 🧩 Desafio Escolhido:

Sistema de pedidos e pagamentos para quiosques autônomos de praça de alimentação.

### 1. 🏗️ Escolha e Justificativa do Modelo Arquitetural
✅ **Modelo: Cliente-Servidor com Centralização em Nuvem**

- Clientes: aplicativos móveis que fazem pedidos.
- Servidor: Responsável por processar os pedidos, registrar pagamentos e atualizar a fila de produção.

🔧 **Justificativa Técnica**

### 2. 🔌 Definição da Comunicação Remota
✅ **Protocolo: gRPC com Protocol Buffers**

- Suporte nativo em Java, eficiente e moderno.
- Permite serialização binária (mais rápida que JSON/XML).
- Suporte a múltiplas linguagens e fácil integração futura com apps Android/iOS.

🔧 **Justificativa Técnica**

### 3. 🖼️ Diagrama Arquitetural

```
+----------------+        gRPC         +----------------------+         JDBC         +-----------------+
| Quiosque 1     | <-----------------> | Servidor Central     | <------------------> | Banco de Dados  |
| (Cliente gRPC) |                     | (Java + Spring Boot) |                      | (PostgreSQL)    |
+----------------+                     +---------------------+                       +-----------------+

+----------------+
| Quiosque 2     |
| (Cliente gRPC) |
+----------------+
```

📋 **Componentes**
- **Clientes (Quiosques)**: Aplicação Java que envia pedidos.
- **Servidor Central**: Aplicação Spring Boot que recebe e gerencia pedidos.
- **Banco de Dados**: PostgreSQL para armazenar pedidos e status.

🔐 **Segurança**
- Autenticação básica via tokens.
- Possibilidade de usar TLS no canal gRPC.

### 4. 💻 Implementação Técnica (Mínima Obrigatória)
🎯 **Componentes a serem desenvolvidos:**
- **Servidor gRPC Java:**
  - Recebe pedidos (`fazerPedido`)
  - Responde com status (`confirmarPedido`)

- **2 Clientes Java:**
  - Enviam pedidos simultaneamente
  - Recebem confirmação do servidor
 
💡 **Exemplos de métodos no `.proto`:**
```
service PedidoService {
  rpc FazerPedido (PedidoRequest) returns (PedidoResponse);
}

message PedidoRequest {
  string nomeProduto = 1;
  int32 quantidade = 2;
  string formaPagamento = 3;
}

message PedidoResponse {
  string status = 1;
  string tempoEstimado = 2;
}
```

### 5. 📚 Documentação Técnica (Resumo)
📌 Modelo Escolhido
- Cliente-Servidor com centralização em nuvem.

📌 Protocolo e Bibliotecas
- gRPC com Protobuf
- Java com Spring Boot
- Banco: PostgreSQL

📌 Estratégias de Sincronização e Segurança
- Sincronização garantida pela atomicidade dos métodos gRPC.
- Segurança por meio de autenticação básica (ex: tokens) e possíveis certificados TLS.

📌 Desafios Enfrentados
- Aprendizado e configuração do gRPC em Java.
- Serialização correta de mensagens .proto.
- Conexões simultâneas com tratamento de concorrência (threads).

## ✅ Etapas sugeridas para o grupo
1. Definir o `.proto` com métodos principais.
2. Gerar o stub do servidor e clientes via plugin gRPC do Maven.
3. Implementar o servidor Spring Boot com integração ao banco de dados.
4. Criar dois clientes que simulam pedidos simultâneos.
5. Testar a troca de mensagens e documentar a arquitetura e desafios.

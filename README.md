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

# Solução Proposta

## Desafio Escolhido:

Sistema de pedidos e pagamentos para quiosques autônomos de praça de alimentação.

### 1. Escolha e Justificativa do Modelo Arquitetural
**Modelo: Cliente-Servidor com Centralização em Nuvem**

- Clientes: aplicativos móveis que realizam os pedidos e computador do Quiosque para manipular estoques, pedidos e vendas.
- Servidor: responsável por armazenar os pedidos, vendas, estoque e pagamentos, além de oferecer as operações que os Clientes podem executar.

**Justificativa Técnica**

  Nossa escolha pelo modelo cliente-servidor foi uma decisão técnica para ter mais controle e segurança. Com isso, os quiosques têm uma alta dependência do servidor central, o que é bom para garantir que o cardápio e os preços estejam sempre iguais para todos. Isso significa que a escalabilidade do sistema, ou seja, a capacidade de aguentar mais quiosques ou mais pedidos, depende totalmente da potência do servidor; para crescer, o jeito mais fácil é simplesmente deixar o servidor mais forte. O ponto negativo que aceitamos com esse modelo é a latência, ou seja, uma pequena demora na resposta toda vez que o quiosque precisa conversar com o servidor, mas essa troca valeu a pena para ter um sistema mais seguro e muito mais fácil de atualizar e gerenciar a partir de um só lugar.

### 2. Definição da Comunicação Remota
**Protocolo: gRPC com Protocol Buffers**

- Suporte nativo em Java, eficiente e moderno.
- Permite serialização binária (mais rápida que JSON/XML).
- Suporte a múltiplas linguagens e fácil integração futura com apps Android/iOS.

**Justificativa Técnica**

  Nós escolhemos usar o gRPC porque, além de ser muito mais rápido e eficiente, ele torna o desenvolvimento mais fácil e direto. Com as outras opções, como os sockets, teríamos que construir toda a lógica de comunicação do zero, o que é bem mais difícil e demorado. O gRPC cuida de toda essa parte complicada para nós e funciona com um "manual de instruções" (o arquivo .proto) que cria regras claras de como o cliente e o servidor devem conversar. Isso evitou muita dor de cabeça com erros de integração e permitiu que nosso time trabalhasse melhor. No fim das contas, essa organização e a forma otimizada como o gRPC se comunica resultam em um sistema com respostas muito mais rápidas para o usuário.

### 3. Diagrama Arquitetural

```
+--------+                     +------------+
| Tablet |                     | Computador |
+--------+                     +------------+
     ^                               ^
     |                               |
     |                               |
     ---------------------------------
         |
         |
         V
+-----------------+        gRPC         +----------+
| Quiosque        | <-----------------> | Service  |
| (Servidor gRPC) |                     | (Java)   |
+-----------------+                     +----------+
                                             ^
                                             |
      _______________________________________|_______________________________________
      |                                      |                                      |
      |                                      |                                      |
+-----------+                          +-----------+                          +-----------+
| Pedido    |                          | Produto   |                          | Venda     |
+-----------+                          +-----------+                          +-----------+
```

**Componentes**
- **Servidor gRPC (Quiosques)**: Aplicação Java que envia pedidos.
- **Service**: Aplicação Java que recebe e gerencia pedidos.
- **Pedido/Produto/Venda**: Estrutura com as caracteristicas especificas para que o objeto seja criado com base na classe.

### 4. Implementação Técnica
- **Servidor gRPC Java:**
  - Adicionar Produtos (`adicionarProduto`)
  - Remover Produto (`removerProduto`)
  - Visualizar Vendas (`visualizarVendas`)
  - Visualizar Pedidos (`visualizarPedidos`)
  - Visualizar Produtos (`visualizarProdutos`)
  - Finalizar Pedidos (`finalizarPedido`)
  - Realizar Pedido (`realizarPedido`)

- **2 Clientes Java:**
  - Visualizam produtos
  - Realizam pedidos (Em caso de envios simultâneos, o gRPC cria uma fila, não ocorre erro) 
  - Recebem confirmação do servidor a cada comunicação via gRPC

### 5. Documentação Técnica
Modelo Escolhido
- Cliente-Servidor

Protocolo e Bibliotecas
- gRPC com Protobuf
- Java com Spring Boot

Estratégias de Sincronização 
- Sincronização garantida pela atomicidade dos métodos gRPC.

Desafios Enfrentados
- Aprendizado e configuração do gRPC em Java.
- Serialização correta de mensagens .proto.

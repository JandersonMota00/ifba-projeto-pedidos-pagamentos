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

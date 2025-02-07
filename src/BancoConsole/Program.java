package BancoConsole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import BancoConsole.models.Banco;
import BancoConsole.models.Conta;
import shared.Helpers;

public class Program {
    private static final Banco bancoConsole = new Banco();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (scanner) {
            abrirMenuPrincipal();
        }
    }

    private static void abrirMenuPrincipal() {
        ArrayList<String> mensagens = new ArrayList<String>();
        while (true) {
            Helpers.Console.clear();
            System.out.println("Banco Console - Home");
            System.out.println();
            if (mensagens.size() > 0) {
                for (String mensagem : mensagens) {
                    System.out.println("\t - " + mensagem);
                }
                System.out.println();
                mensagens.clear();
            }
            System.out.println("\t[0] Sair");
            System.out.println("\t[1] Criar Conta");
            System.out.println("\t[2] Acessar Conta");
            System.out.print("\n\t> ");

            switch (scanner.nextLine()) {
                case "0":
                    Helpers.Console.clear();
                    System.out.println("\nObrigado por acessar o Banco Console.\n");
                    System.exit(0);
                    break;

                case "1":
                    Helpers.Console.clear();
                    System.out.print("Agencia: ");
                    String agencia = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    Conta newConta = bancoConsole.registrarConta(agencia, nome);
                    Helpers.Console.clear();
                    String mensagem = new StringBuilder()
                            .append("Conta criada com sucesso!")
                            .append("\n\t\tSeus dados sao:")
                            .append("\n\t\t\tNumero da Conta: ").append(newConta.getNumero())
                            .append("\n\t\t\tAgencia: ").append(newConta.getAgencia())
                            .append("\n\t\t\tProprietario: ").append(newConta.getNomeCliente())
                            .append("\n\t\t\tSaldo: ").append(newConta.getSaldo())
                            .toString();
                    mensagens.add(mensagem);
                    break;

                case "2":
                    Helpers.Console.clear();
                    System.out.print("Numero da Conta: ");

                    Integer numeroConta = Helpers.Parsers.tryParseInteger(scanner.nextLine());
                    if (numeroConta == null) {
                        mensagens.add("Numero de conta invalido. Por favor, tente novamente.");
                        break;
                    }

                    Optional<Conta> conta = bancoConsole.getContaByNumero(numeroConta);
                    if (!conta.isPresent()) {
                        mensagens.add("Conta nao foi encontrada. Por favor, tente novamente.");
                        break;
                    }

                    abrirMenuConta(conta.get());
                    break;

                default:
                    mensagens.add("Opcao invalida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private static void abrirMenuConta(Conta conta) {
        ArrayList<String> mensagens = new ArrayList<String>();
        while (true) {
            Helpers.Console.clear();
            System.out.println("Banco Console - Conta");
            System.out.println();
            if (mensagens.size() > 0) {
                for (String mensagem : mensagens) {
                    System.out.println("\t - " + mensagem);
                }
                System.out.println();
                mensagens.clear();
            }
            System.out.println("\t[0] Voltar");
            System.out.println("\t[1] Visualizar Dados da Conta");
            System.out.println("\t[2] Realizar Deposito");
            System.out.println("\t[3] Realizar Saque");
            System.out.print("\n\t> ");

            switch (scanner.nextLine()) {
                case "0":
                    return;

                case "1":
                    Helpers.Console.clear();
                    System.out.println("Banco Console - Dados da Conta - " + conta.getNumero());
                    System.out.println();
                    System.out.println("\tNumero da Conta: " + conta.getNumero());
                    System.out.println("\tAgencia: " + conta.getAgencia());
                    System.out.println("\tProprietario: " + conta.getNomeCliente());
                    System.out.println("\tSaldo: " + conta.getSaldo());
                    System.out.println();
                    System.out.println("\t[*] Voltar");
                    System.out.print("\n\t> ");
                    scanner.nextLine();
                    break;

                case "2":
                    Helpers.Console.clear();
                    System.out.print("Valor do Deposito: ");
                    Integer valorDeposito = Helpers.Parsers.tryParseInteger(scanner.nextLine());
                    if (valorDeposito == null) {
                        mensagens.add("Valor invalido. Por favor, tente novamente.");
                        break;
                    }
                    conta.realizarDeposito(BigDecimal.valueOf(valorDeposito));
                    mensagens.add("Deposito realizado com sucesso. A conta agora tem um saldo de " + conta.getSaldo());
                    break;

                case "3":
                    Helpers.Console.clear();
                    System.out.print("Valor do Saque: ");
                    Integer valorSaque = Helpers.Parsers.tryParseInteger(scanner.nextLine());
                    if (valorSaque == null) {
                        mensagens.add("Valor invalido. Por favor, tente novamente.");
                        break;
                    }

                    try {
                        conta.realizarSaque(BigDecimal.valueOf(valorSaque));
                    } catch (Exception e) {
                        mensagens.add("Falha no saque.\n\t\t" + e.getMessage());
                        break;
                    }

                    mensagens.add("Saque realizado com sucesso. A conta agora tem um saldo de " + conta.getSaldo());
                    break;

                default:
                    mensagens.add("Opcao invalida. Por favor, tente novamente.");
                    break;
            }
        }
    }
}
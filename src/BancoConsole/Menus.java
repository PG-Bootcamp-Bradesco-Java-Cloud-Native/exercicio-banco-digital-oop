package BancoConsole;

import java.util.Optional;

import BancoConsole.models.Banco;
import BancoConsole.models.Conta;
import shared.Helpers;
import shared.MenuApp.MenuNode;
import shared.MenuApp.MenuApp;

public class Menus {
    private static MenuNode criarContaMenu = new MenuNode("Criar Conta",
            (MenuApp app) -> {
                Helpers.Console.clear();
                System.out.print("Agencia: ");
                String agencia = app.scan.nextLine();
                System.out.print("Nome: ");
                String nome = app.scan.nextLine();
                Conta newConta = Banco.registrarConta(agencia, nome);
                Helpers.Console.clear();
                String mensagem = new StringBuilder()
                        .append("Conta criada com sucesso!")
                        .append("\n\t\tSeus dados sao:")
                        .append("\n\t\t\tNumero da Conta: ").append(newConta.getNumero())
                        .append("\n\t\t\tAgencia: ").append(newConta.getAgencia())
                        .append("\n\t\t\tProprietario: ").append(newConta.getNomeCliente())
                        .append("\n\t\t\tSaldo: ").append(newConta.getSaldo())
                        .toString();
                app.messages.add(mensagem);
                return false;
            });

    private static MenuNode acessarContaMenu = new MenuNode("Acessar Conta",
            (MenuApp app) -> {
                Helpers.Console.clear();
                System.out.print("Numero da Conta: ");
                Integer numeroConta = Helpers.Parsers.tryParseInteger(app.scan.nextLine());
                if (numeroConta == null) {
                    app.messages.add("Numero de conta invalido. Por favor, tente novamente.");
                    return false;
                }
                Optional<Conta> conta = Banco.getContaByNumero(numeroConta);
                if (!conta.isPresent()) {
                    app.messages.add("Conta nao foi encontrada. Por favor, tente novamente.");
                    return false;
                }
                Banco.setContaAtiva(conta.get());
                return true;
            }).withChildNode("Visualizar Dados da Conta",
                    (MenuApp app) -> {
                        Helpers.Console.clear();
                        System.out.println("Banco Console - Dados da Conta");
                        System.out.println();
                        System.out.println("\tNumero da Conta: " + Banco.getContaAtiva().getNumero());
                        System.out.println("\tAgencia: " + Banco.getContaAtiva().getAgencia());
                        System.out.println("\tProprietario: " + Banco.getContaAtiva().getNomeCliente());
                        System.out.println("\tSaldo: " + Banco.getContaAtiva().getSaldo());
                        System.out.println();
                        System.out.println("\t[*] Voltar");
                        System.out.print("\n> ");
                        app.scan.nextLine();
                        return false;
                    })
            .withChildNode("Realizar Deposito",
                    (MenuApp app) -> {
                        Helpers.Console.clear();
                        System.out.print("Valor do Deposito: ");
                        Integer valorDeposito = Helpers.Parsers.tryParseInteger(app.scan.nextLine());
                        try {
                            Banco.getContaAtiva().realizarDeposito(valorDeposito);
                        } catch (Exception e) {
                            app.messages.add("Falha no deposito.\n\t\t" + e.getMessage());
                            return false;
                        }
                        app.messages.add("Deposito realizado com sucesso. A conta agora tem um saldo de "
                                + Banco.getContaAtiva().getSaldo() + ".");
                        return false;
                    })
            .withChildNode(
                    "Realizar Saque", (MenuApp app) -> {
                        Helpers.Console.clear();
                        System.out.print("Valor do Saque: ");
                        Integer valorSaque = Helpers.Parsers.tryParseInteger(app.scan.nextLine());
                        try {
                            Banco.getContaAtiva().realizarSaque(valorSaque);
                        } catch (Exception e) {
                            app.messages.add("Falha no saque.\n\t\t" + e.getMessage());
                            return false;
                        }
                        app.messages.add(
                                "Saque realizado com sucesso. A conta agora tem um saldo de "
                                        + Banco.getContaAtiva().getSaldo() + ".");
                        return false;
                    });

    public static MenuNode homeMenu = new MenuNode("Banco Console - Home")
            .withChildNode(criarContaMenu)
            .withChildNode(acessarContaMenu);
}

package BancoConsole;

import java.util.Optional;

import BancoConsole.models.Banco;
import BancoConsole.models.Conta;
import BancoConsole.models.ContaCorrente;
import BancoConsole.models.ContaPoupanca;
import shared.Helpers;
import shared.MenuApp.MenuNode;
import shared.MenuApp.MenuApp;

public class Menus {
    private static MenuNode criarContaMenu = new MenuNode("Criar Conta", (MenuApp app) -> {
        Helpers.Console.clear();

        System.out.print("Nome: ");
        String nome = app.scan.nextLine();

        if (nome == null || nome.isEmpty() || nome.isBlank()) {
            app.messages.add("Falha ao criar conta. Nome não pode ser vazio.");
            return false;
        }

        System.out.print("Tipo de conta:\n\t[0] Poupanca\n\t[1] Corrente\n> ");
        Integer tipoConta = Helpers.Parsers.tryParseInteger(app.scan.nextLine());

        Conta newConta;

        if (tipoConta == null) {
            app.messages.add("Falha ao criar conta. Tipo selecionado é inválido.");
            return false;
        }

        switch (tipoConta) {
            case 0:
                newConta = new ContaPoupanca(nome);
                break;

            case 1:
                newConta = new ContaCorrente(nome);
                break;

            default:
                app.messages.add("Falha ao criar conta. Tipo selecionado é inválido.");
                return false;
        }

        Banco.registrarConta(newConta);

        app.messages.add(new StringBuilder()
                .append("Conta criada com sucesso!")
                .append("\n\t\tSeus dados sao:")
                .append(newConta.toString().replace("\n", "\n\t\t\t"))
                .toString());

        return false;
    });

    private static MenuNode acessarContaMenu = new MenuNode("Acessar Conta", (MenuApp app) -> {
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
    }).withChildNode("Visualizar Dados da Conta", (MenuApp app) -> {
        Helpers.Console.clear();
        System.out.println(new StringBuilder("Dados da Conta:\n")
                .append(Banco.getContaAtiva().toString().replace("\n", "\n\t"))
                .append("\n\n\t[*] Voltar")
                .append("\n> "));
        app.scan.nextLine();
        return false;
    }).withChildNode("Realizar Deposito", (MenuApp app) -> {
        Helpers.Console.clear();
        System.out.print("Valor do Deposito: ");
        Double valorDeposito = Helpers.Parsers.tryParseDouble(app.scan.nextLine());
        try {
            Banco.getContaAtiva().realizarDeposito(valorDeposito);
        } catch (Exception e) {
            app.messages.add("Falha no deposito.\n\t\t" + e.getMessage());
            return false;
        }
        app.messages.add("Deposito realizado com sucesso. A conta agora tem um saldo de "
                + Banco.getContaAtiva().getSaldo() + ".");
        return false;
    }).withChildNode("Realizar Saque", (
            MenuApp app) -> {
        Helpers.Console.clear();
        System.out.print("Valor do Saque: ");
        Double valorSaque = Helpers.Parsers.tryParseDouble(app.scan.nextLine());
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

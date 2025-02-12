package BancoConsole;

import shared.MenuApp.MenuApp;
import shared.MenuApp.Configuration.ActionLabelConfiguration;
import shared.MenuApp.Configuration.MenuAppConfiguration;
import shared.MenuApp.Configuration.MessagesConfiguration;

public class Program {
    public static void main(String[] args) {

        MenuAppConfiguration appConfiguration = new MenuAppConfiguration(
                new ActionLabelConfiguration(
                        "Sair",
                        "Voltar"),
                new MessagesConfiguration(
                        "Opção inválida selecionada. Por favor, tente novamente.",
                        "Obrigado por acessar o Banco Console."));

        MenuApp app = new MenuApp(Menus.homeMenu, appConfiguration);

        app.run();
    }
}
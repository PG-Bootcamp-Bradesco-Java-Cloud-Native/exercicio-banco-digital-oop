package shared.MenuApp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import shared.Helpers;
import shared.MenuApp.Configuration.MenuAppConfiguration;

public class MenuApp {

    private Stack<MenuNode> nodeHistory = new Stack<>();
    private final MenuAppConfiguration configuration;
    public final ArrayDeque<String> messages = new ArrayDeque<>();
    public final Scanner scan = new Scanner(System.in);
    public static MenuApp instance;

    public MenuApp(MenuNode rootMenuNode) {
        this.configuration = MenuAppConfiguration.defaultConfiguration;
        this.nodeHistory.add(rootMenuNode);
        MenuApp.instance = this;
    }

    public MenuApp(MenuNode rootMenuNode, MenuAppConfiguration configuration) {
        this.configuration = configuration;
        this.nodeHistory.add(rootMenuNode);
        MenuApp.instance = this;
    }

    public void run() {
        while (true) {
            render();
            Integer input = prompt();
            MenuNode[] menuNodes = getMenuNodes();
            if (input == null || input < 0 || input >= menuNodes.length) {
                messages.add(configuration.messagesConfiguration().invalidOptionMessage());
                continue;
            }
            MenuNode toNode = menuNodes[input];
            navigate(toNode);
        }
    }

    private MenuNode getRootNode() {
        return nodeHistory.get(0);
    }

    private MenuNode getCurrentNode() {
        return nodeHistory.peek();
    }

    private MenuNode[] getMenuNodes() {
        MenuNode currentNode = getCurrentNode();
        MenuNode rootNode = getRootNode();
        ArrayList<MenuNode> menuNodes = new ArrayList<>(List.of(currentNode.getChildNodes()));

        if (currentNode != rootNode) {
            menuNodes.add(0,
                    new MenuNode(configuration.actionLabelConfiguration().returnActionLabel(), (MenuApp app) -> {
                        app.nodeHistory.pop();
                        return false;
                    }));
        }

        menuNodes.add(new MenuNode(configuration.actionLabelConfiguration().exitActionLabel(), (MenuApp app) -> {
            app.exit();
            return false;
        }));

        return menuNodes.toArray(new MenuNode[0]);

    }

    private void render() {
        Helpers.Console.clear();
        MenuNode currentNode = nodeHistory.peek();
        StringBuilder stringBuilder = new StringBuilder(currentNode.getTitle());

        if (!messages.isEmpty()) {
            stringBuilder.append("\n");
            while (!messages.isEmpty()) {
                stringBuilder.append("\n\t- ")
                        .append(messages.pop());
            }
        }
        stringBuilder.append("\n");

        MenuNode[] menuNodes = getMenuNodes();

        Integer i = menuNodes.length;
        for (MenuNode childNode : menuNodes) {
            stringBuilder.append("\n\t[")
                    .append(menuNodes.length - (i--))
                    .append("] ")
                    .append(childNode.getTitle());
        }

        stringBuilder.append("\n> ");

        System.out.print(stringBuilder.toString());
    }

    private Integer prompt() {
        Integer input;
        input = Helpers.Parsers.tryParseInteger(scan.nextLine());
        return input;
    }

    private void navigate(MenuNode toNode) {
        if (!toNode.run()) {
            return;
        }
        this.nodeHistory.push(toNode);
    }

    private void exit() {
        scan.close();
        Helpers.Console.clear();
        System.out.println(configuration.messagesConfiguration().onExitMessage());
        System.exit(0);
    }
}

package shared.MenuApp.Configuration;

public record MessagesConfiguration(String invalidOptionMessage, String onExitMessage) {
    public static MessagesConfiguration defaultConfiguration = new MessagesConfiguration("Option selected is invalid.",
            null);
}

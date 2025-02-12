package shared.MenuApp.Configuration;

public record MenuAppConfiguration(ActionLabelConfiguration actionLabelConfiguration,
                MessagesConfiguration messagesConfiguration) {
        public static MenuAppConfiguration defaultConfiguration = new MenuAppConfiguration(
                        ActionLabelConfiguration.defaultConfiguration,
                        MessagesConfiguration.defaultConfiguration);
}

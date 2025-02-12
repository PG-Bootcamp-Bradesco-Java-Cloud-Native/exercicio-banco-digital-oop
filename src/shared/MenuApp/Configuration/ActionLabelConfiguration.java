package shared.MenuApp.Configuration;

public record ActionLabelConfiguration(String exitActionLabel, String returnActionLabel) {
    public static ActionLabelConfiguration defaultConfiguration = new ActionLabelConfiguration("Exit", "Return");
}

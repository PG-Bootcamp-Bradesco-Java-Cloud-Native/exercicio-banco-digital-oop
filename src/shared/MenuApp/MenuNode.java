package shared.MenuApp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MenuNode {
    private String title;
    private Function<MenuApp, Boolean> action;
    private List<MenuNode> childNodes = new ArrayList<MenuNode>();

    public MenuNode(String title) {
        this(title, (MenuApp app) -> {
            return true;
        });
    }

    public MenuNode(String title, Function<MenuApp, Boolean> action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }

    public MenuNode[] getChildNodes() {
        return this.childNodes.toArray(new MenuNode[0]);
    }

    public MenuNode withChildNode(String title, Function<MenuApp, Boolean> action) {
        return this.withChildNode(new MenuNode(title, action));
    }

    public MenuNode withChildNode(MenuNode node) {
        this.addChild(node);
        return this;
    }

    public void addChild(MenuNode node) {
        this.childNodes.add(node);
    }

    public boolean run() {
        return this.action.apply(MenuApp.instance);
    }

}

/**
 * Reusable component for showing CLI menus
 */
public class Menu {
    // Menu Configuration Fields
    // (marked `final` because they only need to be set once)
    private final String name;
    private final String[] choices;

    public Menu(String name, String[] choices) {
        this.name = name;
        this.choices = choices;
    }
}

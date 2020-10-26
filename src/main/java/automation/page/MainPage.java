package automation.page;

public abstract class MainPage {

    protected String name = "Main";

    public void navigate() {
        System.out.println("navigate to " + name);
    }

    public void simpleSearch() {
        System.out.println("[" + name + "] simple search");
    }

    public void searchWithSyntax() {
        System.out.println("[" + name + "] search with syntax");
    }

    public void pictureSearch() {
        System.out.println("[" + name + "] picture search");
    }

    public boolean isOnThisPage() {
        System.out.println("[" + name + "] check is on " + name + " or not");
        return true;
    }

    public void goToPictureSearchTab() {
        System.out.println("[" + name + "] go to picture search tab");
    }

}

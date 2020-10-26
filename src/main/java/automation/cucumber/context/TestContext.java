package automation.cucumber.context;

import automation.cucumber.background.Background;
import automation.page.MainPage;

public class TestContext {

    private static TestContext instance = null;

    private String buildNum;
    private MainPage page;
    private Background background;

    private TestContext() {

    }

    public static TestContext getInstance() {
        if (instance == null) instance = new TestContext();
        return instance;
    }

    public String getBuildNum() { return buildNum; }
    public MainPage getPage() { return page; }
    public Background getBackground() { return background; }

    public void setBuildNum(String buildNum) { this.buildNum = buildNum; }
    public void setPage(MainPage page) { this.page = page; }
    public void setBackground(Background background) { this.background = background; }

}

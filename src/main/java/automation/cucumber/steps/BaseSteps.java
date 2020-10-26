package automation.cucumber.steps;

import automation.cucumber.background.Background;
import automation.cucumber.context.TestContext;
import automation.page.MainPage;

public abstract class BaseSteps {

    protected TestContext context;
    protected MainPage page;
    protected Background background;

    public BaseSteps() {
        context = TestContext.getInstance();
        page = context.getPage();
        background = context.getBackground();
    }

}

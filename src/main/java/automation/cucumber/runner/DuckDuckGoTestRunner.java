package automation.cucumber.runner;

import automation.cucumber.background.Background;
import automation.cucumber.context.TestContext;
import automation.page.DuckDuckGoPage;
import automation.page.MainPage;
import automation.page.YahooPage;
import automation.utilities.Utilities;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features/SearchEngineTest",
        plugin={"pretty",
                "html:build/test-results/cucumber/DuckDuckGoTest",
                "junit:build/test-results/junit/regression.DuckDuckGoTest.xml"},
        glue={"automation.cucumber.steps.duckduckgo"},
        monochrome=true,
        strict=true)
public class DuckDuckGoTestRunner {

    @BeforeClass
    public static void setUp() {

        System.out.println("===> BeforeClass setUp");

        String buildNum = Utilities.getProperty("buildNum", "build");
        MainPage page = new DuckDuckGoPage();
        Background background = new Background() {
            @Override
            public boolean isMet() {
                if (page != null && ((DuckDuckGoPage) page).isOnThisPage()) return true;
                return false;
            }
        };

        TestContext context = TestContext.getInstance();

        context.setBuildNum(buildNum);
        context.setPage(page);
        context.setBackground(background);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("===> AfterClass tearDown");
    }

}

package automation.cucumber.runner;

import automation.cucumber.background.Background;
import automation.cucumber.context.TestContext;
import automation.page.GooglePage;
import automation.page.MainPage;
import automation.utilities.Utilities;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features/SearchEngineTest",
        plugin={"pretty",
                "html:build/test-results/cucumber/GoogleTest",
                "junit:build/test-results/junit/regression.GoogleTest.xml"},
        glue={"automation.cucumber.steps.google"},
        monochrome=true,
        strict=true)
public class GoogleTestRunner {

    @BeforeClass
    public static void setUp() {

        System.out.println("===> BeforeClass setUp");

        String buildNum = Utilities.getProperty("buildNum", "build");
        MainPage page = new GooglePage();
        Background background = new Background() {
            @Override
            public boolean isMet() {
                if (page != null && ((GooglePage) page).isOnThisPage()) return true;
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

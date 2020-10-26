package automation.cucumber.steps.yahoo;

import automation.cucumber.steps.BaseSteps;
import automation.page.YahooPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class YahooTestSteps extends BaseSteps {


    @When("navigate to page")
    public void navigate_to_page() {
        if (background.isMet()) return;
        ((YahooPage) page).navigate();
    }

    @Given("on search engine page")
    public void on_search_engine_page() {
        ((YahooPage) page).isOnThisPage();
    }

    @Then("search for keywords")
    public void search_for_keywords() {
        ((YahooPage) page).simpleSearch();
    }

    @Then("search for keywords with syntax")
    public void search_for_keywords_with_syntax() {
        ((YahooPage) page).searchWithSyntax();
    }

    @Then("go to picture search tab")
    public void go_to_picture_search_tab() {
        ((YahooPage) page).goToPictureSearchTab();
    }

    @Then("upload and search for picture")
    public void upload_and_search_for_picture() {
        ((YahooPage) page).pictureSearch();
    }

}

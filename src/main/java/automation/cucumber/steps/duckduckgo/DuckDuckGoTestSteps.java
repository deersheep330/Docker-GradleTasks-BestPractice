package automation.cucumber.steps.duckduckgo;

import automation.cucumber.steps.BaseSteps;
import automation.page.DuckDuckGoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DuckDuckGoTestSteps extends BaseSteps {


    @When("navigate to page")
    public void navigate_to_page() {
        if (background.isMet()) return;
        ((DuckDuckGoPage) page).navigate();
    }

    @Given("on search engine page")
    public void on_search_engine_page() {
        ((DuckDuckGoPage) page).isOnThisPage();
    }

    @Then("search for keywords")
    public void search_for_keywords() {
        ((DuckDuckGoPage) page).simpleSearch();
    }

    @Then("search for keywords with syntax")
    public void search_for_keywords_with_syntax() {
        ((DuckDuckGoPage) page).searchWithSyntax();
    }

    @Then("go to picture search tab")
    public void go_to_picture_search_tab() {
        ((DuckDuckGoPage) page).goToPictureSearchTab();
    }

    @Then("upload and search for picture")
    public void upload_and_search_for_picture() {
        ((DuckDuckGoPage) page).pictureSearch();
    }

}

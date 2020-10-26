Feature: Search Engine Test

  Background: On Search Engine
    When navigate to page

  Scenario: Test Simple Search
    Given on search engine page
    Then search for keywords

  Scenario: Test Search With Syntax
    Given on search engine page
    Then search for keywords with syntax

  Scenario: Test Picture Search
    Given on search engine page
    Then go to picture search tab
    Then upload and search for picture

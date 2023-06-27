Feature: Amazon Search

  Scenario: Filter and sort Sony TV results
    Given I am on the Amazon Australia website
    When I search for "Sony TV"
    And I apply filters like:
      | Filter Type  | Option      |
      | Brand        | Sony        |
      | Resolution   | 4K          |
      | Model Year   | 2022        |
    And I sort the results by price 
    Then I log the price of the lowest ticketed item
    And I log the price of the highest ticketed item
     And close browser

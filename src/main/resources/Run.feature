Feature: feature

  Scenario: scenario
    Given start the application
    When user login
    Then user clicks the button "Admin"
    Then the Text "System Users" should be visible
    Then wait 1 seconds
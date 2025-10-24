# login.feature
Feature: feature for login functionality

  Scenario: Successful login with valid credentials

    Given I am on the DemoBlaze homepage
    When I click the "Log in" link
    And I enter valid "testuser" and "testpassword"
    And I click the "Log in" button
    Then I should see a welcome message with my username

  Scenario: Failed login with invalid credentials

    Given I am on the DemoBlaze homepage
    When I click the "Log in" link
    And I enter valid <username> and <password>
    And I click the "Log in" button
    Then I should see an error message

    Examples:
    | username | password |
    | baduser  | badpass  |

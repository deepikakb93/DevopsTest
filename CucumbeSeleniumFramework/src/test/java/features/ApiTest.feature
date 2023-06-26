Feature: Testing API

Scenario: Verify API response
Given the API endpoint "https://reqres.in/api/users?page=2"
When a GET request is sent
Then the response status code should be 200

Scenario: Verify API response does not have the first name Jack
Given the API endpoint "https://reqres.in/api/users?page=2"
When a GET request is sent
Then the response should not contain the name "Jack"

Scenario: Verify API response returns empty data array for page3
Given the API endpoint "https://reqres.in/api/users?page=3"
When a GET request is sent
Then the response should return empty data array
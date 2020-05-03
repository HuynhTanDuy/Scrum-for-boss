Feature: client can get list user
  Scenario: client makes call to GET /user
    When the client calls /user
    Then the client receives status code of 200
    
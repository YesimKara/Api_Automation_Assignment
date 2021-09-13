@apitesting
Feature: System should allow to read all users  using api end point "https://gorest.co.in/public/v1/users"

  Background: TC_001 and 002 backround
    Given use given api url "https://gorest.co.in/"


  Scenario Outline: POST-Create a new user
    Given  user set api endpoint "public/v1/users"
    And  User creates new data user with request body "<Name>","<Gender>","<Email>","<Status>"
    Then validete the status code is 201
    And validate the user id is not null
    And validate user name is "<Name>"
    And validate user gender is "<Gender>"
    And validate user email is "<Email>"
    And validate user status is "<Status>"

    Examples:
      | Name     | Gender | Email            | Status |
      | John1 Doe1 | male   | john20@gmail.com | active |


  Scenario Outline: PUT-update user data
    Given  user set api endpoint "public/v1/users/""<UserId>"
    And  update the user with "<UserId>" using request body "<Name>","<Gender>","<Email>","<Status>"
    Then validate the status code is 200
    And validate the user id is not null
    And validate user name is "<Name>"
    And validate user gender is "<Gender>"
    And validate user email is "<Email>"
    And validate user status is "<Status>"

    Examples:
      | Name      | Gender | Email            | Status | UserId |
      | John3 Doe | female | john20@gmail.com | active | 1802   |


  Scenario Outline: PATCH-create one comment for given  user data
    Given  user set api endpoint "public/v1/users/""<UserId>"
    And  update using patch the user with "<UserId>" using request body "<Name>","<Gender>","<Email>","<Status>"
    Then validate the status code is 200
    And validate the user id is not null
    And validate user name is "<Name>"
    And validate user gender is "<Gender>"
    And validate user email is "<Email>"
    And validate user status is "<Status>"

    Examples:
      | Name       | Gender | Email          | Status | UserId |
      | John25 Doe | female | john20@gmail.com | active | 1802   |

  Scenario: DELETE-Delete user
    Given user set endpoint "public-api/users/" for "1802" userid
    And use DELETE request to delete spesific user
    Then validate the response status code is 204
    Then try to get the user data and validate response code is 404
    Then validate with response message "Resource not found"

  Scenario Outline: : Create a post with one comment
    Given user sets "<EndpointPost>" post
    And create a post with given userId and create one "<comment>" and "<title>"
    Then user sets "<EndPointComment>" post and create one "<comment>" using "<userId>","<name>","<email>"
    And verify that comment is created for given "<name>" and "<email>"
    Examples:
      | EndpointPost                   | EndPointComment                   | userId | comment       | title       | name       | email          |
      | public/v1/users/<userId>/posts | public/v1/posts/<userId>/comments | 1772   | first comment | first title | John25 Doe | john@gmail.com |

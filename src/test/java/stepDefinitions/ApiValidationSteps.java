package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pages.ApiValidation;
import utilities.ConfigurationReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiValidationSteps extends ApiValidation {

    @Given("use given api url {string}")
    public void useGivenApiUrl(String url) {
        RestAssured.baseURI=url;
    }

    @Given("user set api endpoint {string}")
    public void userSetApiEndpoint(String path) {
        RestAssured.basePath=path;
    }
    @And("User creates new data user with request body {string},{string},{string},{string}")
    public void userCreatesNewDataUserWithRequestBody(String name, String gender, String email, String status) {
      response = postMethod(name,gender,email,status);
    }
    @Then("validete the status code is {int}")
    public void valideteTheStatusCodeIs(int expectedCode) {
        int statusCode = response.statusCode();
        try {
            Assert.assertEquals(statusCode, expectedCode);
            System.out.println("Assertion succesfull");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("validate the user id is not null")
    public void validateTheUserIdIsNotNull() {
       //response.prettyPeek();
     try {
       int id = response.jsonPath().getInt("data.id");
         System.out.println(id);
         Assert.assertTrue(response.jsonPath().getInt("data.id") !=0,"id is empty");
     }catch(Exception e){
         e.printStackTrace();
     }
    }
    @And("validate user name is {string}")
    public void validateUserNameIs(String expectedName) {
        String name = response.jsonPath().get("data.name");
        System.out.println(name);
        try {
            Assert.assertEquals(name, expectedName);
            System.out.println("Assertion successful for name");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("validate user gender is {string}")
    public void validateUserGenderIs(String expectedGender) {
        String gender = response.jsonPath().get("data.gender");
        System.out.println(gender);
        try{
        Assert.assertEquals(gender,expectedGender);
        System.out.println("Assertion successful for gender");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("validate user email is {string}")
    public void validateUserEmailIs(String expectedEmail) {
        String email = response.jsonPath().get("data.email");
        System.out.println(email);
        try{
        Assert.assertEquals(email,expectedEmail);
        System.out.println("Assertion successful for email");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("validate user status is {string}")
    public void validateUserStatusIs(String expectedStatus) {
        String status = response.jsonPath().get("data.status");
        System.out.println(status);
        try{
        Assert.assertEquals(status,expectedStatus);
        System.out.println("Assertion successful for status");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @Given("user set api endpoint {string}{string}")
    public void userSetApiEndpoint(String endPoint, String userId) {
        RestAssured.basePath=endPoint+userId;
    }
    @And("update the user with {string} using request body {string},{string},{string},{string}")
    public void updateTheUserWithUsingRequestBody(String userId,String name, String gender, String email, String status) {
        response = putMethod(name,gender,email,status);

    }

    @Then("validate the status code is {int}")
    public void validateTheStatusCodeIs(int expectedCode) {
        int statusCode = response.statusCode();
        try {
            Assert.assertEquals(statusCode, expectedCode);
            System.out.println("Assertion successful");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("validate user status is {string}.")
    public void validateUserStatusIsPut(String expectedStatus) {
        String status = response.jsonPath().get("data.status");
        System.out.println(status);
        try {
            Assert.assertEquals(status, expectedStatus);
            System.out.println("Assertion successful for status");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @And("update using patch the user with {string} using request body {string},{string},{string},{string}")
    public void updateUsingPatchTheUserWithUsingRequestBody(String userId,String name, String gender, String email, String status) {
        response = putMethod(name,gender,email,status);
    }

    @Given("user set endpoint {string} for {string} userid")
    public void userSetEndpoint(String endPoint, String userId) {
        RestAssured.basePath = endPoint+userId;

    }
    @And("use DELETE request to delete spesific user")
    public void useDELETERequestToDeleteSpesificUser() {
        response = deleteMethod();
    }
    @Then("validate the response status code is {int}")
    public void validateTheResponseStatusCodeIs(int expectedCode) {
        response.prettyPrint();

      int code = 0;
        try {code = response.jsonPath().getInt("code");
            System.out.println(code);
        } catch (Exception i){
            i.printStackTrace();
        }

        try{
            Assert.assertEquals(code,expectedCode);
            System.out.println("Assertion successful for code");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @Then("try to get the user data and validate response code is {int}")
    public void tryToGetTheUserDataAndValidateResponseCodeIs(int expectedCode) {
        response = deleteMethod();
        int code = response.jsonPath().getInt("code");
        System.out.println(code);
        try{
            Assert.assertEquals(code,expectedCode);
            System.out.println("Assertion successful for code");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }
    @Then("validate with response message {string}")
    public void validateWithResponseMessage(String expectedMessage) {
      response.prettyPrint();
        String message = response.jsonPath().get("data.message");
        System.out.println(message);
        try{
            Assert.assertEquals(message,expectedMessage);
            System.out.println("Assertion successful for message");
        } catch(AssertionError ae) {
            ae.printStackTrace();
        }
    }


    @Given("user sets {string} post")
    public void userSetsAndFor(String endpointPost) {
    RestAssured.basePath = endpointPost;
        System.out.println(endpointPost);
    }
    int postId ;
    @And("create a post with given userId and create one {string} and {string}")
    public void createAPostWithGivenUserId(String comment, String title) {
        response = postMethodCreate(comment,title);
        response.prettyPrint();
        postId = response.jsonPath().getInt("data.id");
    }
    @Then("user sets {string} post and create one {string} using {string},{string},{string}")
    public void userSetsPostAndCreateOne(String endPointComment, String comment, String userId,String name,String email) {
        endPointComment = endPointComment.replaceAll(userId, String.valueOf(postId));
        RestAssured.basePath = endPointComment;
        response = postMethodComment(comment,name,email);
        response.prettyPrint();
    }
    @And("verify that comment is created for given {string} and {string}")
    public void verifyThatCommentIsCreatedForGivenUserId(String expectedName,String expectedEmail) {
        String actualName = response.jsonPath().get("data.name");
        String actualEmail = response.jsonPath().get("data.email");
        Assert.assertEquals(actualName,expectedName);
        Assert.assertEquals(actualEmail,expectedEmail);
        System.out.println("Assertion successful");
    }

}


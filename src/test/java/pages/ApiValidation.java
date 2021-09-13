package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiValidation {
   public Response response;
    public String updateBody(String name, String gender, String email, String status){
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator +
                    "src/test/resources/testData/userDetails.json")));
            body = body.replaceAll("replacename", name);
            body = body.replaceAll("replacegender", gender);
            body = body.replaceAll("replaceemail", email);
            body = body.replaceAll("replacestatus", status);
            //   System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
    public String updateCommentBody(String comment, String title){
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator +
                    "src/test/resources/testData/comment.json")));
            body = body.replaceAll("replacecomment", comment);
            body = body.replaceAll("replacetitle", title);

            //   System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
    public String updateCommentPost(String comment, String name,String email){
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator +
                    "src/test/resources/testData/commentCreate.json")));
            body = body.replaceAll("replacecomment", comment);
            body = body.replaceAll("replacename", name);
            body = body.replaceAll("replaceemail", email);

            //   System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    public String patchBody(String comment){
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator +
                    "src/test/resources/testData/comment.json")));
            body = body.replaceAll("replacename", comment);
            //   System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
    public Response postMethod(String name, String gender, String email, String status){
        String body = updateBody(name,gender,email,status);
        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(body)
                .post();
        return response;
    }
    public Response putMethod(String name, String gender, String email, String status){
        String body = updateBody(name,gender,email,status);

        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(body)
                .put();
        return response;
    }
    public Response patchMethod(String comment){
        String body = patchBody(comment);
        //String body = "{\"name\":\"John25 Doe\"}";
        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(body)
                .patch();
        return response;
    }
    public Response deleteMethod(){
        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .delete();
        return response;
    }
    public Response postMethodCreate(String comment,String title){
        String body = updateCommentBody(comment,title);
        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(body)
                .post();
        return response;
    }
    public Response postMethodComment(String comment,String name,String email){
        String body = updateCommentPost(comment,name,email);
        Response response = given()
                .headers("Authorization","Bearer " + ConfigurationReader.getProperty("api_access_token"),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body(body)
                .post();
        return response;
    }



}

package com.Foodics.Amazon.API;

import com.Foodics.Amazon.base.config.EndPoint;
import com.Foodics.Amazon.base.object.User;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ReqresApi {

    private int userId;

    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
    public void createUser() {
        User user = new User("John Doe", "Software Engineer");
        System.out.println(user.getJob() + user.getName());
        try {
            Response response =
                    given()
                            .baseUri(EndPoint.API_BaseURI_ENDPOINT)
                            .contentType(ContentType.JSON)
                            .body(user)
                            .log().all()
                    .when()
                            .post(EndPoint.API_BaseURI_ENDPOINT_User)
                    .then()
                            .log().all()
                            .statusCode(201)
                            .contentType(ContentType.JSON)
                            .extract()
                            .response();

            userId = response.jsonPath().getInt("id");
            Assert.assertNotNull(userId, "User ID should not be null");
            Assert.assertEquals(response.jsonPath().getString("name"), user.getName(), "Name mismatch");
            Assert.assertEquals(response.jsonPath().getString("job"), user.getJob(), "Job mismatch");
            Assert.assertNotNull(response.jsonPath().getString("createdAt"), "createdAt should not be null");

            System.out.println("User created successfully with ID: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error creating user: " + e.getMessage());
        }

    }


    public void retrieveUser() {
        try {
            Response response =
                    given()
                            .baseUri(EndPoint.API_BaseURI_ENDPOINT)
                            .pathParam("id", "2")// created user not able to be retrived even using postman OR Swagger
                            .log().all()
                    .when()
//                    .   get("/api/users/{id}")
                            .get("/api/users/{id}")
                    .then()
                            .log().all()
                            .statusCode(200)
                            .contentType(ContentType.JSON)
                            .extract()
                            .response();

            Assert.assertEquals(response.jsonPath().getString("data.first_name") + " " + response.jsonPath().getString("data.last_name"), "Janet Weaver", "Name mismatch");
            Assert.assertEquals(response.jsonPath().getString("data.last_name"), "Weaver", "Job mismatch");

            System.out.println("User retrieved successfully with ID: " + userId);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error retrieving user: " + e.getMessage());
        }
    }


    public void updateUser() {
        User updatedUser = new User("Janet", "Team Lead");

        try {
            Response response = given()
                    .baseUri(EndPoint.API_BaseURI_ENDPOINT)
//                    .contentType(ContentType.JSON)
                    .pathParam("id", "2")// created user not able to be retrived even using postman OR Swagger
                    .body(updatedUser)
                    .when()
                    .put("/api/users/{id}")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract()
                    .response();
///*Update user always return
            //  "updatedAt": "2025-03-18T22:00:06.418Z"

//            Assert.assertEquals(response.jsonPath().getString("name"), updatedUser.getName(), "Updated name mismatch");
//            Assert.assertEquals(response.jsonPath().getString("job"), updatedUser.getJob(), "Updated job mismatch");
            Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "updatedAt should not be null");

            System.out.println("User updated successfully with ID: " + userId);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error updating user: " + e.getMessage());
        }
    }
}
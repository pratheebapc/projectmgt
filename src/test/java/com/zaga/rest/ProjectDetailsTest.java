package com.zaga.rest;

import org.junit.jupiter.api.Test;

import com.zaga.model.entity.Currency;
import com.zaga.model.entity.ProjectDetails;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class ProjectDetailsTest {

    @Test
    void createProjectDetailsApiTest(){
        ProjectDetails projectDetails = new ProjectDetails(null,"","","","","","","","","","","","","","","","","","","","",null, null, null, null, null, Currency.EUR,"","","","");
        RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(projectDetails)
        .when().post("/zaga/projectManagement/createProjectDetails")
        .then().statusCode(200);
    }

    @Test
    void getProjectDetailsApiTest(){
        RestAssured.given()
        .when().get("/zaga/projectManagement/viewProjectDetails")
        .then().statusCode(200);
    }

    @Test
    void getProjectDetailsByIdTest(){
        String projectId = "555";
        RestAssured.given().contentType(ContentType.JSON).pathParam("projectId",projectId)
        .when().get("/zaga/projectManagement/viewProjectDetailsById/{projectId}")
        .then().statusCode(200);
    }
    void updateProjectDetailsApiTest(){
        // ProjectDetails projectDetails = new ProjectDetails(null,"", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        ProjectDetails projectDetails = new ProjectDetails(null,"", null, null, null, null, null, null, "3","",null, null, null, null, null, null, null,"",null, null, null, null, null, null, null,"",null, null, null, null, null);
        RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(projectDetails) //.pathParam("projectId", "3")
        .when().put("/zaga/projectManagement/updateProjectDetails")
        .then().statusCode(200);
    }

    @Test
    void deleteProjectDetailsApiTest(){
        String projectId ="2";
        RestAssured.given().contentType(ContentType.JSON).pathParam("projectId", projectId)
        .when().delete("/zaga/projectManagement/deleteProjectDetails/{projectId}")
        .then().statusCode(204);
}
}
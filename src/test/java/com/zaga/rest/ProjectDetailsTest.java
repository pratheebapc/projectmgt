package com.zaga.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import com.zaga.model.entity.Currency;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.ProjectType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectDetailsTest {
    private static MongodExecutable mongodExe;
    private static MongodProcess mongod;
    private static ObjectMapper mapper;
    private static String projectName;
    private ProjectDetails createResponse;

    @BeforeAll
    public static void setUp() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodConfig mongodConfig = MongodConfig.builder()
                .version(Version.Main.V6_0)
                .build();
        mongodExe = starter.prepare(mongodConfig);
        mongod = mongodExe.start();

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        projectName = "project-" + RandomStringUtils.randomAlphanumeric(10);
    }

    @AfterAll
    public static void tearDown() throws Exception {
        mongod.stop();
        mongodExe.stop();
    }

    private String creationResponse() {
        return createResponse.projectId;
    }

    @Test
    @Order(1)
    void createProjectDetailsApiTest() throws JsonProcessingException {
        int arbitraryStart = 5;
        LocalDate startDate = LocalDate.now().minusDays(arbitraryStart);
        LocalDate endDate = LocalDate.now();
        ProjectDetails projectDetails = ProjectDetails.builder().id(null)
            // employee details
            .employeeName("sharamua").employeeEmail("").employeeNumber("")
            .employeeId("").employeeRole("")
            // project details
            .projectAssignmentStatus(false).projectManager("")
            .projectName(projectName)
            // client details
            .clientName("").clientCountry("").clientTimezone("")
            .clientAddress("").clientEmail("")
            // miscellaneous
            .duration("").startDate(startDate).endDate(endDate)
            .quoteStatus("").quoteId("").date("").validDate("")
            .from(null).to(null).serviceDescription(null).totalManDays(null)
            .unitPrice(null).clientCurrency(Currency.EUR).totalAmount(0.0f)
            .po("").sfdc("").pa("").projectType(ProjectType.Active)
            .build();

        String json = mapper.writeValueAsString(projectDetails);

        Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(json)
            .when()
            .post("/zaga/projectManagement/projectDetails");
        String responseBody = response.getBody().asString();
        createResponse = mapper.readValue(responseBody, ProjectDetails.class);

        response
            .then()
            .statusCode(200);
    }

    @Test
    void getProjectDetailsApiTest() {
        RestAssured.given()
            .when()
            .get("/zaga/projectManagement/projectDetails")
            .then()
            .statusCode(200);
    }

     @Test
     @Order(2)
     void getProjectDetailsByIdTest() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .pathParam("projectId", createResponse.projectId)
            .when()
            .get("/zaga/projectManagement/projectDetails/byId/{projectId}")
            .then()
            .statusCode(200);
     }

     void updateProjectDetailsApiTest() {
         int arbitraryStart = 10;
         LocalDate startDate = LocalDate.now().minusDays(arbitraryStart);
         LocalDate endDate = LocalDate.now();
         ProjectDetails projectDetails = ProjectDetails.builder().id(null)
                 // employee details
                 .employeeName("sharamua").employeeEmail("").employeeNumber("")
                 .employeeId("").employeeRole("")
                 // project details
                 .projectAssignmentStatus(false).projectManager("")
                 .projectName(projectName).projectId(createResponse.projectId)
                 // client details
                 .clientName("").clientCountry("").clientTimezone("")
                 .clientAddress("").clientEmail("")
                 // miscellaneous
                 .duration("").startDate(startDate).endDate(endDate)
                 .quoteStatus("").quoteId("").date("").validDate("")
                 .from(null).to(null).serviceDescription(null).totalManDays(null)
                 .unitPrice(null).clientCurrency(Currency.EUR).totalAmount(0.0f)
                 .po("").sfdc("").pa("").projectType(ProjectType.Active)
                 .build();

         RestAssured.given()
             .contentType(ContentType.JSON)
             .accept(ContentType.JSON)
             .body(projectDetails)
             .when()
             .put("/zaga/projectManagement/updateProjectDetails")
             .then()
             .statusCode(200);
     }

     @Test
     @Order(3)
     void deleteProjectDetailsApiTest() {
         RestAssured.given()
             .contentType(ContentType.JSON)
             .pathParam("projectId", createResponse.projectId)
             .when()
             .delete("/zaga/projectManagement/projectDetails/{projectId}")
             .then()
             .statusCode(204);
     }
}

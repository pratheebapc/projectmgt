package com.zaga.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
        private static MongoHelper mongoHelper;
        private static ObjectMapper mapper;
        private static String projectName;
        private ProjectDetails createResponse;

        @BeforeAll
        public static void setUp() throws Exception {
                mongoHelper = new MongoHelper();
                mongoHelper.startDB();
                mongoHelper.loadCollection(
                                "ProjectManagement",
                                "counter",
                                "fixtures/ProjectManagement/counter.json");

                mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                projectName = "project-" + RandomStringUtils.randomAlphanumeric(10);
        }

        @AfterAll
        public static void tearDown() throws Exception {
                mongoHelper.stopDB();
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
                                .employeeId("2").employeeRole("")
                                // project details
                                .projectAssignmentStatus(false).projectManager("").projectId("1")
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
                                .post("/zaga/projectManagement/projectDetails/createProjectDetails");
                String responseBody = response.getBody().asString();
                createResponse = mapper.readValue(responseBody, ProjectDetails.class);

                response
                                .then()
                                .statusCode(200);

                System.out.println("---------" + createResponse);
        }

        @Test
        @Order(3)
        void getProjectDetailsApiTest() {
                RestAssured.given()
                                .when()
                                .get("/zaga/projectManagement/projectDetails/viewProjectDetails")
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
                                .get("/zaga/projectManagement/projectDetails/viewProjectDetailsById/{projectId}")
                                .then()
                                .statusCode(200);
        }

        @Test
        @Order(4)
        void updateProjectDetailsApiTest() throws JsonProcessingException {
                int arbitraryStart = 10;
                LocalDate startDate = LocalDate.now().minusDays(arbitraryStart);
                LocalDate endDate = LocalDate.now();

                ProjectDetails projectDetails = ProjectDetails.builder().id(null)
                                // employee details
                                .employeeName("sharamua").employeeEmail("").employeeNumber("")
                                .employeeId("2").employeeRole("")
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

                String json = mapper.writeValueAsString(projectDetails);
                RestAssured.given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .body(json)
                                .when()
                                .put("/zaga/projectManagement/projectDetails/updateProjectDetails").then()
                                .statusCode(200);
        }

        @Test
        @Order(5)
        void deleteProjectDetailsApiTest() {
                RestAssured.given()
                                .contentType(ContentType.JSON)
                                .pathParam("projectId", createResponse.projectId)
                                .when()
                                .delete("/zaga/projectManagement/projectDetails/deleteProjectDetails/{projectId}")
                                .then()
                                .statusCode(204);
        }
}

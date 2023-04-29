// package com.zaga.rest;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import org.apache.commons.lang3.RandomStringUtils;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.MethodOrderer;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestMethodOrder;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// import com.zaga.model.dto.Agenda;
// import com.zaga.model.dto.Attendees;
// import com.zaga.model.entity.MeetingMinutes;

// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.RestAssured;
// import io.restassured.http.ContentType;
// import io.restassured.response.Response;

// @QuarkusTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// public class MeetingMinutesTest {

// private static MongoHelper mongoHelper;
// private static ObjectMapper mapper;
// // private static String projectName;
// private MeetingMinutes createResponse;

// @BeforeAll
// public static void setUp() throws Exception {
// mongoHelper = new MongoHelper();
// mongoHelper.startDB();
// mongoHelper.loadCollection(
// "ProjectManagement",
// "counter",
// "fixtures/ProjectManagement/counter.json");

// mapper = new ObjectMapper();
// mapper.registerModule(new JavaTimeModule());

// // projectName = "project-" + RandomStringUtils.randomAlphanumeric(10);
// }

// @AfterAll
// public static void tearDown() throws Exception {
// mongoHelper.stopDB();
// }

// // private String creationResponse() {
// // return createResponse.projectId;
// // }

// @Test
// @Order(1)
// void createMeetingMinutesApiTest() throws JsonProcessingException {
// List<Attendees> list = new ArrayList<Attendees>();
// Attendees attendee = new Attendees("Alberto", "Redhat");
// Attendees attendee1 = new Attendees("Bala", "Redhat");
// list.add(attendee);
// list.add(attendee1);
// List<Agenda> list1 = new ArrayList<Agenda>();
// Agenda agenda = new Agenda("PAM issue", "2hours", "Bala", "Explained about
// PAM issue");
// list1.add(agenda);
// MeetingMinutes minutes = MeetingMinutes.builder().id(null)
// .employeeName("Surendhar").projectName("citi
// ci").projectId("45").meetingMinutesId(null)
// .date("2023-04-10").startTime("10:00 AM").endTime("12:00 PM")
// .meetingObjective("Discussed about the CI
// issue").attendeesPresent(list).agenda(list1)
// .build();

// String json = mapper.writeValueAsString(minutes);

// Response response = RestAssured.given()
// .contentType(ContentType.JSON)
// .accept(ContentType.JSON)
// .body(json)
// .when()
// .post("/zaga/projectManagement/meetingMinutes/createMeetingMinutes");
// String responseBody = response.getBody().asString();
// createResponse = mapper.readValue(responseBody, MeetingMinutes.class);

// response
// .then()
// .statusCode(200);

// System.out.println("---------" + createResponse);
// }

// @Test
// @Order(2)
// void getMeetingMinutesApiTest() {
// RestAssured.given()
// .when()
// .get("/zaga/projectManagement/meetingMinutes/getMeetingMinutes")
// .then()
// .statusCode(200);
// }

// @Test
// @Order(3)
// void getMeetingMinutesByMeetingMinutesIdTest() {
// RestAssured.given()
// .contentType(ContentType.JSON)
// .pathParam("meetingMinutesId", createResponse.meetingMinutesId)
// .when()
// .get("/zaga/projectManagement/meetingMinutes/getMeetingMinutesByMeetingMinutesId/{meetingMinutesId}")
// .then()
// .statusCode(200);
// }

// @Test
// @Order(4)
// void getMeetingMinutesByProjectIdTest() {
// RestAssured.given()
// .contentType(ContentType.JSON)
// .pathParam("projectId", createResponse.projectId)
// .when()
// .get("/zaga/projectManagement/meetingMinutes/getMeetingMinutesByProjectId/{projectId}")
// .then()
// .statusCode(200);
// }

// @Test
// @Order(5)
// void updateProjectDetailsApiTest() throws JsonProcessingException {
// List<Attendees> list = new ArrayList<Attendees>();
// Attendees attendee = new Attendees("Alberto", "Redhat");
// Attendees attendee1 = new Attendees("Bala", "Redhat");
// list.add(attendee);
// list.add(attendee1);
// List<Agenda> list1 = new ArrayList<Agenda>();
// Agenda agenda = new Agenda("PAM issue", "2hours", "Bala", "Explained about
// PAM issue");
// list1.add(agenda);

// MeetingMinutes minutes = MeetingMinutes.builder().id(null)
// .employeeName("Surendhar").projectName("citi ci").projectId("45")
// .meetingMinutesId(createResponse.meetingMinutesId)
// .date("2023-04-10").startTime("10:00 AM").endTime("12:00 PM")
// .meetingObjective("Discussed about the CI
// issue").attendeesPresent(list).agenda(list1)
// .build();

// String json = mapper.writeValueAsString(minutes);
// RestAssured.given()
// .contentType(ContentType.JSON)
// .accept(ContentType.JSON)
// .body(json)
// .when()
// .put("/zaga/projectManagement/meetingMinutes/modifyMeetingMinutesById").then()
// .statusCode(200);
// }

// @Test
// @Order(6)
// void deleteMeetingMinutesApiTest() {
// RestAssured.given()
// .contentType(ContentType.JSON)
// .pathParam("meetingMinutesId", createResponse.meetingMinutesId)
// .when()
// .delete("/zaga/projectManagement/meetingMinutes/deleteMeetingMinutesById/{meetingMinutesId}")
// .then()
// .statusCode(200);
// }

// }

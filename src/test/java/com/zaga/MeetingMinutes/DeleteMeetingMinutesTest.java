package com.zaga.MeetingMinutes;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@QuarkusTest
public class DeleteMeetingMinutesTest {
    
    
    @Test
    public void testDeleteMeetingMinutes(){

        String id = "4";
        RestAssured.given().contentType(ContentType.JSON).pathParam("meetingMinutesId",id)
                    .when().delete("/zaga/projectManagement/meetingMinutes/deleteMeetingMinutesById/{meetingMinutesId}")
                    .then().statusCode(200);
    }
}

package com.zaga.MeetingMinutes;

import org.junit.jupiter.api.Test;

import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class ModifyMeetingMinutesTest {
    
    @Test
    public void testUpdateMeetingMinutes(){

        MeetingMinutes meetingMinutes = new MeetingMinutes(null, "","","","5",null, "","", null, null, null);
        String meetingMinutesId = "2";
        UpdateMeetingMinutesDto dto = new UpdateMeetingMinutesDto(meetingMinutesId, meetingMinutes);
        RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(dto)
                    .when().put("/zaga/projectManagement/meetingMinutes/modifyMeetingMinutesById")
                    .then().statusCode(200);
    }


}

package com.test.task.framework.api;

import com.test.task.framework.report.ReportHelper;
import com.test.task.framework.reader.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApi
{
    public JsonPath getUserDetails(String appName){
        JsonPath jsonPathEvaluator = null;
        String apiEndPoint=ConfigReader.getPropertyValue(appName,"apiEndPoint");
        RestAssured.baseURI= "https://randomuser.me/api/";

        Response response = RestAssured.given()
                .get(apiEndPoint);
        if(response.getStatusCode()==200) {
            jsonPathEvaluator=response.jsonPath();
            ReportHelper.PASS("Data retrieved successfully from API");
        }
        else{
            ReportHelper.FAIL("Error in getting response: "+response.getStatusCode());
        }
        return jsonPathEvaluator;
    }

}

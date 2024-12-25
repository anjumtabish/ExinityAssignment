package services;

import utils.ConfigReader;
import utils.RestAssuredUtils;
import io.restassured.response.Response;

public class IssueService {

    public static Response createIssue(String payload) {
        return RestAssuredUtils.post(ConfigReader.getProperty("base.url"), payload);
    }

    public static Response retrieveIssue(String issueId) {
        return RestAssuredUtils.get(ConfigReader.getProperty("base.url") + "/" + issueId);
    }

    public static Response updateIssue(String issueId, String payload) {
        return RestAssuredUtils.put(ConfigReader.getProperty("base.url") + "/" + issueId, payload);
    }

    public static Response deleteIssue(String issueId) {
        return RestAssuredUtils.delete(ConfigReader.getProperty("base.url") + "/" + issueId);
    }
}

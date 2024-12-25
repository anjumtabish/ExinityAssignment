package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import constants.ApiConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

    /**
     * Returns a pre-configured RequestSpecification with headers.
     *
     * @return RequestSpecification
     */
	
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .header("Authorization", ApiConstants.AUTH_HEADER)
                .header("Content-Type", ApiConstants.CONTENT_TYPE)
                .header("User-Agent", ApiConstants.USER_AGENT)
                .header("Accept", ApiConstants.ACCEPT);
    }

    /**
     * Sends a POST request to the specified endpoint with the provided body.
     *
     * @param endpoint API endpoint
     * @param body     Request payload
     * @return Response from the server
     */
    public static Response post(String endpoint, String body) {
        return getRequestSpecification()
                .body(body)
                .post(endpoint);
    }

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint API endpoint
     * @return Response from the server
     */
    public static Response get(String endpoint) {
        return getRequestSpecification()
                .get(endpoint);
    }

    /**
     * Sends a PUT request to the specified endpoint with the provided body.
     *
     * @param endpoint API endpoint
     * @param body     Request payload
     * @return Response from the server
     */
    public static Response put(String endpoint, String body) {
        return getRequestSpecification()
                .body(body)
                .put(endpoint);
    }

    /**
     * Sends a DELETE request to the specified endpoint.
     *
     * @param endpoint API endpoint
     * @return Response from the server
     */
    public static Response delete(String endpoint) {
        return getRequestSpecification()
                .delete(endpoint);
    }


    
    public static String getPayload(String templatePath, Map<String, String> placeholders) {
        try {
            String payload = new String(Files.readAllBytes(Paths.get(templatePath)));
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                payload = payload.replace("${" + entry.getKey() + "}", entry.getValue());
            }
            return payload;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read payload template: " + e.getMessage());
        }
    }
}

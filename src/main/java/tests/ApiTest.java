package tests;

import io.restassured.response.Response;
import services.IssueService;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ExtentReportUtils;
import utils.RestAssuredUtils;
//import utils.ScreenshotUtils;

public class ApiTest {

	private static String issueId; // Shared issue ID for all tests

	@BeforeSuite
	public void setupReporting() {
		ExtentReportUtils.createTest("Setup Suite");
		ExtentReportUtils.getExtentReports(); // Initialize Extent Reports
	}

	@AfterSuite
	public void teardownReporting() {
		ExtentReportUtils.flushReports(); // Finalize and flush reports
	}

	/*@AfterMethod
	public void captureFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error occurred.";
			ExtentReportUtils.getTest().fail("Test failed: " + errorMessage);

			try {
				String screenshotPath = ScreenshotUtils.captureScreenshot(result.getName());
				if (screenshotPath != null) {
					ExtentReportUtils.getTest().addScreenCaptureFromPath(screenshotPath);
				} else {
					ExtentReportUtils.getTest().info("Screenshot not captured due to an error.");
				}
			} catch (Exception e) {
				ExtentReportUtils.getTest().info("Screenshot capturing failed: " + e.getMessage());
			}
		}
	}*/


	//Test case to create issue 
	@Test
	public void testCreateIssue() {


		ExtentReportUtils.createTest("Create Issue");
		Map<String, String> placeholders = new HashMap<>();
		placeholders.put("projectKey", "QA");
		placeholders.put("summary", "Main order flow broken");
		placeholders.put("description", "Order entry fails when selecting supplier.");
		placeholders.put("issueType", "Task");
		placeholders.put("labels", "bugfix");

		String payload = RestAssuredUtils.getPayload("src/test/resources/templates/issue_payload.json", placeholders);
		Response response = IssueService.createIssue(payload);

		Assert.assertEquals(response.statusCode(), 201, "Issue creation failed!");
		ExtentReportUtils.getTest().pass("Issue created successfully.");
		issueId = response.jsonPath().getString("id");
		Assert.assertNotNull(issueId, "Issue ID is null!");  // Assertion for Issue ID
		 // Extract and validate the message
	    String message = response.jsonPath().getString("message");
	    Assert.assertEquals(message, "Issue created successfully.", "Unexpected response message!"); // Assertion for message
		System.out.println("POST API-Issue created with ID: " + issueId);
	}
	//--------------------------
	//Test case to Retrieve created issue 


	@Test(priority = 2, dependsOnMethods = "testCreateIssue")
	public void testRetrieveIssue() {
		ExtentReportUtils.createTest("Retrieve Issue");

		// API Call
		Response response = IssueService.retrieveIssue(issueId);

		// Validation
		Assert.assertEquals(response.statusCode(), 200, "Issue retrieval failed!");
		String retrievedSummary = response.jsonPath().getString("fields.summary");
		Assert.assertEquals(retrievedSummary, "Main order flow broken", "Summary mismatch for retrieved issue!");
		ExtentReportUtils.getTest().pass("Issue retrieved successfully with correct details.");
		System.out.println("Retrieved API-retrievedSummary: " + retrievedSummary);
	}

	//Test case to Update or modify  created issue 
	@Test(priority = 3, dependsOnMethods = "testRetrieveIssue")
	public void testUpdateIssue() {
		ExtentReportUtils.createTest("Update Issue");

		// Prepare payload
		Map<String, String> placeholders = new HashMap<>();
		placeholders.put("summary", "Updated: Main order flow broken");
		placeholders.put("description", "Updated description for the issue.");

		String payload = RestAssuredUtils.getPayload("src/test/resources/templates/update_payload.json", placeholders);

		// API Call
		Response response = IssueService.updateIssue(issueId, payload);

		// Validation
		Assert.assertEquals(response.statusCode(), 204, "Issue update failed!");
		ExtentReportUtils.getTest().pass("Issue updated successfully.");


	}

	//Test case to Delete  created issue 
	@Test(priority = 4, dependsOnMethods = "testUpdateIssue")
	public void testDeleteIssue() {
		ExtentReportUtils.createTest("Delete Issue");

		// API Call
		Response response = IssueService.deleteIssue(issueId);
		System.out.println("Delete API-Issue deleted with ID: " + issueId);
		// Validation
		Assert.assertEquals(response.statusCode(), 204, "Issue deletion failed!");
		ExtentReportUtils.getTest().pass("Issue deleted successfully.");
	}

}

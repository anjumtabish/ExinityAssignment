package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportUtils {
	 private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    public static ExtentReports getExtentReports() {
	        if (extent == null) {
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);
	        }
	        return extent;
	    }

	    public static ExtentTest createTest(String testName) {
	        ExtentTest test = getExtentReports().createTest(testName);
	        extentTest.set(test);
	        return test;
	    }

	    public static ExtentTest getTest() {
	        return extentTest.get();
	    }

	    public static void flushReports() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
}

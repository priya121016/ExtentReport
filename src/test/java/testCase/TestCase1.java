package testCase;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setup() {

		htmlreporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("automation report");
		htmlreporter.config().setReportName("Automation report test");
		htmlreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);

		extent.setSystemInfo("Tester Name", "Priya Gupta");
		extent.setSystemInfo("Orgainization", "FedEx");
		
	}

	@AfterTest
	public void closeReport() {

		extent.flush();

	}

	@Test
	public void doLogin() {

		test = extent.createTest("Login test");
		System.out.println("Login the site successfully");

	}

	@Test
	public void doUserreg() {

		test = extent.createTest("Registration test");
		Assert.fail("user unable to register the page");

	}

	@Test
	public void isSkip() {

		test = extent.createTest("Skip test");
		throw new SkipException("skip the test case");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			
			String methodName = result.getMethod().getMethodName();

			String text = "<b>" + methodName + "::::: Fail" + "</b>";

			Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
			test.log(Status.FAIL, m);

		} else if (result.getStatus() == ITestResult.SKIP) {

			String methodName = result.getMethod().getMethodName();

			String text = "<b>" + methodName + "::::: Skip" + "</b>";

			Markup m = MarkupHelper.createLabel(text, ExtentColor.BLUE);
			test.log(Status.SKIP, m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String methodName = result.getMethod().getMethodName();

			String text = "<b>" + methodName + "::::: Passed" + "</b>";

			Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN);
			test.log(Status.PASS, m);;

		}

	}
}

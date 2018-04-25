package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import config.ActionKeywords;
import config.Constants;
import config.Log;
import utility.ExcelUtils;



public class DriverScript 
{
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sTestCaseID1;
	public static String sRunMode;
	public static Logger logger;

	public static void main(String[] args) throws Exception 
	{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();

		ExcelUtils.setExcelFile(Constants.Path_TestData,Constants.Sheet_TestSteps);
		//This is to start the Log4j logging in the test case
		PropertyConfigurator.configure("C:\\Users\\Archana\\git\\KDF\\KeywordDrivenFramework\\Logfolder\\log4j.properties");
		DriverScript startEngine = new DriverScript();


		String Path_OR = Constants.Prop_path;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);


		startEngine.execute_TestCase();
	}

	
	public void execute_TestCase() throws Exception
	{
		int iTotalTestCases = ExcelUtils.getRowCount("Test Cases");
		for(int iTestcase=0;iTestcase<iTotalTestCases;iTestcase++)
		{
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID+2,"Test Cases");
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID,"Test Cases"); 

			if (sRunMode.equals("Yes"))
			{ 
				Log.startTestCase(sTestCaseID);
				iTestStep=1;
				//iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				sTestCaseID1 = ExcelUtils.getCellData(++iTestcase, Constants.Col_TestCaseID,Constants.Sheet_TestSteps); 
				iTestLastStep = ExcelUtils.getTestStepsCount("Test Steps", sTestCaseID1, iTestStep-(iTestStep-1));
				for (;iTestStep<iTestLastStep;iTestStep++)
				{
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					execute_Actions();
				}
				Log.endTestCase(sTestCaseID);
			}
			else
			{
				System.out.println("else");
			}
		}
	}

	private static void execute_Actions() throws Exception
	{

		for(int i = 0;i < method.length;i++){

			if(method[i].getName().equalsIgnoreCase(sActionKeyword)){
				method[i].invoke(actionKeywords,sPageObject);
				break;
			}
		}
	}

}

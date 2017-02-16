package config;

/**
 * Created by Mamata.Mechineni on 24-Jan-17.
 */
public class Constants {
    public static final String URL = "https://directqa.dimensiondata.com/seller/direct/login.asp?sso=0";

    public static final String Path_OR = "C://Selenium WorkSpace//DirectCiscoOne - Copy//src//config//OR.txt";

    public static final String Path_TestData = "C://Selenium WorkSpace//DirectCiscoOne - Copy//src//dataEngine//";

    public static final String File_TestData = "DataEngine.xlsx";
    public static final String File_TestScenarios = "TestScenarios.xlsx";

    public static final String KEYWORD_FAIL = "FAIL";
    public static final String KEYWORD_PASS = "PASS";

    //Data Sheet Column Numbers
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID =1 ;
    public static final int Col_PageObject =4 ;
    public static final int Col_ActionKeyword =5 ;
    public static final int Col_RunMode =2 ;
    public static final int Col_Result =3 ;
    public static final int Col_DataSet =6 ;
    public static final int Col_TestStepResult =7 ;
    public static final  int Col_VerifyMethods = 8;

    // Data Engine Excel sheets
    public static final String Sheet_TestScenarios = "TestScenarios";
    public static final String Sheet_TestCases = "TestCases";
    public static final String Sheet_TestSteps = "TestSteps";
    public static final String Sheet_TestData = "TestData";

}

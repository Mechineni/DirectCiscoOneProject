package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.ArrayList;

import com.sun.tools.classfile.Opcode;
import org.apache.log4j.xml.DOMConfigurator;

import config.*;
import org.apache.poi.sl.draw.geom.IfElseExpression;
import utility.*;

import javax.swing.*;

public class DriverScript {

    public static Properties OR;
    public static ActionKeywords actionKeywords;
    public static VerifyMethods verifyMethods;
    public static String sVerifyMethods;
    public static String sActionKeyword;
    public static String sPageObject;
    public static Method method[];
    public static Method vmethod[];

    public static int iTestStep;
    public static int iTestScenario;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sTestScenarioID;
    public static String sRunMode;
    public static String sData;
    public static String sComponentName;
    public static boolean bResult;
    public static boolean bComponentResult;
    public static boolean bValue;

    public DriverScript() throws NoSuchMethodException, SecurityException{
        actionKeywords = new ActionKeywords();
        method = actionKeywords.getClass().getMethods();
        verifyMethods = new VerifyMethods();
        vmethod = verifyMethods.getClass().getMethods();
    }

    public static void main(String[] args) throws Exception {
        ExcelUtils.setExcelFile(Constants.Path_TestData+Constants.File_TestData);
        DOMConfigurator.configure("log4j.xml");
        String Path_ORs = Constants.Path_OR;
        FileInputStream fs = new FileInputStream(Path_ORs);
        OR = new Properties(System.getProperties());
        OR.load(fs);

        DriverScript startEngine = new DriverScript();
        startEngine.execute_TestCase();

    }

    private void execute_TestCase() throws Exception {
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestScenarios);
        //System.out.print(""+iTotalTestCases);
        for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){

            bResult = true;
            sTestScenarioID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestScenarios);

            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestScenarios);
            if (sRunMode.equalsIgnoreCase("Yes")){
                Log.startTestCase(sTestScenarioID);
                iTestScenario = ExcelUtils.getRowContains(sTestScenarioID, Constants.Col_TestCaseID, sTestScenarioID);
                bComponentResult = true;

                int k = 15;
                for (int j= 4; j<k;j++){

                    sComponentName = ExcelUtils.getCellData(iTestScenario,j, Constants.Sheet_TestScenarios);
                    //ArrayList ListComponents = new ArrayList();
                    //ListComponents.add(sComponentName);
                    //iTestStep = ExcelUtils.getRowContains(sComponentName, Constants.Col_TestCaseID, sComponentName);

                    iTestLastStep = ExcelUtils.getTestStepsCount(sComponentName, sComponentName);

                    bResult=true;

                    for (iTestStep=1;iTestStep<iTestLastStep;iTestStep++){

                        //sVerifyMethods = ExcelUtils.getCellData(iTestStep, Constants.Col_VerifyMethods,sComponentName);
                        sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,sComponentName);
                        sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, sComponentName);
                        sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, sComponentName);
                        //System.out.print("Verify"+sVerifyMethods);

                        if (sData.equals("")) {

                            sData = "test";
                            System.out.print("entered  "+sData);
                        }
                        if (sActionKeyword.startsWith("Verify_")){
                            //System.out.print("entering"+sActionKeyword);
                            execute_VerifyMethod();

                        } else{
                            //System.out.print(""+sVerifyMethods);
                            execute_Actions();
                        }

                        if(bResult==false){
                            ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestScenarios);
                            bComponentResult = false;
                            Log.endTestCase(sTestCaseID);
                            break;
                        }
                    }

                }
                if(bResult==true & bComponentResult==false) {
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_Result, Constants.Sheet_TestScenarios);
                    Log.endTestCase(sTestCaseID);
                }else if (bResult==true & bComponentResult==true){
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_Result, Constants.Sheet_TestScenarios);
                    Log.endTestCase(sTestCaseID);
                }
            }
        }
    }

    private static void execute_Actions() throws Exception {

        for(int i=0;i<method.length;i++){

            if(method[i].getName().equals(sActionKeyword)){
                System.out.print("Data for blank "+sData);
                method[i].invoke(actionKeywords,sPageObject,sData);

                if(bResult==true){
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, sComponentName);
                    break;
                }else{
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, sComponentName);
                    ActionKeywords.closeBrowser("","");
                    break;
                }
            }
        }
    }

    private static void execute_VerifyMethod() throws Exception {

        for(int i=0;i<vmethod.length;i++){

            if(vmethod[i].getName().equals(sActionKeyword)){
                //System.out.print("Data for blank"+sData);
                vmethod[i].invoke(verifyMethods,sPageObject,sData);

                if(bResult==true){
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, sComponentName);
                    break;
                }else{
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, sComponentName);
                    ActionKeywords.closeBrowser("","");
                    break;
                }
            }
        }
    }

}
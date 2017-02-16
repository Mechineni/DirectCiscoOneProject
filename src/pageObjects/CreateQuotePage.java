package pageObjects;

import config.ActionKeywords;

/**
 * Created by Mamata.Mechineni on 14-Feb-17.
 */
public class CreateQuotePage {

    public static void CreateQuote(){
        ActionKeywords.click("lnk_CreateQuote","test");
        ActionKeywords.input("txtbx_BuyerName","mamata");
        ActionKeywords.select("lbx_Rgion","001 z - Demo Region");
        ActionKeywords.click("btn_Search","test");
        ActionKeywords.click("lnk_BuyerLink","mamata");
    }
}

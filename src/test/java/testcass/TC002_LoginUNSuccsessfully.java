package testcass;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;


public class TC002_LoginUNSuccsessfully extends BaseClass {


    @Test(groups = {"Regression","Master"})
    public void VerifyLoginSuccess() {
        logger.info("starting the testcase ***");
        try {
            LoginPage Lp = new LoginPage(driver);
            Lp.setUserName("standauser");
            Lp.setPassword("secreuce");
            Lp.Login();
            logger.info("validating the error message ***");
            String errormess = Lp.message();
            Assert.assertEquals(errormess, "Epic sadface: Username and password do not match any user in this service");
        } catch (Exception e) {
            logger.error("Test failed");
            logger.debug("Debug details");
            Assert.fail();
        }
        logger.info("Test case finished ****");
    }



}

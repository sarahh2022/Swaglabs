package testcass;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;


public class TC001_LoginSuccseefully extends BaseClass {



    @Test(groups = {"sanity","Master"})
    public void LoginSuccess(){

        logger.info("starting the testcase ***");
        LoginPage Lp = new LoginPage(driver);
        Lp.setUserName(p.getProperty("Username"));
        Lp.setPassword(p.getProperty("password"));
        logger.info("Enter credentionals***");
        Lp.Login();
        logger.info("clicking on login***");

        ProductsPage PP = new ProductsPage(driver);
        Boolean DisplayingProduct = PP.IsProductDisplayed();
        Assert.assertTrue(DisplayingProduct);
        logger.info("Test case finished ****");

    }
}

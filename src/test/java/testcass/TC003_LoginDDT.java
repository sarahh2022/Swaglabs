package testcass;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utalities.Dataproviders;

public class TC003_LoginDDT extends BaseClass {

    @Test (dataProvider ="LoginData" , dataProviderClass = Dataproviders.class)

    public void DataDrivenLogin(String usrname , String pwd , String expresult) {


        LoginPage Lp = new LoginPage(driver);
        Lp.setUserName(usrname);
        Lp.setPassword(pwd);
        Lp.Login();

        ProductsPage PP = new ProductsPage(driver);
        Boolean DisplayingProduct = PP.IsProductDisplayed();
        //valid data -- login success -- test pass-- logout
        //vlid data -- lobin fail -- test fail
        //invalid data -- login succes -test fail -- logout


        if(expresult.equalsIgnoreCase("valid")){
            if(DisplayingProduct==true){
                PP.logoutbutton();
                Assert.assertTrue(true);
            }
            else{

                Assert.assertTrue(false);
            }

            }
        if (expresult.equalsIgnoreCase("invalid")){
            if(DisplayingProduct==true){
                PP.logoutbutton();
                Assert.assertTrue(false);
            }
            else{

                Assert.assertTrue(true);
            }
        }

        }
    }

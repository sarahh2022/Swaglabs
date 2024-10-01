package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Constructor{

    public LoginPage(WebDriver driver){
        super (driver);
    }


    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement sumbitbutton;

    @FindBy(xpath ="//h3[@data-test='error']")
    WebElement errormessage;

 public void setUserName(String UN){
     username.sendKeys(UN);
 }

    public void setPassword(String Pass){
        password.sendKeys(Pass);
    }

    public void Login(){
        sumbitbutton.click();
    }

    public String message (){
     return (errormessage.getText());

    }

}

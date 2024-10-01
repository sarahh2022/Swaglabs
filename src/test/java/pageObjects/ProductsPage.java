package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class ProductsPage extends Constructor{

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath ="//div[@class='product_label']")
    WebElement productsLabel;

    @FindBy(xpath = "//*[@id=\"menu_button_container\"]/div/div[3]/div/button")
    WebElement menu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutbutton;



    public boolean IsProductDisplayed(){
        try {
            return (productsLabel.isDisplayed());
        } catch (Exception e){
            return  false;
        }

    }


    public void logoutbutton(){
        menu.click();
        logoutbutton.click();
    }
}

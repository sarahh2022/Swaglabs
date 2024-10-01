package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Regression", "Master", "sanity"})
    @Parameters({"os", "browser"})
    public void Setup(String os, String br) throws IOException {
        //To read data from properties file
        FileReader file = new FileReader("./src//test//resources//Config.properties");
        p = new Properties();
        p.load(file);

        //To log the steps of my excution
        logger = LogManager.getLogger(this.getClass());


        if(p.getProperty("Excution_env").equalsIgnoreCase("Remote")){
            DesiredCapabilities cap = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){
                cap.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac")){
                cap.setPlatform(Platform.MAC);
            }
            else {
               System.out.println ("No matching system");
                return;
            }

            switch(br.toLowerCase()){
                case "chrome": cap.setBrowserName("chrome"); break;
                case "edge": cap.setBrowserName("MicreosoftEdge"); break;
                default: System.out.println("No matching browser");return;
            }
            driver = new RemoteWebDriver(new URL(" http://10.0.87.26:4444"), cap);
        }




        if(p.getProperty("Excution_env").equalsIgnoreCase("local")){
            // To read browser form xml parameters
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("invalid browser");
                    return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("AppURL"));
        driver.manage().window().maximize();

    }


    @AfterClass(groups = {"Regression", "Master", "sanity"})
    public void teardown() {
        driver.quit();

    }

    public String capturescreenshot(String tname) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
        File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetfilepath =System.getProperty("user.dir") +"\\screenshots\\ "+ tname + "_"  + timeStamp;
        File targetfile = new File(targetfilepath);
        sourcefile.renameTo(targetfile);
        return targetfilepath;
    }



}

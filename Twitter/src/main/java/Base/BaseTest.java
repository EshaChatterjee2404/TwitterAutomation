package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static DesiredCapabilities caps;
    protected static FileInputStream ip;
    protected static Properties props;

    @BeforeClass
    @Parameters({"platformName","platformVersion","UDID","deviceName"})
    public void setupDriver(@Optional("Android")String platformName,@Optional("11")String platformVersion,@Optional("fb7713bd98fb")String UDID,@Optional("Mi A3")String deviceName) {
        try {
            String path = "C:\\Users\\HP\\eclipse-workspace\\Amazon\\src\\test\\resources\\config.properties";
            ip = new FileInputStream(path);
            props = new Properties();
            props.load(ip);
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.UDID, UDID);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("android"));
            caps.setCapability(MobileCapabilityType.APP, props.getProperty("androidApp"));
            caps.setCapability("appPackage","com.twitter.android");
            caps.setCapability("appActivity","com.twitter.android.StartActivity");
            URL url = new URL(props.getProperty("url"));
            driver = new AndroidDriver(url, caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
    	driver.quit();
    }
}

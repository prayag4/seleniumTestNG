package utilities;
import java.io.File;
import java.io.IOException;
// import java.sql.Date;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

// import ch.qos.logback.core.util.FileUtil;
import common.BaseTest;

public class testUtils extends BaseTest{

    public void getScreenshot() throws IOException{
        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(".//screenshot//"+screenshotFileName+".png"));
    }
    
}

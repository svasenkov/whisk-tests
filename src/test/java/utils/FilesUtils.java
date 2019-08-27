package utils;


import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.SelenideHelper.SELENOID_URL;
import static org.apache.commons.io.FileUtils.copyURLToFile;

public class FilesUtils {

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static void saveFile(String content, String filePath)  {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void getFileFromContainer(String exportFileName) {
//
//        String session = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//        String path = SELENOID_URL + "download/" + session + "/" + exportFileName;
//
//        int retryCount = 1;
//        while (retryCount <= 10) {
//            try {
//                copyURLToFile(new URL(path), new File("build/downloads/" + exportFileName));
//                break;
//            } catch (IOException e) {
//                logger.error("Файл не найден. Попытка " + retryCount + " из 10");
//                sleep(1000);
//                retryCount++;
//            }
//        }
//    }
}

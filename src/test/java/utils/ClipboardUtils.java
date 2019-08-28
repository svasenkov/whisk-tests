package utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static helpers.EnvHelper.isSelenoid;
import static helpers.EnvHelper.selenoidUrl;
import static helpers.SelenideHelper.getSessionId;
import static io.restassured.RestAssured.get;

public class ClipboardUtils {
    public String getContent() {

        if(isSelenoid) { // for remote browser https://aerokube.com/selenoid/latest/#_accessing_clipboard
            String clipboardUrl = selenoidUrl + "/clipboard/" + getSessionId();
            System.out.println("Selenoid clipboard url: " + clipboardUrl);

            String selenoidClipboardResponse = get(clipboardUrl).asString();
            System.out.println("Selenoid clipboard response: " + selenoidClipboardResponse);

            return selenoidClipboardResponse;

        } else { // for local running browser
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            String result = null;
            try {
                result = (String) clipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("String from clipboard:" + result);
            return result;
        }
    }
}

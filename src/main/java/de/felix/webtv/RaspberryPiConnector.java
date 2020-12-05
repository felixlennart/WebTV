package de.felix.webtv;

import de.felix.webtv.WebtvApplication;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

public class RaspberryPiConnector {
    private String openString;
    private String killString;
    private String shutdownString;

    public void init() throws IOException {
        Properties prop = new Properties();
        prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("bashcommands.properties"));
        openString = prop.getProperty("open");
        killString = prop.getProperty("kill");
        shutdownString = prop.getProperty("shutdown");
    }

    public void kill() throws IOException {
        Runtime.getRuntime().exec(this.killString);
    }

    public void shutdown() throws IOException {
        Runtime.getRuntime().exec(this.shutdownString);
    }

    public void close() {
        System.exit(0);
    }

    public void refresh() throws AWTException, InterruptedException {
        System.setProperty("java.awt.headless", "false");
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        r.keyPress(KeyEvent.VK_F5);
        Thread.sleep(200);
        r.keyRelease(KeyEvent.VK_F5);
        Thread.sleep(5000);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(200);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void open(String senderUrl) throws IOException, InterruptedException, AWTException {
        init();
        kill();
        Runtime.getRuntime().exec(openString + " " + senderUrl);
        Thread.sleep(5000);
        System.setProperty("java.awt.headless", "false");
        // Deal with different player types
        if(senderUrl.contains("ustvgo")){
            Robot r = new Robot();
            Thread.sleep(6500);
            r.mouseMove(550, 550);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(200);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }else {
            Robot r = new Robot();
            r.mouseMove(50, 50);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public void openOnly(String url) throws IOException {
        init();
        Runtime.getRuntime().exec(openString + " " + url);
    }
}

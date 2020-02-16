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

    public void open(String senderUrl) throws IOException, InterruptedException, AWTException {
        init();
        kill();
        Runtime.getRuntime().exec(openString + " " + senderUrl);
        Thread.sleep(5000);
        System.setProperty("java.awt.headless", "false");
        // Deal with different player types
        if(senderUrl.contains("ustvgo")){
            Robot r = new Robot();
            // reload page for vpn extension in chrome
                r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                r.keyPress(KeyEvent.VK_F5);
                Thread.sleep(200);
                r.keyRelease(KeyEvent.VK_F5);
                Thread.sleep(5000);
            r.mouseMove(80, 80);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(200);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }else {
            Robot r = new Robot();
            r.mouseMove(50, 50);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }
}

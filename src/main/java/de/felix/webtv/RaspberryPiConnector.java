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

    public void init() throws IOException {
        Properties prop = new Properties();
        prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("bashcommands.properties"));
        openString = prop.getProperty("open");
        killString = prop.getProperty("kill");
    }

    public void kill() throws IOException {
        Runtime.getRuntime().exec(this.killString);
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
            r.keyPress(KeyEvent.VK_F);
            r.keyRelease(KeyEvent.VK_F);
        }else {
            Robot bot = new Robot();
            bot.mouseMove(50, 50);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }
}

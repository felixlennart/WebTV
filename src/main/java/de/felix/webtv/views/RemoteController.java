package de.felix.webtv.views;
import de.felix.webtv.RaspberryPiConnector;
import de.felix.webtv.RemoteHandler;
import de.felix.webtv.WebtvApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Controller
public class RemoteController {

    private List<String[]> liste() throws IOException {
        List<String[]> senderListe = new ArrayList<>();
        Properties prop = new Properties();
        prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("sender.properties"));
        prop.forEach((k, v) -> senderListe.add(new String[] {(String)k, (String)v}));
        return senderListe;
    }

    @GetMapping({"/", "/remote"})
    public String remote(Model model, ModelMap vars) throws IOException {
        vars.put("senderliste", liste());
        return "remote";
    }

    @PostMapping({"/", "/remote"})
    public String remoteSubmit(@ModelAttribute RemoteHandler remoteHandler, ModelMap vars, @RequestParam("url") String url) throws IOException, AWTException, InterruptedException {
        RaspberryPiConnector piConnector = new RaspberryPiConnector();
//        piConnector.open(remoteHandler.getSenderLink());
        if(url.equals("killSpring") || url.equals("closePlayer")){
            piConnector.init();
            piConnector.kill();
            if(url.equals("killSpring")){
                piConnector.shutdown();
                System.exit(0);
            }
        }
        piConnector.open(url);
        vars.put("senderliste", liste());
        return "remote";
    }
}



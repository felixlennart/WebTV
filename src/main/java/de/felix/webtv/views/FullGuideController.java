package de.felix.webtv.views;

import de.felix.webtv.RaspberryPiConnector;
import de.felix.webtv.RemoteHandler;
import de.felix.webtv.TVGuide;
import de.felix.webtv.WebtvApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class FullGuideController {

        @GetMapping({"/fullguide"})
        public String fullguide(Model model, ModelMap vars) throws IOException {
            TVGuide tvGuide = new TVGuide();
            Properties prop = new Properties();
            prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("sender.properties"));
            String[][] guideData = new String[prop.size()][2];
            AtomicInteger count = new AtomicInteger();
            prop.forEach((k,v) -> {
                guideData[count.intValue()][0] = (String) k;
                count.getAndIncrement();
            });
            for(int i = 0; i < guideData.length; i++){
                tvGuide.setChannel(guideData[i][0]);
                guideData[i][1] = tvGuide.getGuide();
            }
            vars.put("guideData", guideData);
            return "fullguide";
        }
}

package de.felix.webtv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Properties;

public class TVGuide {
    private String url;
    private String channel;
    private String cssQuery;

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getGuide() {
        Properties prop = new Properties();
        try {
            prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("guideselectors.properties"));
            setCssQuery(prop.getProperty(getChannel()));
            prop.clear();
            prop.load(WebtvApplication.class.getClassLoader().getResourceAsStream("guideurls.properties"));
            setUrl(prop.getProperty(getChannel()));

            if(cssQuery != null && url != null){
            Document document = Jsoup.connect(getUrl()).followRedirects(true).timeout(60000).get();
                return document.body().select(getCssQuery()).get(0).text();
            }
            else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

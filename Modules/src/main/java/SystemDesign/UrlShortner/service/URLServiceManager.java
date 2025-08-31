package SystemDesign.UrlShortner.service;


import SystemDesign.UrlShortner.entitiy.URL;
import SystemDesign.UrlShortner.entitiy.URLAnalytics;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class URLServiceManager {
    private List<String> activeLongURLs;
    private List<String> activeShortCodes;
    private Map<String, URL> urlMap;
    private AtomicInteger counter;

    public URLServiceManager() {
        this.activeLongURLs = new ArrayList<>();
        this.activeShortCodes = new ArrayList<>();
        this.urlMap = new ConcurrentHashMap<>();
        this.counter = new AtomicInteger(1);
    }

    public String generateShortURL(String longURL){
        try{
            if(activeLongURLs.contains(longURL)){
                throw new Exception("This long URL already shortened: " + longURL);
            }
            String shortCode = URLGenerator.getRandomShortCode(6, activeShortCodes);

            if(shortCode == null){
                throw new Exception("Failed to Generate Code. Retry after some time.");
            }

            activeShortCodes.add(shortCode);
            activeLongURLs.add(longURL);

            URL url = new URL("U00" + counter, longURL, shortCode, LocalDateTime.now());

            URLAnalytics urlAnalytics = new URLAnalytics("UA00" + counter, url);

            urlMap.putIfAbsent(url.getUrlId(), url);
            counter.getAndIncrement();
            url.setUrlAnalytics(urlAnalytics);

            return shortCode;
        } catch (Exception e) {
            System.out.println("Error generating short URL: "+  e.getMessage());
        }
        return null;
    }

}

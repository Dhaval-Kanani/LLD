package SystemDesign.UrlShortner.entitiy;

import java.time.LocalDateTime;

public class URL {
    private String urlId;
    private String shortCode;
    private String longURL;
    private String alias;
    private URLAnalytics urlAnalytics;
    private LocalDateTime expiresOn;
    private LocalDateTime createdOn;

    public URL(String urlId, String longURL, String shortCode, LocalDateTime expiresOn) {
        this.urlId = urlId;
        this.longURL = longURL;
        this.shortCode = shortCode;
        this.createdOn = LocalDateTime.now();
        this.expiresOn = expiresOn;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlAnalytics(URLAnalytics urlAnalytics) {
        this.urlAnalytics = urlAnalytics;
    }
}

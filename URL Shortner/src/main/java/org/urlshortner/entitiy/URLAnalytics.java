package org.urlshortner.entitiy;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class URLAnalytics {
    private String urlAnalyticsId;
    private URL url;
    private long clickCount;
    private List<String> domains;
    private LocalDateTime lastUpdatedOn;

    private Object object;

    public URLAnalytics(String urlAnalyticsId, URL url) {
        this.urlAnalyticsId = urlAnalyticsId;
        this.url = url;
        this.clickCount = 0;
        this.domains = Collections.emptyList();
    }

    public long getClickCount() {
        synchronized (object) {
            return clickCount;
        }
    }

    public List<String> getDomains() {
        return domains;
    }

    public URL getUrl() {
        return url;
    }

    public void incrementClickCount() {
        synchronized (object) {
            this.clickCount++;
            this.lastUpdatedOn = LocalDateTime.now();
        }
    }

    public void addDomain(String domain) {
        synchronized (object) {
            this.domains.add(domain);
            this.lastUpdatedOn = LocalDateTime.now();
        }
    }

}

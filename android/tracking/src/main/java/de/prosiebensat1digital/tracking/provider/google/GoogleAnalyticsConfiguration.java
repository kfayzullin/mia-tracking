package de.prosiebensat1digital.tracking.provider.google;

/**
 * Configuration data for Google Analytics.
 */
public class GoogleAnalyticsConfiguration {
    private final String trackerId;

    /**
     * @param trackerId the google tracker id.
     */
    public GoogleAnalyticsConfiguration(final String trackerId) {
        this.trackerId = trackerId;
    }

    /**
     * @return the google tracker id.
     */
    public String getTrackerId() {
        return trackerId;
    }
}

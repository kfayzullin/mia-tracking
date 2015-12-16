package de.prosiebensat1digital.tracking;

import de.prosiebensat1digital.tracking.provider.google.GoogleAnalyticsConfiguration;
import de.prosiebensat1digital.tracking.provider.mixpanel.MixpanelConfiguration;

/**
 * Configuration data for the tracking layer and the tracking providers.
 */
public class TrackingLayerConfiguration {
    private Boolean developerSupport = false;
    private MixpanelConfiguration mixpanelConfiguration = null;
    private GoogleAnalyticsConfiguration googleAnalyticsConfiguration = null;

    /**
     * @return is the developer support of ReactNative active or not?
     */
    public Boolean getDeveloperSupport() {
        return developerSupport;
    }

    /**
     * @param developerSupport is the developer support of ReactNative active or not?
     */
    public void setDeveloperSupport(Boolean developerSupport) {
        this.developerSupport = developerSupport;
    }

    /**
     * @return configuration data for mixpanel or {@code null} if it should not be used.
     */
    public MixpanelConfiguration getMixpanelConfiguration() {
        return mixpanelConfiguration;
    }

    /**
     * @param mixpanelConfiguration configuration data for mixpanel or {@code null} if it should not be used.
     */
    public void setMixpanelConfiguration(final MixpanelConfiguration mixpanelConfiguration) {
        this.mixpanelConfiguration = mixpanelConfiguration;
    }

    /**
     * @return configuration data for google analytics or {@code null} if it should not be used.
     */
    public GoogleAnalyticsConfiguration getGoogleAnalyticsConfiguration() {
        return googleAnalyticsConfiguration;
    }

    /**
     * @param googleAnalyticsConfiguration configuration data for google analytics or {@code null} if it should not be used.
     */
    public void setGoogleAnalyticsConfiguration(final GoogleAnalyticsConfiguration googleAnalyticsConfiguration) {
        this.googleAnalyticsConfiguration = googleAnalyticsConfiguration;
    }
}

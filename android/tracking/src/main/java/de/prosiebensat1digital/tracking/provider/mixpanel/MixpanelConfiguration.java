package de.prosiebensat1digital.tracking.provider.mixpanel;

/**
 * Configuration data for mixpanel.
 */
public class MixpanelConfiguration {
    private final String token;

    /**
     * @param token the mixpanel project token.
     */
    public MixpanelConfiguration(final String token) {
        this.token = token;
    }

    /**
     * @return the mixpanel project token.
     */
    public String getToken() {
        return token;
    }
}

package edu.ap.facilitytoolspringboot.security;

import java.util.Map;

public class AzureOAuth2UserInfo extends OAuth2UserInfo{

    public AzureOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((String) attributes.get("id"));
    }

    @Override
    public String getName() { return ((String) attributes.get("name")); }

    @Override
    public String getRole() {
        return  "Medewerker";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public Boolean getNotification() {
        return false;
    }
}

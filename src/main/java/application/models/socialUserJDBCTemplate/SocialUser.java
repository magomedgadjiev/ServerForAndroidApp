package application.models.socialUserJDBCTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialUser {

    private String userID;

    private String token;

    public SocialUser(){
        ;
    }

    @JsonCreator
    public SocialUser(@JsonProperty(value = "userID") String userID, @JsonProperty(value = "token") String token) {
        this.userID = userID;
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

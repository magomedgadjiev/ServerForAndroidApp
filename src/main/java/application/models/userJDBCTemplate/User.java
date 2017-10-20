package application.models.userJDBCTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class User {
    private String email;
    private String login;
    private String password;

    User(){
        ;
    }

    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        Map<String, String> map;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

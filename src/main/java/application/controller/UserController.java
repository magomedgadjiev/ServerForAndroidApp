package application.controller;

import application.models.Message;
import application.models.socialUserJDBCTemplate.SocialUser;
import application.models.socialUserJDBCTemplate.SocialUserJDBCTemplate;
import application.models.userJDBCTemplate.User;
import application.models.userJDBCTemplate.UserJDBCTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.io.IOException;


@RestController
@Component
public class UserController {
    private final UserJDBCTemplate userJDBCTemplate;
    private final SocialUserJDBCTemplate socialUserJDBCTemplate;

    private final static Logger LOGGER = Logger.getLogger("maga");


    @Autowired
    public UserController(UserJDBCTemplate userJDBCTemplate, SocialUserJDBCTemplate socialUserJDBCTemplate) {
        this.userJDBCTemplate = userJDBCTemplate;
        this.socialUserJDBCTemplate = socialUserJDBCTemplate;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<?> signOut() throws IOException {
        return ResponseEntity.ok().body(new User("a", "a", "a"));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/signUpSocial", method = RequestMethod.POST)
    public ResponseEntity<?> signUpSocial(@RequestBody SocialUser socialUser){
        if (!socialUserJDBCTemplate.add(socialUser)){
            LOGGER.debug("access_token conflict");
            return ResponseEntity.ok().body(new Message<>(HttpStatus.CONFLICT.value(), socialUser));
        }
        LOGGER.debug("success user_id registered");
        return ResponseEntity.ok().body(new Message<>(HttpStatus.OK.value(), socialUser));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody User user){
        if (!userJDBCTemplate.add(user)){
            LOGGER.debug("User conflict");
            return ResponseEntity.ok().body(new Message<>(HttpStatus.CONFLICT.value(), user));
        }
        LOGGER.debug("success user registered");
        return ResponseEntity.ok().body(new Message<>(HttpStatus.OK.value(), user));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody User user){
        if (userJDBCTemplate.getUser(user) == null){
            LOGGER.debug("user not found");
            return ResponseEntity.ok().body(new Message<>(HttpStatus.NOT_FOUND.value(), user));
        }
        LOGGER.debug("get user success");
        return ResponseEntity.ok().body(new Message<>(HttpStatus.OK.value(), user));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/signInSocial", method = RequestMethod.POST)
    public ResponseEntity<?> signInSocial(@RequestBody SocialUser socialUser){
        if (socialUserJDBCTemplate.getSocialUser(socialUser.getToken()) == null){
            LOGGER.debug("user_id not found");
            return ResponseEntity.ok().body(new Message<>(HttpStatus.NOT_FOUND.value(), socialUser));
        }
        LOGGER.debug("get user_id success");
        return ResponseEntity.ok().body(new Message<>(HttpStatus.OK.value(), socialUser));
    }
}

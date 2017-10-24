package application.models.socialUserJDBCTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.SocketImpl;

@Service
public class SocialUserJDBCTemplate {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SocialUserJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean add(SocialUser socialUser){
        try{
            String sql = "insert into social_users (user_id, user_token) values (?, ?);";
            jdbcTemplate.update(sql, socialUser.getUserID(), socialUser.getToken());
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    public SocialUser getSocialUser(String token){
        try{
            String sql = "SELECT * FROM social_users WHERE user_token = ?;";
            return jdbcTemplate.queryForObject(sql, new SocialUserMapper(), (Object) token);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}

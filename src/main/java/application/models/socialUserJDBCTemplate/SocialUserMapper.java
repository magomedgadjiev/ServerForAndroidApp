package application.models.socialUserJDBCTemplate;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialUserMapper implements RowMapper<SocialUser> {
    @Override
    public SocialUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        final SocialUser user = new SocialUser();
        user.setToken(rs.getString("user_token"));
        user.setUserID(rs.getString("user_id"));
        return user;
    }
}
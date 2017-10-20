package application.models.userJDBCTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserJDBCTemplate {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean add(User user) {
        try {
            String sql = "INSERT INTO users(email, login, password) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getPassword());
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    public User getUser(User user) {
        try {
            String sql = "SELECT * FROM users where LOWER(login) = LOWER(?) AND LOWER(password) = LOWER(?)";
            return jdbcTemplate.queryForObject(sql, new UserMapper(), user.getLogin(), user.getPassword());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}

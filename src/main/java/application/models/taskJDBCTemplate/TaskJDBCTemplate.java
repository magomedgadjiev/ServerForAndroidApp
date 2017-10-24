package application.models.taskJDBCTemplate;

import application.models.socialUserJDBCTemplate.SocialUser;
import application.models.socialUserJDBCTemplate.SocialUserJDBCTemplate;
import application.models.userJDBCTemplate.User;
import application.models.userJDBCTemplate.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class TaskJDBCTemplate {
    private final JdbcTemplate jdbcTemplate;
    private final UserJDBCTemplate userJDBCTemplate;
    private final SocialUserJDBCTemplate socialUserJDBCTemplate

    @Autowired
    public TaskJDBCTemplate(JdbcTemplate jdbcTemplate, UserJDBCTemplate userJDBCTemplate, SocialUserJDBCTemplate socialUserJDBCTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userJDBCTemplate = userJDBCTemplate;
        this.socialUserJDBCTemplate = socialUserJDBCTemplate;
    }

    public Task add(Task task){
        String sql = "INSERT INTO tasks(";
        switch (task.getType()){
            case 0: {
                User user = userJDBCTemplate.getUserOfEmail(task.getId());
                if (user == null){
                    throw new EmptyResultDataAccessException("User not found", 128);
                }
                sql += " user_id,";
                break;
            }
            case 1: {
                SocialUser socialUser = socialUserJDBCTemplate.getSocialUser(task.getId());
                if (socialUser == null){
                    throw new EmptyResultDataAccessException("User not found", 128);
                }
                sql += " social_user_id,";
                break;
            }
            default: break;
        }
        try{
            sql += " name, time, repeat, count, notification) VALUES(?,?,?,?,?,?)";
            jdbcTemplate.update(sql, task.getId(), task.getName(), task.getTime(), task.isRepeat(), task.getCount(), task.getNotification());
            return task;
        }catch (DuplicateKeyException | EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public Task getTask(String type){


    }
}

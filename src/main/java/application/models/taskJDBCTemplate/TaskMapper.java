package application.models.taskJDBCTemplate;

import application.config.TimestampHelper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        String id = resultSet.getString("user_id");
        if (id == null){
            task.setId(resultSet.getString("social_user_id"));
            task.setType(1);
        } else {
            task.setId(id);
            task.setType(0);
        }
        task.setCount(resultSet.getInt("count"));
        task.setName(resultSet.getString("name"));
        task.setNotification(TimestampHelper.fromTimestamp(resultSet.getTimestamp("notification")));
        task.setTime(TimestampHelper.fromTimestamp(resultSet.getTimestamp("time")));
        task.setRepeat(resultSet.getBoolean("repeat"));
        return null;
    }
}

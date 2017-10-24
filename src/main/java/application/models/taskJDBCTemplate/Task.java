package application.models.taskJDBCTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Task {
    private Integer type;

    private String name;

    private String id;

    private Timestamp time;

    private boolean repeat;

    private int count;

    private Timestamp notification;


    @JsonCreator
    public Task(@JsonProperty(value = "type") Integer type,
                @JsonProperty(value = "name") String name,
                @JsonProperty(value = "id") String id,
                @JsonProperty(value = "time")Timestamp time,
                @JsonProperty(value = "repeat")boolean repeat,
                @JsonProperty(value = "count")int count,
                @JsonProperty(value = "notification")Timestamp notification) {
        this.type = type;
        this.name = name;
        this.id = id;
        this.time = time;
        this.repeat = repeat;
        this.count = count;
        this.notification = notification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getNotification() {
        return notification;
    }

    public void setNotification(Timestamp notification) {
        this.notification = notification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

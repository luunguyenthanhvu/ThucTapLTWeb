package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class Contact implements Serializable {
    private String fullName;
    private String email;
    private String topic;
    private String message;

    public Contact(String fullName, String email, String topic, String message) {
        this.fullName = fullName;
        this.email = email;
        this.topic = topic;
        this.message = message;
    }
    public Contact() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

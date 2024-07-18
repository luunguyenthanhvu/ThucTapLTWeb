package nhom55.hcmuaf.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogDTO {
    private int id;
    private String ip;
    private String level;
    private String address;
    private String national;
    private String note;
    private String preValue;
    private String currentValue;
    private String createAt;
    private String updateAt;

    public LogDTO(Log log) {
        this.id = log.getId();
        this.ip = log.getIp();
        this.level = log.getLevel().toString();
        this.address = log.getAddress();
        this.national = log.getNational();
        this.note = log.getNote();
        this.preValue = log.getPreValue();
        this.currentValue = log.getCurrentValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createAt = log.getCreateAt() != null ? log.getCreateAt().format(formatter) : null;
        this.updateAt = log.getUpdateAt() != null ? log.getUpdateAt().format(formatter) : null;
    }

    // Getters and setters if needed

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPreValue() {
        return preValue;
    }

    public void setPreValue(String preValue) {
        this.preValue = preValue;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                ", national='" + national + '\'' +
                ", note='" + note + '\'' +
                ", preValue='" + preValue + '\'' +
                ", currentValue='" + currentValue + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}


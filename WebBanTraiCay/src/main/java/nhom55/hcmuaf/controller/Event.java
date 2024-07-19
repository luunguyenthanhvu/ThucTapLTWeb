package nhom55.hcmuaf.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

  private String name;
  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate eventDate;

  public static void main(String[] args) {
    // Chuỗi JSON đầu vào (giả định)
//    String jsonInput = "{\"name\":\"Birthday Party\",\"eventDate\":\"08/12/2024\",\"cc\":\"Birthdddddd\"}";
//
//    try {
//      // Khởi tạo ObjectMapper và đăng ký JavaTimeModule
//      ObjectMapper objectMapper = new ObjectMapper();
//      objectMapper.registerModule(new JavaTimeModule());
//
//      // Chuyển đổi JSON thành đối tượng Event
//      Event event = objectMapper.readValue(jsonInput, Event.class);
//
//      // In ra kết quả
//      System.out.println("Event Name: " + event.getName());
//      System.out.println("Event Date: " + event.getEventDate());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  // Getter và Setter
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }
}

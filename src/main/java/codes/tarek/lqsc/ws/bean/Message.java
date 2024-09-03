package codes.tarek.lqsc.ws.bean;

public class Message {

  private String message;
  private String level;

  // Getter und Setter
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return "Message [message=" + message + ", level=" + level + "]";
  }

}

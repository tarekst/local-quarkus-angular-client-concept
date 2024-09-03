package codes.tarek.lqsc.controller;

import codes.tarek.lqsc.ws.MessageWebSocket;
import codes.tarek.lqsc.ws.bean.Message;
import io.quarkus.logging.Log;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.Session;

@ApplicationScoped
public class MessageController {

  @Inject
  MessageWebSocket messageWebSocket;

  public void handleMessage(Session session, Message message) {
    switch (message.getLevel()) {
      case "info":
        Log.info(message);
        messageWebSocket.sendMessage(session, "Server confirmed: " + "[" + message.getLevel().toUpperCase() + "] " + message.getMessage());
        break;
      case "warn":
        Log.warn(message);
        messageWebSocket.sendMessage(session, "Server confirmed: " + "[" + message.getLevel().toUpperCase() + "] " + message.getMessage());
        break;
      case "error":
        Log.error(message);
        messageWebSocket.sendMessage(session, "Server confirmed: " + "[" + message.getLevel().toUpperCase() + "] " + message.getMessage());
        break;
      default:
        Log.warn("Unknown level: " + message.getLevel());
        messageWebSocket.sendMessage(session, "Server could not handle level: " + message.getLevel());
    }
  }

}

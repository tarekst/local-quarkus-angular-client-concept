package codes.tarek.lqsc.ws;

import codes.tarek.lqsc.controller.MessageController;
import codes.tarek.lqsc.ws.bean.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/message-sockets/{level}")
public class MessageWebSocket {

  @Inject
  MessageController messageController;

  private ObjectMapper objectMapper = new ObjectMapper(); // JSON-Mapper

  @OnOpen
  public void onOpen(Session session) {
    Log.info("WebSocket connection opened");
  }

  @OnClose
  public void onClose(Session session) {
    Log.info("WebSocket connection closed");
  }

  @OnError
  public void onError(Session session, Throwable throwable) {
    Log.error("WebSocket error occurred", throwable);
  }

  @OnMessage
  public void onMessage(Session session, String message) {
    try {
      Message msg = objectMapper.readValue(message, Message.class);
      Log.info("Received message: " + msg.getMessage() + " with level: " + msg.getLevel());

      messageController.handleMessage(session, msg);
    } catch (Exception e) {
      Log.error("Failed to parse message", e);
    }
  }

  public void sendMessage(Session session, String message) {
    session.getAsyncRemote().sendObject(message, result -> {
      if (result.getException() != null) {
        Log.error("Unable to send WebSocket message!", result.getException());
      }
    });
  }

}

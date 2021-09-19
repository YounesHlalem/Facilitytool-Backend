package edu.ap.facilitytoolspringboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class WebSocketController {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/send/message")
    @SendTo("/chat")
    public String onReceivedMessage(String message) {
        try {
            LOG.info("Received the message: {} from the front-end and returned it back", message);
            return new SimpleDateFormat("HH:mm:ss").format(new Date() + "- " + message);
        } catch (Exception e){
            LOG.error("Couldn't receive and send the message {}", message, e);
            return "";
        }
    }
}

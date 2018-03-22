package hello.service;

import hello.model.Greeting;
import hello.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendTopicMessage(String dest,HelloMessage message){
        template.convertAndSend(dest,new Greeting(message.getName()));
    }
}

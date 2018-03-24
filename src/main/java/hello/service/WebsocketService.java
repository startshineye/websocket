package hello.service;

import hello.model.Greeting;
import hello.model.HelloMessage;
import hello.model.chat.InMessage;
import hello.model.chat.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService{

    @Autowired
    private SimpMessagingTemplate template;

    public void sendTopicMessage(String dest,HelloMessage message){
        template.convertAndSend(dest,new Greeting(message.getName()));
    }

    public void sendSingleChatMessage(InMessage message) {
        System.out.println("dest:"+"/chat/single/"+message.getTo());
        template.convertAndSend("/chat/single/"+message.getTo(),
                new OutMessage(message.getFrom()+"发送:"+message.getContent())
                );
    }
}

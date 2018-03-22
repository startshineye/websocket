package hello.controller.v2;

import hello.model.HelloMessage;
import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * SimpleMessageTemplate 灵活发送消息给固定者
 */
@Controller
public class V2reetingController {
   @Autowired
   private WebsocketService service;


   @MessageMapping("v2")
   public void greeting(HelloMessage message){
       service.sendTopicMessage("/topic/greetings",message);
   }
}

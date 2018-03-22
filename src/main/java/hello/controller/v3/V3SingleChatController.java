package hello.controller.v3;

import hello.model.chat.InMessage;
import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class V3SingleChatController {

    @Autowired
    private WebsocketService websocketService;

    @MessageMapping("/v3/singlechat")
    public void singleChat(InMessage message){
        websocketService.sendSingleChatMessage(message);
    }

}

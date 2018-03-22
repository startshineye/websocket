package hello.controller.v1;

import hello.model.Greeting;
import hello.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 使用@SendTo 固定发送给指定的订阅者
 */
@Controller
public class GreetingController {

    @MessageMapping("/v1")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);
        return new Greeting("Hello, " + message.getName() + "!");
    }
}

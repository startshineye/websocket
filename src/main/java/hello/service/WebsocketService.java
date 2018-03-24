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

    public void sendJvmInfo() {
        //可用进程数
        int availa = Runtime.getRuntime().availableProcessors();
        //空闲内存
        long free = Runtime.getRuntime().freeMemory();
        //最大内存数
        long max = Runtime.getRuntime().maxMemory();

        String content = String.format("可用进程数:%s 空闲内存:%s 最大内存数:%s",availa,free,max);
        System.out.println("信息: "+content);
        template.convertAndSend("/monitor/jvm/info",new OutMessage(content));
    }
}

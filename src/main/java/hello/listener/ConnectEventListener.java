package hello.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent>{
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("[SubscribeEventListener 监听器类]"+wrap.getCommand().getMessageType());
    }
}

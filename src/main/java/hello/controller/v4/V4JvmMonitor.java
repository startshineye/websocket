package hello.controller.v4;


import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class V4JvmMonitor {

    @Autowired
    private WebsocketService ws;

    @Scheduled(fixedRate=3000)
    public void sendJvmInfo(){
        ws.sendJvmInfo();
    }
}

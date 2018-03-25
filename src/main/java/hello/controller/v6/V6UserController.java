package hello.controller.v6;

import hello.model.chat.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class V6UserController {
    private static Map<String,String> userMap = new HashMap<String,String>();
    public static Map<String,User> onlineUserMap = new HashMap<String,User>();
    static {
        userMap.put("admin","123456");
        userMap.put("zs","123456");
        userMap.put("lisi","123456");
        userMap.put("wangwu","123456");
        onlineUserMap.put("123",new User("admin","123456"));
    }
    @RequestMapping(value = "login", method= RequestMethod.POST)
    public String login(@RequestParam(value = "username",required=true) String username, @RequestParam(value = "pwd",required = true) String pwd, HttpSession session){
        String password = userMap.get(username);
        if(pwd.equals(password)){
            User user = new User(username, pwd);
            String sessionId = session.getId();
            onlineUserMap.put(sessionId,user);
            return "redirect:/v6/chat.html";
        }else{
            return "redirect:/v6/error.html";
        }
    }
}

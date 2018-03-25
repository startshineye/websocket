package hello.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpHandShakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {

        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) serverHttpRequest;
            //获取sessionId传递
            HttpSession session =  serverRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            attributes.put("sessionId",sessionId);
            System.out.println("握手拦截器: beforeHandshake"+sessionId);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, @Nullable Exception e) {
        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) serverHttpRequest;
            //获取sessionId传递
            HttpSession session =  serverRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("握手拦截器: afterHandshake"+sessionId);
        }
    }
}

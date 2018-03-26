package hello.interceptor;

import hello.controller.v6.V6UserController;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 * 功能:频道拦截器,类似管道,获取消息的一些meta数据
 */
public class SocketChanelInterceptor extends ChannelInterceptorAdapter {
    /**
     * 实际消息发送到频道之前调用
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("==SocketChanelInterceptor==>>preSend");
        return super.preSend(message, channel);
    }

    /**
     * 消息发送立即调用
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
         //消息头访问器
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);

        if(stompHeaderAccessor.getCommand()==null) return;//避免非stomp消息类型，例如心跳检测
        String sessionId = stompHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println("==SocketChannelIntecepter ==>> postSend sessionId = "+sessionId);
        switch (stompHeaderAccessor.getCommand()){
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:

                break;

            case UNSUBSCRIBE:

                break;
            default:
                break;
        }
        super.postSend(message, channel, sent);
    }

    //断开连接
    private void disconnect(String sessionId) {
        //用户下线操作
        V6UserController.onlineUserMap.remove(sessionId);
    }
    //连接成功
    private void connect(String sessionId) {
        System.out.println("connect sessionId="+sessionId);
    }

    /**
     * 消息发送之后进行调用,是否有异常,进行数据清理
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        System.out.println("==SocketChanelInterceptor==>>afterSendCompletion");
        super.afterSendCompletion(message, channel, sent, ex);
    }
}

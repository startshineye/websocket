package hello.model.chat;

import java.util.Date;

public class InMessage {
    private String from;
    private String to;
    private String content;
    private Date timer;

    public InMessage(){

    }

    public InMessage(String content){
        this.content=content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimer() {
        return timer;
    }

    public void setTimer(Date timer) {
        this.timer = timer;
    }
}

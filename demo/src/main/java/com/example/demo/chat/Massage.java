package com.example.demo.chat;

import java.util.Date;

public class Massage {
    String text;
    String ID;
    String source;
    String sender;
    String date;
    boolean forward;
    String forwardFrom;
    String IDReplyTo;
    String replyTo;
    static int NO;

    public Massage(int NO) {
        this.NO = NO;
    }

    public Massage(String text, String source, String sender) {
        this.text = text;
        this.source = source;
        this.sender = sender;
        this.date = (new Date()).toString();
        this.forward = false;
        NO ++;
        ID = "massage" + NO;
    }

    public Massage(String text, String source, String sender, String forwardFrom) {
        this.text = text;
        this.source = source;
        this.sender = sender;
        this.date = (new Date()).toString();
        this.forward = false;
        this.forwardFrom = forwardFrom;
        NO ++;
        ID = "massage" + NO;
    }

    public Massage(String text, String source, String sender, String IDReplyTo, String replyTo) {
        this.text = text;
        this.source = source;
        this.sender = sender;
        this.IDReplyTo = IDReplyTo;
        this.replyTo = replyTo;
        this.forward = false;
        this.date = (new Date()).toString();
        NO ++;
        ID = "massage" + NO;
    }

    public Massage(String text, String ID, String source, String sender, String date, boolean forward,
                   String forwardFrom, String IDReplyTo, String replyTo) {
        this.text = text;
        this.ID = ID;
        this.source = source;
        this.sender = sender;
        this.date = date;
        this.forward = forward;
        this.forwardFrom = forwardFrom;
        this.IDReplyTo = IDReplyTo;
        this.replyTo = replyTo;
    }




    public String getText() {
        return text;
    }

    public String getID() {
        return ID;
    }

    public String getSource() {
        return source;
    }

    public String getSender() {
        return sender;
    }

    public String getDate() {
        return date;
    }

    public int isForward() {
        if(forward)
            return 1;
        else
            return 0;
    }

    public String getForwardFrom() {
        return forwardFrom;
    }

    public String getIDReplyTo() {
        return IDReplyTo;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public static int getNO() {
        return NO;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String showMassage(String ID) {
        String output = "";
        if(sender.equals(ID))
            output += "you:\n";
        else
            output += sender + ":\n";
        output += /*"\t" +*/ text + "\n";
        if(replyTo.length() > 4)
            output += "reply to " + replyTo + "\n";
        output += date;
        return  output;
    }

    public String replyTo() {
        String output = sender + ": ";
        if(text.length() > 25)
            output += text.substring(22) + "...";
        else
            output += text;
        return output;
    }
}

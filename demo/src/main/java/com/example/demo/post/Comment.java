package com.example.demo.post;

import java.util.Date;

public class Comment {
    String text;
    String sender;
    String ID;
    String source;
    String date;
    int likesNumber;
    String likedBy;
    int commentsNumber;
    String comments;
    static int NO;


    public Comment(int NO) {
        this.NO = NO;
    }

    public Comment(String text, String postedBy, String source) {
        this.text = text;
        this.sender = postedBy;
        this.date = (new Date()).toString();
        this.likesNumber = 0;
        this.commentsNumber = 0;
        this.source = source;
        NO ++;
        this.ID = "comment" + NO;
    }

    public Comment(String text, String sender, String ID, String source, String date, int likesNumber, String likedBy,
                   int commentsNumber, String comments) {
        this.text = text;
        this.sender = sender;
        this.ID = ID;
        this.source = source;
        this.date = date;
        this.likesNumber = likesNumber;
        this.likedBy = likedBy;
        this.commentsNumber = commentsNumber;
        this.comments = comments;
    }

    public boolean like(String ID){
        if(likesNumber == 0){
            likedBy = ID;
            likesNumber ++;
            return true;
        }
        for (String s : likedBy.split(", ")) {
            if(s.equals(ID))
                return false;
        }
        likedBy += ", " + ID;
        likesNumber ++;
        return true;
    }

    public String showLikes(){
        String output = "";
        if(likesNumber == 0)
            output = "no one has liked this post."+ "\n";
        else
            for (String s : likedBy.split(", ")) {
                output += s + "\n";
            }
        return output;
    }

    public String show() {
        String output = sender + "\n";
        output += this.text + "\n";
        output += likesNumber + " likes" + "    ";
        output += commentsNumber + " comments" + "\n";
        output += date;
        return output;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public String getID() {
        return ID;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public String getLikedBy() {
        return likedBy;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public String getComments() {
        return comments;
    }

    public static int getNO() {
        return NO;
    }
}

package com.example.demo.post;


import java.util.Date;

public class Post {
    String text;
    String postedBy;
    String ID;
    String date;
    int likesNumber;
    String likedBy;
    int commentsNumber;
    String comments;
    static int NO;

    public Post(int NO) {
        this.NO = NO;
    }

    public Post(String text, String postedBy) {
        this.text = text;
        this.postedBy = postedBy;
        date = (new Date()).toString();
        likedBy = "";
        likesNumber = 0;
        commentsNumber = 0;
        comments = "";
        NO ++;
        ID = "post" + NO;
    }

    public Post(String text, String postedBy, String ID, String date, int likesNumber, String likedBy, int commentsNumber) {
        this.text = text;
        this.postedBy = postedBy;
        this.ID = ID;
        this.date = date;
        this.likesNumber = likesNumber;
        this.likedBy = likedBy;
        this.commentsNumber = commentsNumber;
    }

    public String getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public String getPostedBy() {
        return postedBy;
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

    public String show() {
        String output = postedBy + "\n";
        output += this.text + "\n";
        output += likesNumber + " likes" + "    ";
        output += commentsNumber + " comments" + "\n";
        output += date;
        return output;
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

    public String showComments(){
        String output = "";
        /*if(likesNumber == 0)
            output = "no comment exist.";
        else {
            output = "" + commentsNumber;
            for (Comment comment : comments) {
                output += "\n" + comment.toString();
            }
        }*/
        return output;
    }

    public boolean checkLike(String id) {
        if(likedBy == null)
            return false;
        for (String s : likedBy.split(", ")) {
            if (s.equals(id)){
                return true;
            }
        }
        return false;
    }

    public void newComment(String id) {
        if(commentsNumber == 0){
            comments = ID;
            commentsNumber ++;
            return;
        }

        comments += ", " + ID;
        commentsNumber ++;
        return;
    }
}

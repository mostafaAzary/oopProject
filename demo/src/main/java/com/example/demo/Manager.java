package com.example.demo;

import com.example.demo.account.Account;
import com.example.demo.account.CommercialAccount;
import com.example.demo.chat.Group;
import com.example.demo.chat.Massage;
import com.example.demo.chat.PrivateChat;
import com.example.demo.database.ReadingData;
import com.example.demo.database.WritingData;
import com.example.demo.post.Comment;
import com.example.demo.post.Post;
import java.sql.SQLException;
import java.util.ArrayList;

public class Manager {
    WritingData writingData;
    ReadingData readingData;
    String ID;

    public Manager(String ID) {
        this.ID = ID;
        this.writingData = new WritingData();
        this.readingData = new ReadingData();
        readingData.setNumber();
    }

    public Manager(){
        this.writingData = new WritingData();
        this.readingData = new ReadingData();
        readingData.setNumber();
    }

    private boolean checkFormat(String string) {
        if(string.replaceAll("[A-Z0-9]|[a-z]|_", "").equals("") & string.length() >= 8)
            return true;
        else
            return false;
    }

    public String login(String userName, String password) {
        if(userName.length() == 0)
            return "Username must be entered.";
        else if(password.length() == 0)
            return "Password must be entered.";
        else if(!checkFormat(userName))
            return ("Username format is invalid.");
        else if(!readingData.checkID(userName))
            return ("No user exists with this username.");
        else {
            try {
                if(readingData.checkAccount(userName, password)) {
                    this.ID = userName;
                    return ("ok");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Wrong password.";
    }

    public String newAccount(String username, String password) {
        if(username.length() == 0)
            return "Username must be entered.";
        else if(password.length() == 0)
            return "Password must be entered.";
        if(!checkFormat(username))
            return "Username format is invalid.";
        if(readingData.checkID(username))
            return "There is an account with this Username.";
        if(!checkFormat(password))
            return "Password format is invalid.";
        Account account = new Account(username, password);
        writingData.newAccount(account);
        return "Register successful.";
    }



    public void showProfile() {
        Account account = readingData.getAccount(this.ID);
        String output = account.showProfile();
        System.out.println(output);
    }

    public boolean showProfile(String input) {
        String ID = input.split("\\s")[2];
        if(input.split("\\s").length != 3){
            System.out.println("ERROR!");
            return false;
        } else if(readingData.checkID(ID)){
            Account account = readingData.getAccount(ID);
            String output = account.showProfile();
            System.out.println(output);
            return true;
        } else {
            System.out.println("There is no com.example.phase2.account with this ID.");
            return false;
        }
    }

    public void blockList() {
        String output =readingData.getAccountsBlockList(this.ID);
        System.out.print(output);
    }

    public void followingList(String ID) {
        Account account = readingData.getAccount(ID);
        String output = account.followingList();
        System.out.print(output);
    }

    public void followerList(String ID) {
        Account account = readingData.getAccount(ID);
        String output = account.followerList();
        System.out.print(output);
    }

    public void groupList() {
        Account account = readingData.getAccount(this.ID);
        String output = account.groupList();
        System.out.print(output);
    }

    public void privateChatList() {
        Account account = readingData.getAccount(this.ID);
        String output = account.privateChatList();
        System.out.print(output);
    }


    public boolean checkGroupID(String ID) {
        if(!checkFormat(ID)){
            System.out.println("ID format is invalid. \n"
                    +"try again.");
            return false;
        } else if(readingData.checkGroupID(ID)){
            System.out.println("There is a group with this ID. \n" +
                    "Choose another ID and try again.");
            return false;
        } else
            return true;
    }

    public boolean checkMember(String id, String members) {
        if(!readingData.checkID(id)){
            System.out.println("no user exists with this ID.");
            return false;
        } else if(!readingData.checkFollower(ID, id)){
            System.out.println("this user isn't your follower. \n" +
                    "you just can add user which is your follower.");
            return false;
        }
        for (String member : members.split(", ")) {
            if(member.equals(id)){
                System.out.println("this user was added to group.");
                return false;
            }
        }
        return true;
    }

    public void newGroup(String name, String ID, String admin, String members, int membersNumber) {
        Group group = new Group(name, ID, admin, membersNumber, members);
        writingData.newGroup(group);
        System.out.println("group was created successfully.");
        for (String member : members.split(", ")) {
            Account account = readingData.getAccount(member);
            account.addGroup(ID);
            writingData.addGroupTOAccount(account);
        }

    }

    public void newPost(String postBy, String text) {
        Account account = readingData.getAccount(this.ID);
        Post post = new Post(text, postBy);
        account.newPost(post.getID());
        writingData.addPostToAccount(account);
        writingData.newPost(post);
        System.out.println("com.example.phase2.post posted successfully.");
    }


    public void follow(String id) {
        Account account = readingData.getAccount(this.ID);
        if(account.checkFollowing(id)){
            //System.out.println(com.example.phase2.account.checkFollowing(id));
            System.out.println("You were already following this com.example.phase2.account.");
        } else {
            System.out.println("This com.example.phase2.account has been added to your followings list.");
            writingData.addFollowing(id, readingData.getAccount(this.ID));
            writingData.addFollower(this.ID, readingData.getAccount(id));
        }
    }

    public void unfollow(String id) {
        Account account = readingData.getAccount(this.ID);
        if(account.checkFollowing(id)){
            System.out.println("This com.example.phase2.account has been removed from your following list.");
            writingData.removeFollowing(id, readingData.getAccount(this.ID));
            writingData.removeFollower(this.ID, readingData.getAccount(id));
        } else {
            System.out.println("You are not following this com.example.phase2.account.");
        }
    }

    public void block(String id) {
        Account account = readingData.getAccount(this.ID);
        if(!account.checkBlock(id)){
            System.out.println("This com.example.phase2.account has already been blocked.");
            account.block(id);
            writingData.block(account);
        } else {
            System.out.println("This com.example.phase2.account was blocked.");
        }
    }

    public void back(){
        System.out.println("You are back to the main menu.");
    }


    public boolean checkAdmin(String groupID) {
        Group group = readingData.getGroup(groupID);
        if(group.getAdmin().equals(this.ID)){
            System.out.println("enter new name:");
            return true;
        }
        else{
            System.out.println("Only the admin can change the name of the group.");
            return false;
        }
    }

    public void changGroupName(String groupID, String name) {
        Group group = readingData.getGroup(groupID);
        group.setName(name);
        writingData.changGroupName(group);
        System.out.println("Group's name changed successfully.");
    }

    public void removeMember(String groupID, String ID) {
        Group group = readingData.getGroup(groupID);
        if(group.getAdmin().equals(this.ID)){
            if(!readingData.checkID(ID)){
                System.out.println("no user exists with this ID.");
                return;
            } else {
                if(ID.equals(group.getAdmin()))
                    System.out.println("Admin cannot leave the group.");
                else if(group.checkMember(ID)){
                    group.removeMember(ID);
                    writingData.removeMember(group);
                    System.out.println("This com.example.phase2.account has been successfully removed.");
                } else
                    System.out.println("This com.example.phase2.account is not a member of this group.");
            }
        } else
            System.out.println("Only the admin can remove the member from the group.");
    }

    public void bonMember(String groupID, String ID) {
        Group group = readingData.getGroup(groupID);
        if(group.getAdmin().equals(this.ID)) {
            if (!readingData.checkID(ID)) {
                System.out.println("no user exists with this ID.");
                return;
            } else {
                if (group.checkMember(ID)) {
                    if (group.checkBon(ID))
                        System.out.println("This user has already been boned.");
                    else {
                        writingData.bonMember(group);
                        System.out.println("This user was boned.");
                    }
                } else
                    System.out.println("This com.example.phase2.account is not a member of this group.");
            }
        } else
            System.out.println("Only the admin can remove the member from the group.");
    }

    public void memberList(String groupID) {
        Group group = readingData.getGroup(groupID);
        String members = group.showMembers();
        System.out.println(members);
    }

    public void addMember(String input, String groupID) {
        String ID = input.split("\\s")[2];
        Group group = readingData.getGroup(groupID);
        if(group.getAdmin().equals(this.ID)) {
            if (checkMember(ID, group.getMembers())) {
                group.addMember(ID);
                Account account = readingData.getAccount(ID);
                account.addGroup(groupID);
                writingData.addMember(group, account);
            }
        }else
            System.out.println("Only the admin can add member to group.");
    }



    public PrivateChat getPrivateChat(String id) {
        PrivateChat chat = readingData.getPrivateChat(ID, id);
        if (chat == null){
            chat = new PrivateChat(this.ID, id);
            writingData.newPrivateChat(chat);
            Account account1 = readingData.getAccount(this.ID),
                    account2 = readingData.getAccount(id);
            account1.addPrivateChat(chat.getID());
            account2.addPrivateChat(chat.getID());
            writingData.addPrivateChat(account1);
            writingData.addPrivateChat(account2);
            return chat;
        }
        else
            return chat;
    }

    public boolean checkBlockAccount(String id) {
        Account account1 = readingData.getAccount(this.ID),
                account2 = readingData.getAccount(id);
        if(account1.checkBlock(id)){
            System.out.println("You have blocked this com.example.phase2.account.");
            return false;
        }
        else
        if(account2.checkBlock(this.ID)){
            System.out.println("This com.example.phase2.account has blocked you.");
            return false;
        }
        else
            return true;
    }

    public void newMassage(String chatID, String text) {
        Massage massage = new Massage(text, chatID, this.ID);
        writingData.newMassage(massage);
    }

    public boolean showMassages(String sourceID) {
        ArrayList<Massage> massages = readingData.showMassages(sourceID);
        String output = "";
        boolean condition = false;
        if(massages == null)
            output = "No massage exist.\n";
        else{
            int i = 1;
            for (Massage massage : massages) {
                if(massage.getText() != null){
                    output += (i) + ". " + massage.showMassage(this.ID) + "\n";
                    i++;
                    condition = true;
                }
            }
            if(i == 1)
                output = "No massage exist.\n";
        }
        System.out.print(output);
        return condition;
    }

    public void enterMassageNumber(){
        System.out.println("Enter the message number:");
    }

    public void enterPostNumber() {
        System.out.println("Enter the com.example.phase2.post number:");
    }

    public String getMassageID(String sourceID, int number) {
        ArrayList<Massage> massages = readingData.showMassages(sourceID);
        String output = "error";
        if(massages == null | massages.size() == 0)
            System.out.println("No massage exist.");
        else if(number > massages.size())
            System.out.println("No massage exist with this number.");
        else if(number < 0)
            System.out.println("ERROR!");
        else{
            Massage massage = massages.get(number - 1);
            output =massage.getID();
        }
        return output;
    }

    public boolean checkEditAndDelete(String sourceID, String massageID) {
        Massage massage = readingData.getMassages(massageID);
        if(!massage.getSender().equals(this.ID)){
            System.out.println("you can only edit or delete the messages which you sent.");
            return  false;
        } else
        if(massage.isForward() == 1){
            System.out.println("Forwarded!");
            return false;
        } else if(!readingData.checkLastMassages(sourceID, this.ID, massageID)){
            System.out.println("You only can edit or delete the last 5 messages.");
            return false;
        }
        else
            return true;
    }

    public void editMassage(String massageID, String newText) {
        Massage massage = readingData.getMassages(massageID);
        massage.setText(newText);
        String newReplyTo = massage.replyTo();
        writingData.editMassage(massageID, newText, newReplyTo);
        System.out.println("Message edited successfully");
    }

    public void deleteMassage(String massageID) {
        writingData.deleteMassage(massageID);
        System.out.println("Message deleted successfully.");
    }

    public void newReplyMassage(String sourceID, String massageID, String text) {
        Massage massage = readingData.getMassages(massageID);
        String replyTo = massage.replyTo();
        Massage replyMassage = new Massage(text, sourceID, this.ID, massageID, replyTo);
        writingData.newMassage(replyMassage);
    }

    public void newForwardMassage(String ID, String massageID) {
        if(readingData.checkGroupID(ID)){
            Massage massage = readingData.getMassages(massageID);
            Massage replyMassage = new Massage(massage.getText(), ID, this.ID, massage.getSender());
            writingData.newMassage(replyMassage);
            writingData.forwardedMassage(massageID);
        }
        if(readingData.checkID(ID)){
            Massage massage = readingData.getMassages(massageID);
            Massage replyMassage = new Massage(massage.getText(), ID, this.ID, massage.getSender());
            writingData.newMassage(replyMassage);
            writingData.forwardedMassage(massageID);
        }
    }

    public boolean group(String input) {
        String ID = input.split("\\s")[1];
        if(input.split("\\s").length != 2){
            System.out.println("ERROR!");
            return false;
        } else if(readingData.checkGroupID(ID)){
            System.out.println(1);
            Group group = readingData.getGroup(ID);
            if(group.checkMember(this.ID)){
                System.out.println(2);
                String output = group.show();
                System.out.println(output);
                return true;
            }
        }
        System.out.println("You are not a member of a group with this ID.");
        return false;
    }

    public boolean showPosts(String ID) {
        ArrayList<Post> posts = readingData.showPosts(ID);
        String output = "";
        boolean condition = true;
        if(posts == null){
            output = "No posts exist.\n";
            condition = false;
        }
        else{
            int i = 1;
            for (Post post : posts) {
                output += (i) + ". " + post.show() + "\n";
                i++;
            }
            if(i == 1){
                output = "No posts exist.\n";
                condition = false;
            }
        }
        System.out.print(output);
        return condition;
    }

    public void likePost(String postID) {
        //System.out.println(postID);
        Post post = readingData.getPost(postID);
        if(post.checkLike(this.ID))
            System.out.println("You have already liked this com.example.phase2.post.");
        else{
            post.like(this.ID);
            writingData.likeMassage(post);
            System.out.println("com.example.phase2.post liked successfully.");
        }
    }

    public String getPostID(String ID, int number) {
        ArrayList<Post> posts = readingData.showPosts(ID);
        String output = "error";
        if(posts == null | posts.size() == 0)
            System.out.println("No posts exist.");
        else if(number > posts.size())
            System.out.println("No com.example.phase2.post exist with this number.");
        else if(number < 0)
            System.out.println("ERROR!");
        else{
            Post post = posts.get(number - 1);
            output = post.getID();
        }
        return output;
    }

    public void showLikes(String postID) {
        Post post = readingData.getPost(postID);
        String output = post.showLikes();
        System.out.print(output);
    }

    public boolean showComments(String postID) {
        ArrayList<Comment> comments = readingData.showComments(postID);
        String output = "";
        boolean condition = false;
        if(comments == null)
            output = "No comment exist.\n";
        else{
            int i = 1;
            for (Comment comment : comments) {
                if(comment.getText() != null){
                    output += (i) + ". " + comment.show() + "\n";
                    i++;
                    condition = true;
                }
            }
            if(i == 1)
                output = "No comment exist.\n";
        }
        System.out.print(output);
        return condition;
    }

    public void newComment(String postID, String text) {
        Comment comment = new Comment(text, this.ID, postID);
        Post post = readingData.getPost(postID);
        post.newComment(this.ID);
        writingData.newComment(comment, post);
    }


    //public boolean changGroupID(String groupID, String newID) {
       /* Group group = readingData.getGroup(groupID);
        if(groupID.equals(newID)){
            System.out.println("بی تغییر");
            return true;
        }
        if(checkGroupID(newID)){
            writingData.changGroupID(group);
            System.out.println("successfully.");
            return true;
        } else return false;
    }*/
}

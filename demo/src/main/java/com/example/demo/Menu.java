package com.example.demo;

import com.example.demo.chat.PrivateChat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Menu {
    Manager manager;
    Scanner scanner = new Scanner(System.in);
    int step;
    String input;
    Stage stage;
    Scene scene;
    Parent root;

    
    //constructor
    public Menu() {
        this.manager = new Manager();
    }


    //login
    @FXML
    private Label loginLabel;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;

    public void buttonLogin(ActionEvent e) throws IOException {
        String string = manager.login(loginUsername.getText(), loginPassword.getText());
        if(string.equals("ok")){
            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            setProfile();
            stage.show();
        }
        else loginLabel.setText(string);
    }


    //register
    @FXML
    private Label registerLabel;
    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;

    @FXML
    protected void buttonRegister(){
        String string = manager.newAccount(registerUsername.getText(), registerPassword.getText());
        registerLabel.setText(string);
    }


    //profile
    @FXML
    private Label profileUsername;
    @FXML
    private Label postsNumber;
    @FXML
    private Label followersNumber;
    @FXML
    private Label followingsNumber;

    @FXML
    protected void setProfile(){
        profileUsername.setText(manager.ID);
        postsNumber.setText(String.valueOf(manager.readingData.getAccount(manager.ID).getPostsNumber()));
        followersNumber.setText(String.valueOf(manager.readingData.getAccount(manager.ID).getFollowers()));
        followingsNumber.setText(String.valueOf(manager.readingData.getAccount(manager.ID).getFollowings()));
    }








    private void mainMenu() {
            if (input.equals("block list"))
                manager.blockList();
            else if (input.equals("followings list"))
                manager.followingList(manager.ID);
            else if (input.equals("followers list"))
                manager.followerList(manager.ID);
            else if (input.equals("group list"))
                manager.groupList();
            else if (input.equals("private chat list"))
                manager.privateChatList();
            else if (input.startsWith("show profile ")){
                if(manager.showProfile(input)){
                    showProfile();
                }
            } else if (input.startsWith("group ")){
                if(manager.group(input)){
                    groupMenu(input.split("\\s")[1]);
                }
            } else if (input.startsWith("logout")){
                return;
            }
            else if (input.equals("new group"))
                newGroup();
            else if (input.equals("create post"))
                createPost();
            else if (input.equals("show posts"))
                postMenu(manager.ID);
    }

    private void createPost() {
        String text = "", postBy = manager.ID, string;
        System.out.println("With command add text, you can type the text of the post, " +
                "and with command post, the relevant content will be posted.");
        while(true){
            string = scanner.nextLine();
            if(string.equals("add text"))
                break;
            else
                System.out.println("invalid command.");
        }
        while(true){
            string = scanner.nextLine();
            if(string.equals("post")){
                if(text.equals(""))
                    System.out.println("The text of the post should not be empty.");
                else {
                    manager.newPost(postBy, text);
                    return;
                }
            } else if(text.equals(""))
                text = string;
            else
                text += "\n" + string;
        }
    }

    private void newGroup() {
        System.out.println("enter the group's name:");
        String name, admin = manager.ID;
        name = scanner.nextLine();
        System.out.println("enter the group's ID:");
        String ID;
        while(true){
            ID = scanner.nextLine();
            if(manager.checkGroupID(ID))
                break;
        }
        String members = admin;
        System.out.println("enter the group's members:");
        input = scanner.nextLine();

        int membersNumber = 1;
        while (!input.equals("finish")){
            if(manager.checkMember(input, members)){
                members += ", " + input;
                membersNumber ++;
            }
            input = scanner.nextLine();
        }
        manager.newGroup(name, ID, admin, members, membersNumber);
    }



    private void showProfile() {
        String ID = input.split("\\s")[2];
        while (true) {
            input = scanner.nextLine();
            if (input.equals("followings list"))
                manager.followingList(ID);
            else if (input.equals("followers list"))
                manager.followerList(ID);
            else if (input.equals("follow"))
                manager.follow(ID);
            else if (input.equals("block"))
                manager.block(ID);
            else if (input.equals("unfollow"))
                manager.unfollow(ID);
            else if (input.equals("chat")){
                if(manager.checkBlockAccount(ID)){
                    PrivateChat chat = manager.getPrivateChat(ID);
                    chatMenu(chat.getID());
                }
            } else if (input.equals("view posts")){
                //77777777
                step = 4;
                return;
            } else if (input.startsWith("back")){
                manager.back();
                step = 2;
                return;
            } else
                System.out.println("invalid command");
        }
    }



    private void postMenu(String ID) {
        if(manager.showPosts(ID)){
            while (true) {
                input = scanner.nextLine();
                if (input.equals("like"))
                    likePost(ID);
                else if (input.equals("show likes"))
                    showLike(ID);
                else if (input.equals("comment"))
                    comment(ID);
                else if (input.equals("show comments"))
                    showComments(ID);
                else if (input.startsWith("back"))
                    return;
                else
                    System.out.println("invalid command");
            }
        }
    }

    private void comment(String ID) {
        if (manager.showPosts(ID)) {
            manager.enterPostNumber();
            int number = Integer.parseInt(scanner.nextLine());
            String postID = manager.getPostID(ID, number);
            if (postID.startsWith("post")){
                String text = enterMassageText();
                manager.newComment(postID, text);
            }
        }
    }

    private void showComments(String postID) {
        if(manager.showComments(postID)){
            /*while (true) {
                input = scanner.nextLine();
                if (input.replace(" ", "").equals("help"))
                    manager.postMenuHelp();
                else if (input.equals("like"))
                    likePost(ID);
                else if (input.equals("show likes"))
                    showLike(ID);
                else if (input.equals("comment"))
                    comment(ID);
                else if (input.equals("show comments"))
                    showComments(ID);
                else if (input.startsWith("back"))
                    return;
                else
                    System.out.println("invalid command");
            }
        */}
    }

    private void showLike(String ID) {
        if (manager.showPosts(ID)) {
            manager.enterPostNumber();
            int number = Integer.parseInt(scanner.nextLine());
            String postID = manager.getPostID(ID, number);
            if (!postID.startsWith("post"))
                return;
            else
                manager.showLikes(postID);
        }
    }

    private void likePost(String ID) {
        if (manager.showPosts(ID)) {
            manager.enterPostNumber();
            int number = Integer.parseInt(scanner.nextLine());
            String postID = manager.getPostID(ID, number);
            if (!postID.startsWith("post"))
                return;
            else
                manager.likePost(postID);
        }
    }


    private void chatMenu(String chatID) {
        while (true) {
            input = scanner.nextLine();
            if (input.equals("new massage"))
                newMassage(chatID);
            else if (input.equals("show massages"))
                manager.showMassages(chatID);
            else if (input.equals("edit"))
                edit(chatID);
            else if (input.equals("delete"))
                delete(chatID);
            else if (input.equals("reply"))
                reply(chatID);
            else if (input.equals("forward"))
                forward(chatID);
            else if (input.startsWith("back"))
                return;
            else
                System.out.println("invalid command");
        }
    }

    private void forward(String sourceID) {
        if (manager.showMassages(sourceID)) {
            manager.enterMassageNumber();
            int number = scanner.nextInt();
            String massageID = manager.getMassageID(sourceID, number);
            if(!massageID.equals("error")) {
                System.out.println("enter the ID of the group or account you want to forward this message to:");
                String ID = scanner.nextLine();
                manager.newForwardMassage(ID, massageID);
            }
        }
    }

    private void reply(String sourceID) {
        if (manager.showMassages(sourceID)) {
            manager.enterMassageNumber();
            int number = scanner.nextInt();
            String massageID = manager.getMassageID(sourceID, number);
            if(massageID.equals("error"))
                return;
            String text = enterMassageText();
            manager.newReplyMassage(sourceID, massageID, text);
        }
    }

    private void edit(String sourceID) {
        String string;
        if(manager.showMassages(sourceID)){
            manager.enterMassageNumber();
            int number = scanner.nextInt();
            String massageID = manager.getMassageID(sourceID, number);
            if(!massageID.equals("error"))
                if(manager.checkEditAndDelete(sourceID, massageID)){
                    System.out.println("Enter the new message text:");
                    String newText = "";
                    while(true){
                        string = scanner.nextLine();
                        if(string.equals("send")){
                            if(newText.equals(""))
                                System.out.println("The message text must not be empty.");
                            else{
                                manager.editMassage(massageID, newText);
                                break;
                            }
                        }
                        newText += string;
                    }
                }
        }
    }

    private void delete(String sourceID) {
        if (manager.showMassages(sourceID)) {
            manager.enterMassageNumber();
            int number = Integer.parseInt(scanner.nextLine());
            String massageID = manager.getMassageID(sourceID, number);
            if (massageID.equals("error"))
                return;
            else if (manager.checkEditAndDelete(sourceID, massageID)) {
                manager.deleteMassage(massageID);
            } else
                return;
        }
    }

    private void newMassage(String chatID) {
        String text = enterMassageText();
        manager.newMassage(chatID, text);
    }

    private String enterMassageText(){
        System.out.println("Write the text of the message:");
        String text = "";
        while(true){
            input = scanner.nextLine();
            if(input.equals("send")){
                if(text.equals(""))
                    System.out.println("The message text must not be empty.");
                else
                    break;
            }
            if(text.equals(""))
                text += input;
            else
                text += "\n" + input;
        }
        return text;
    }

    private String enterCommentText(){
        System.out.println("Write the text of the comment:");
        String text = "";
        while(true){
            input = scanner.nextLine();
            if(input.equals("send")){
                if(text.equals(""))
                    System.out.println("The comment text must not be empty.");
                else
                    break;
            }
            if(text.equals(""))
                text += input;
            else
                text += "\n" + input;
        }
        return text;
    }




    private void groupMenu(String ID){
        while (true) {
            input = scanner.nextLine();
            if (input.equals("chang group’s name"))
                changGroupName(ID);
            /*else if (input.equals("change group’s ID"))
                changGroupID(ID);*/
            else if (input.equals("remove member"))
                removeMember(ID);
            else if (input.equals("members list"))
                manager.memberList(ID);
            else if (input.equals("add members"))
                manager.addMember(input, ID);
            else if (input.equals("bon member"))
                bonMember(ID);
            else if (input.equals("chat"))
                chatMenu(ID);
            else if (input.equals("back"))
                return;
            else
                System.out.println("invalid command");
        }
    }


    private void changGroupID(String ID) {
        if(manager.checkAdmin(ID)){
            while (true){
                input = scanner.nextLine();
                //if(manager.changGroupID(ID, input))
                return;
            }
        }
    }

    private void bonMember(String ID) {
        if(manager.checkAdmin(ID)){
            input = scanner.nextLine();
            manager.bonMember(ID, input);
        }
    }

    private void removeMember(String ID) {
        if(manager.checkAdmin(ID)){
            input = scanner.nextLine();
            manager.removeMember(ID, input);
        }
    }

    private void changGroupName(String ID) {
        if(manager.checkAdmin(ID)){
            input = scanner.nextLine();
            manager.changGroupName(ID, input);
        }
    }
}


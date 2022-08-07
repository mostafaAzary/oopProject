package com.example.demo.database;


import com.example.demo.account.Account;
import com.example.demo.account.CommercialAccount;
import com.example.demo.chat.Group;
import com.example.demo.chat.Massage;
import com.example.demo.chat.PrivateChat;
import com.example.demo.post.Comment;
import com.example.demo.post.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WritingData {

    Connection connection;

    public WritingData() {
        {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root",
                        "ma400105358");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //start();
    }

    public void newAccount(Account account) {
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(
                    "INSERT INTO accounts(ID, password)" +
                            "values ('" + account.getID() + "', '" + account.getPassword() + "');");
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newCommercialAccount(CommercialAccount commercialAccount) {
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(
                    "INSERT INTO commercialAccounts(ID, password)" +
                            "values ('" + commercialAccount.getID() + "', '" + commercialAccount.getPassword() + "');");
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void newGroup(Group group) {
        String command = "INSERT INTO allGroups(groupName, ID, groupAdmin, membersNumber, members)" +
                "values ('" + group.getName() + "', '"+ group.getID() + "', '" +
                group.getAdmin() + "', '" + group.getMembersNumber() +"', '" + group.getMembers() + "');";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newPost(Post post) {
        String command = "INSERT INTO posts" +
                " values ('" + post.getText() + "', '"+ post.getPostedBy() + "', '"+ post.getID() +"', '"+
                post.getDate() +"', "+ post.getLikesNumber() +", '"+ post.getLikedBy() +"', "+ post.getCommentsNumber()
                +", '" + post.getComments() + "', " + post.getNO() + ");\n",
                command2 = "UPDATE num " +
                        "SET lastNumber = lastNumber + 1 " +
                        "WHERE titre = 'com.example.phase2.post';";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPostToAccount(Account account) {
        String command = "UPDATE accounts" +
                " SET postsNumber = postsNumber + 1 " +
                " WHERE ID = '" + account.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void likeMassage(Post post) {
        String command = "UPDATE posts" +
                " SET likesNumber = likesNumber + 1, " +
                "likedBy = '" +post.getLikedBy() + "'" +
                " WHERE ID = '" + post.getID() + "';";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void newMassage(Massage massage) {
        String command = "INSERT INTO massages" +
                " values ('" + massage.getText() + "', '"+ massage.getID() + "', '"+ massage.getSource() +"', '"+
                massage.getSender() +"', '"+ massage.getDate() +"', "+ massage.isForward() +", '"+ massage.getForwardFrom()
                +"', '"+
                massage.getIDReplyTo() + "', '"+ massage.getReplyTo() + "', " + massage.getNO() + ");\n",
                command2 = "UPDATE num " +
                        "SET lastNumber = lastNumber + 1 " +
                        "WHERE titre = 'massage';";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editMassage(String massageID, String newText, String newReplyTo) {
        String command = "UPDATE massages" +
                " SET massageText = '" + newText + "' " +
                " WHERE ID = '" + massageID + "';",
                command2 = "UPDATE massages " +
                        "SET replyTo = '" + newReplyTo + "'" +
                        "WHERE IDReplyTo = '" + massageID + "';";
        System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMassage(String massageID) {
        String command = "DELETE FROM massages" +
                " WHERE ID = '" + massageID + "';",
                command2 = "UPDATE massages " +
                        "SET replyTo = '', IDReplyTo = '' " +
                        "WHERE IDReplyTo = '" + massageID + "';";
        //System.out.println(command2);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void forwardedMassage(String massageID) {
        String command = "UPDATE massages" +
                " SET forward = " + 1 +
                " WHERE ID = '" + massageID + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void newPrivateChat(PrivateChat chat) {
        String command = "INSERT INTO privateChats(accountID1, accountID2, ID) " +
                "values ('" + chat.getAccountID1() + "', '"+ chat.getAccountID2() + "', '"+ chat.getID() + "');";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPrivateChat(Account account) {
        String command = "UPDATE accounts" +
                " SET followersNumber = followersNumber + 1, " +
                "privateChats = '" + account.getPrivateChats() + "'" +
                " WHERE ID = '" + account.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFollowing(String followingID, Account account) {
        try {
            account.addFollowing(followingID);
            String command = "UPDATE accounts" +
                    " SET followingsNumber = followingsNumber + 1, " +
                    "followings = '" + account.getFollowings() + "'" +
                    " WHERE ID = '" + account.getID() + "';";
            //System.out.println("\n"+command);
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFollower(String followerID, Account account) {
        try {
            account.addFollower(followerID);
            String command = "UPDATE accounts" +
                    " SET followersNumber = followersNumber + 1, " +
                    "followers = '" + account.getFollowers() + "'" +
                    " WHERE ID = '" + account.getID() + "';";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFollowing(String followingID, Account account) {
        try {
            account.removeFollowing(followingID);
            String command = "UPDATE accounts" +
                    " SET followingsNumber = followingsNumber - 1, " +
                    "followings = '" + account.getFollowings() + "'" +
                    " WHERE ID = '" + account.getID() + "';";
            //System.out.println("\n"+command);
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFollower(String followerID, Account account) {
        try {
            account.removeFollower(followerID);
            String command = "UPDATE accounts" +
                    " SET followersNumber = followersNumber - 1, " +
                    "followers = '" + account.getFollowers() + "'" +
                    " WHERE ID = '" + account.getID() + "';";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void block(Account account) {
        try {
            String command = "UPDATE accounts" +
                    " SET blocks = '" + account.getBlocks() + "' " +
                    " WHERE ID = '" + account.getID() + "';";
            //System.out.println(command);
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void changGroupName(Group group) {
        String command = "UPDATE allGroups" +
                " SET groupName = '" + group.getName() + "'  " +
                " WHERE ID = '" + group.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroupTOAccount(Account account) {
        String command = "UPDATE accounts" +
                " SET groupsID = '" + account.getGroups() + "' " +
                " WHERE ID = '" + account.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMember(Group group) {
        String command = "UPDATE allGroups" +
                " SET membersNumber = membersNumber - 1, " +
                "members = '" + group.getMembers() + "'" +
                " WHERE ID = '" + group.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bonMember(Group group) {
        String command = "UPDATE allGroups" +
                " SET bonMembers = '" + group.getBonMembers() + "'" +
                " WHERE ID = '" + group.getID() + "';";
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public void changGroupID(Group group) {}


    public void start(){
        String command = "UPDATE accounts SET blocks = null " +
                //", followers = 'mostafaAzari' WHERE ID = 'Azari1381'";
                //"DELETE FROM accounts;";
                "WHERE ID = 'mostafaAzari';";

        String createTable =
                "CREATE TABLE if not exists massages (\n" +
                        "massageText text, ID text , source text, sender text, date date, forward BIT(1), forwardFrom text, " +
                        "IDReplyTo text, replyTo text );\n" +
                        "CREATE TABLE if not exists privateChats (\n" +
                        "accountID1 text, accountID2 text, ID text );\n" +
                        "CREATE TABLE if not exists num (\n" +
                        "titre text, lastNumber int );\n" +
                        "CREATE TABLE IF NOT EXISTS accounts(\n" +
                        "ID TEXT, password TEXT, followersNumber int, followers TEXT, followingsNumber int, followings TEXT, " +
                        "blocks TEXT, postsNumber int, posts TEXT,\tgroupsID TEXT, privateChats TEXT );\n" +
                        "CREATE TABLE IF NOT EXISTS allGroups(\n" +
                        "groupName TEXT, ID TEXT, groupAdmin TEXT, membersNumber int, members TEXT, followingsNumber int, " +
                        "bonMembers TEXT );",

                s = "UPDATE posts " +
                        "SET likesNumber = 0," +
                        " likedBy = ''" +
                        " WHERE NO = 1;",

                string = "UPDATE accounts \n " +
                        "SET followingsNumber = 2," +
                        " followers = 'Azari1381, MOSTAFAAA' " +
                        //" followings = '' " +
                        "WHERE ID = 'mostafaAzari';";

        //System.out.println(s);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(s);
            Statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Group group, Account account) {
        String command = "UPDATE allGroups " +
                "SET members = '" + group.getMembers() + "' " +
                "WHERE ID = '" + group.getID() + "';",
                command2 = "UPDATE accounts " +
                        "SET groupsID = '" + account.getGroups() + "' " +
                        "WHERE ID = '" + group.getID() + "';";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newComment(Comment comment, Post post) {
        String command = "UPDATE posts " +
                "SET likesNumber = '" + post.getLikesNumber() + "', " +
                "likedBy = '" + post.getLikedBy() + "' " +
                "WHERE ID = '" + post.getID() + "';",
                command1 = "INSERT INTO comments" +
                        " values ('" + comment.getText() + "', '"+ comment.getSender() + "', '"+ comment.getID() +"', '"+
                        comment.getSource() +"', '"+ comment.getDate() +"', "+ comment.getLikesNumber() +", '"+ comment.getLikedBy()
                        +"', " + comment.getCommentsNumber() +", '" + comment.getComments() + "', " + post.getNO() + ");\n",
                command2 = "UPDATE num " +
                        "SET lastNumber = lastNumber + 1 " +
                        "WHERE titre = 'comment';";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(command);
            Statement.executeUpdate(command1);
            Statement.executeUpdate(command2);
            Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

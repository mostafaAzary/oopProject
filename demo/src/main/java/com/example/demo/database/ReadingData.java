package com.example.demo.database;

import com.example.demo.account.Account;
import com.example.demo.chat.Group;
import com.example.demo.chat.Massage;
import com.example.demo.chat.PrivateChat;
import com.example.demo.post.Comment;
import com.example.demo.post.Post;

import java.sql.*;
import java.util.ArrayList;

public class ReadingData {

    Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root",
                    "ma400105358");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAccount(String ID, String password) throws SQLException {
        String command = "SELECT * FROM accounts WHERE accounts.ID = '" + ID + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                if(resultSet.getString(2).equals(password))
                    return true;
                else return false;
            }
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkID(String id) {
        String command = "SELECT * FROM accounts WHERE ID = '" + id + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(1));
                Statement.close();
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccount(String id) {
        String command = "SELECT * FROM accounts WHERE accounts.ID = '" + id + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Account account = new Account(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getInt(8),
                        resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11));
                //System.out.println(resultSet.getString(6));
                Statement.close();
                return account;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkGroupID(String id) {
        String command = "SELECT * FROM allGroups WHERE ID = '" + id + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Statement.close();
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkFollower(String accountID, String followerID) {
        Account account = getAccount(accountID);
        if(account.checkFollower(followerID))
            return true;
        else
            return false;
    }

    public boolean checkFollowing(String accountID, String followingID) {
        Account account = getAccount(accountID);
        if(account.checkFollowing(followingID))
            return true;
        else
            return false;
    }

    public Group getGroup(String id) {
        String command = "SELECT * FROM allGroups WHERE ID = '" + id + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Group group = new Group(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7));
                Statement.close();
                return group;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Massage> showMassages(String sourceID) {
        String command = "SELECT * FROM massages WHERE source = '" + sourceID + "';";
        //System.out.println(command);
        ArrayList<Massage> massages = new ArrayList<>();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Massage massage = new Massage(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getBoolean(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9));

                //System.out.println(resultSet.getString(6));
                massages.add(massage);
            }
            Statement.close();
            return massages;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Massage getMassages(String massageID) {
        String command = "SELECT * FROM massages WHERE ID = '" + massageID + "';";
        Massage massage;
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                massage = new Massage(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getBoolean(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9));
                return massage;}
            Statement.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkLastMassages(String sourceID, String senderID, String massageID) {
        String command = "SELECT ID FROM massages WHERE " +
                " sender = '" + senderID + "' AND source = '" + sourceID + "'" +
                " order by num DESC LIMIT 5;";
        //System.out.println(command);
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(1));
                if(resultSet.getString(1).equals(massageID))
                    return true;
            }
            Statement.close();
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void setNumber() {
        String command = "SELECT lastNumber FROM num WHERE titre = 'massage';",
                command1 = "SELECT lastNumber FROM num WHERE titre = 'comment';",
                command2 = "SELECT lastNumber FROM num WHERE titre = 'com.example.phase2.post';";
        int massageNO = 0, postNO = 0, commentNO = 0;
        try {
            Statement Statement = connection.createStatement();

            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                massageNO = resultSet.getInt(1);
            }
            Massage massage = new Massage(massageNO);

            resultSet = Statement.executeQuery(command2);
            while (resultSet.next()) {
                postNO = resultSet.getInt(1);
            }
            Post post = new Post(postNO);

            resultSet = Statement.executeQuery(command1);
            while (resultSet.next()) {
                commentNO = resultSet.getInt(1);
            }
            Comment comment = new Comment(commentNO);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Massage massage = new Massage(massageNO);
        Post post = new Post(postNO);
        Comment comment = new Comment(commentNO);
    }



    public String getAccountsBlockList(String id) {
        String command = "SELECT * FROM accounts WHERE accounts.ID = '" + id + "';", output = "";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                output = resultSet.getString(7) + ",";
                output = output.replaceAll(",", "\n");
                Statement.close();
                if(output.equals("null\n"))
                    output = "No blocked account. "+ "\n";
                return output;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PrivateChat getPrivateChat(String id, String id1) {
        String command = "SELECT * FROM privateChats " +
                "WHERE (accountID1 = '" + id + "' AND accountID2 = '" + id1 + "') " +
                "OR (accountID1 = '" + id1 + "' AND accountID2 = '" + id + "');";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                PrivateChat chat = new PrivateChat(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3));
                Statement.close();
                return chat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Post> showPosts(String postedBy) {
        String command = "SELECT * FROM posts WHERE postedBy = '" + postedBy + "';";
        //System.out.println(command);
        ArrayList<Post> posts = new ArrayList<>();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Post post = new Post(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6),
                        resultSet.getInt(7));

                //System.out.println(resultSet.getString(6));
                posts.add(post);
            }
            Statement.close();
            return posts;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPost(String postID) {
        String command = "SELECT * FROM posts WHERE ID = '" + postID + "';";
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Post post = new Post(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6),
                        resultSet.getInt(7));
                //System.out.println(com.example.phase2.post.show());
                return post;
                //System.out.println(resultSet.getString(6));
            }
            Statement.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Comment> showComments(String postID) {
        String command = "SELECT * FROM comments WHERE source = '" + postID + "';";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(command);
            while (resultSet.next()) {
                Comment comment= new Comment(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getString(7), resultSet.getInt(8),
                        resultSet.getString(9));
                //System.out.println(resultSet.getString(6));
                comments.add(comment);
            }
            Statement.close();
            return comments;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

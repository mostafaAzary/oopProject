package com.example.demo.account;

public class Account {
    String ID;
    private String password;
    int followersNumber;
    String followers;
    int followingsNumber;
    String followings;
    String blocks;
    int postsNumber;
    String posts;
    String groups;
    String privateChats;

    public Account(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.followersNumber = 0;
        this.followers = "";
        this.followingsNumber = 0;
        this.followings = "";
        this.blocks = "";
        this.postsNumber = 0;
        this.posts = "";
        this.groups = "";
        this.privateChats = "";
    }

    public Account(String ID, String password, int followersNumber, String followers, int followingsNumber,
                   String followings, String blocks, int postsNumber, String posts,
                   String groups, String privateChats) {
        this.ID = ID;
        this.password = password;
        this.followersNumber = followersNumber;
        this.followers = followers;
        this.followingsNumber = followingsNumber;
        this.followings = followings;
        this.blocks = blocks;
        this.postsNumber = postsNumber;
        this.posts = posts;
        this.groups = groups;
        this.privateChats = privateChats;
    }

    public String showProfile() {
        String output = this.ID + "\n";
        output += followersNumber + " followers" + "    ";
        output += followingsNumber + " followings" + "    ";
        output += postsNumber + " posts";
        return output;
    }

    public String blockList() {
        String output = "";
        if(blocks == null)
            output = "No blocked account. "+ "\n";
        else
            for (String block : blocks.split(", ")) {
                output += block + "\n";
            }
        return output;
    }

    public String followingList() {
        //System.out.println(followings);
        String output = "";
        if(followings == null)
            output = "No following exists. "+ "\n";
        else
            for (String following : followings.split(", ")) {
                //System.out.println(following);
                output += following + "\n";
            }
        //System.out.println();
        return output;
    }

    public String followerList() {
        String output = "";
        if(followers == null)
            output = "No follower exists. "+ "\n";
        else
            for (String follower : followers.split(", ")) {
                output += follower + "\n";
            }
        return output;
    }

    public String groupList() {
        String output = "", string = null;
        if(groups==null)
            output = "No group exists. "+ "\n";
        else
            for (String group : (groups + ",  ").split(", ")) {
                output += group + "\n";
            }
        return output;
    }

    public String privateChatList() {
        String output = "";
        if(privateChats == null)
            output = "No private com.example.phase2.chat exists. "+ "\n";
        else{
            for (String privateChat : privateChats.split(", ")) {
                output += privateChat + "\n";
            }
            output = output.replaceAll(",", "");
            output = output.replaceAll(this.ID, "");
        }
        return output;
    }

    public void newPost(String id) {
        if (postsNumber > 0)
            posts += ", " + id;
        else posts = id;
        this.postsNumber ++;
    }

    public int getPostsNumber() {
        return postsNumber;
    }

    public boolean checkFollowing(String id) {
        if(followings == null)
            return false;
        for (String following : followings.split(", ")) {
            if (following.equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean checkFollower(String id) {
        if(followers == null)
            return false;
        for (String follower : followers.split(", ")) {
            if (follower.equals(id))
                return true;
        }
        return false;
    }

    public boolean checkBlock(String id) {
        if(blocks == null)
            return false;
        for (String block : blocks.split(", ")) {
            if (block.equals(id))
                return true;
        }
        return false;
    }

    public  void block(String ID){
        if(blocks == null)
            blocks = ID;
        else
            blocks += ", " + ID;
    }

    public String getBlocks() {
        return blocks;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void addFollowing(String followingID) {
        this.followingsNumber ++;
        if(followingsNumber == 1)
            followings = followingID;
        else
            followings += ", " + followingID;
    }

    public void removeFollowing(String followingID) {
        this.followingsNumber --;
        if(followingsNumber == 0)
            followings = "";
        else{
            followings = followings.replace(followingID + ", ", "");
            followings = followings.replace(", " + followingID, "");
        }
    }

    public String getFollowings() {
        return followings;
    }

    public void addFollower(String followerID) {
        this.followersNumber ++;
        if(followersNumber == 1)
            followers = followerID;
        else
            followers += ", " + followerID;
    }

    public void removeFollower(String followerID) {
        this.followersNumber --;
        if(followersNumber == 0)
            followers = " ";
        else {
            followers = followers.replace(followerID + ", ", "");
            followers = followers.replace(", " + followerID, "");
        }
    }

    public String getFollowers() {
        return followers;
    }

    public void addPrivateChat(String ID) {
        if(privateChats == null)
            privateChats = ID;
        else
            privateChats += ", " + ID;
    }

    public void addGroup(String ID) {
        if(groups == null)
            groups = ID;
        else
            groups += ", " + ID;
    }

    public String getGroups() {
        return groups;
    }

    public String getPrivateChats() {
        return privateChats;
    }
}

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

    public Account(String ID, String password, int followersNumber, String followers, int followingsNumber, String followings, String blocks, int postsNumber, String posts, String groups, String privateChats) {
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
        output = output + this.followersNumber + " followers    ";
        output = output + this.followingsNumber + " followings    ";
        output = output + this.postsNumber + " posts";
        return output;
    }

    public String blockList() {
        String output = "";
        if (this.blocks == null) {
            output = "No blocked account. \n";
        } else {
            String[] var2 = this.blocks.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String block = var2[var4];
                output = output + block + "\n";
            }
        }

        return output;
    }

    public String followingList() {
        String output = "";
        if (this.followings == null) {
            output = "No following exists. \n";
        } else {
            String[] var2 = this.followings.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String following = var2[var4];
                output = output + following + "\n";
            }
        }

        return output;
    }

    public String followerList() {
        String output = "";
        if (this.followers == null) {
            output = "No follower exists. \n";
        } else {
            String[] var2 = this.followers.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String follower = var2[var4];
                output = output + follower + "\n";
            }
        }

        return output;
    }

    public String groupList() {
        String output = "";
        String string = null;
        if (this.groups == null) {
            output = "No group exists. \n";
        } else {
            String[] var3 = (this.groups + ",  ").split(", ");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String group = var3[var5];
                output = output + group + "\n";
            }
        }

        return output;
    }

    public String privateChatList() {
        String output = "";
        if (this.privateChats == null) {
            output = "No private com.example.phase2.chat exists. \n";
        } else {
            String[] var2 = this.privateChats.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String privateChat = var2[var4];
                output = output + privateChat + "\n";
            }

            output = output.replaceAll(",", "");
            output = output.replaceAll(this.ID, "");
        }

        return output;
    }

    public void newPost(String id) {
        if (this.postsNumber > 0) {
            this.posts = this.posts + ", " + id;
        } else {
            this.posts = id;
        }

        ++this.postsNumber;
    }

    public int getPostsNumber() {
        return this.postsNumber;
    }

    public boolean checkFollowing(String id) {
        if (this.followings == null) {
            return false;
        } else {
            String[] var2 = this.followings.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String following = var2[var4];
                if (following.equals(id)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean checkFollower(String id) {
        if (this.followers == null) {
            return false;
        } else {
            String[] var2 = this.followers.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String follower = var2[var4];
                if (follower.equals(id)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean checkBlock(String id) {
        if (this.blocks == null) {
            return false;
        } else {
            String[] var2 = this.blocks.split(", ");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String block = var2[var4];
                if (block.equals(id)) {
                    return true;
                }
            }

            return false;
        }
    }

    public void block(String ID) {
        if (this.blocks == null) {
            this.blocks = ID;
        } else {
            this.blocks = this.blocks + ", " + ID;
        }

    }

    public String getBlocks() {
        return this.blocks;
    }

    public String getID() {
        return this.ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void addFollowing(String followingID) {
        ++this.followingsNumber;
        if (this.followingsNumber == 1) {
            this.followings = followingID;
        } else {
            this.followings = this.followings + ", " + followingID;
        }

    }

    public void removeFollowing(String followingID) {
        --this.followingsNumber;
        if (this.followingsNumber == 0) {
            this.followings = "";
        } else {
            this.followings = this.followings.replace(followingID + ", ", "");
            this.followings = this.followings.replace(", " + followingID, "");
        }

    }

    public String getFollowings() {
        return this.followings;
    }

    public void addFollower(String followerID) {
        ++this.followersNumber;
        if (this.followersNumber == 1) {
            this.followers = followerID;
        } else {
            this.followers = this.followers + ", " + followerID;
        }

    }

    public void removeFollower(String followerID) {
        --this.followersNumber;
        if (this.followersNumber == 0) {
            this.followers = " ";
        } else {
            this.followers = this.followers.replace(followerID + ", ", "");
            this.followers = this.followers.replace(", " + followerID, "");
        }

    }

    public String getFollowers() {
        return this.followers;
    }

    public void addPrivateChat(String ID) {
        if (this.privateChats == null) {
            this.privateChats = ID;
        } else {
            this.privateChats = this.privateChats + ", " + ID;
        }

    }

    public void addGroup(String ID) {
        if (this.groups == null) {
            this.groups = ID;
        } else {
            this.groups = this.groups + ", " + ID;
        }

    }

    public String getGroups() {
        return this.groups;
    }

    public String getPrivateChats() {
        return this.privateChats;
    }

    public int getFollowersNumber() {
        return followersNumber;
    }

    public int getFollowingsNumber() {
        return followingsNumber;
    }

    public String getPosts() {
        return posts;
    }
}


package com.example.demo.chat;

public class Group {
    String name;
    String ID;
    String admin;
    int membersNumber;
    String members;
    private String bonMembers;
    String massages; //ignore

    public Group(String name, String ID, String admin, int membersNumber, String members) {
        this.name = name;
        this.ID = ID;
        this.admin = admin;
        this.membersNumber = membersNumber;
        this.members = members;
    }

    public Group(String name, String ID, String admin, int membersNumber, String members, String bonMembers,
                 String massages) {
        this.name = name;
        this.ID = ID;
        this.admin = admin;
        this.membersNumber = membersNumber;
        this.members = members;
        this.bonMembers = bonMembers;
        this.massages = massages;
    }

    public boolean checkMember(String ID){
        if(members == null)
            return false;
        for (String member : members.split(", ")) {
            System.out.println(member);
            if(member.equals(ID))
                return true;
        }
        return false;
    }

    public boolean checkBon(String ID){
        if(bonMembers == null)
            return false;
        for (String bonMember : bonMembers.split(", ")) {
            if(bonMember.equals(ID))
                return true;
        }
        return false;
    }

    public void removeMember(String ID){
        this.membersNumber --;
        if(membersNumber == 1)
            members = admin;
        else {
            members = members.replace(ID + ", ", "");
            members = members.replace(", " + ID, "");
        }
    }

    public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public String getID() {
        return ID;
    }

    public String getMembers() {
        return members;
    }

    public int getMembersNumber() {
        return membersNumber;
    }

    public String getBonMembers() {
        return bonMembers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String showMembers() {
        String output = "";
        for (String member : members.split(", ")) {
            output += "\n" + member;
        }
        return output;
    }

    public String show() {
        String output = name + "\n";
        int membersNumber = members.split(", ").length;
        output += "ID: " + ID + "    " + "admin: " + admin + "    " + "membersNumber: " + membersNumber;
        return output;
    }

    public void addMember(String id) {
        members += ", " + id;
    }
}

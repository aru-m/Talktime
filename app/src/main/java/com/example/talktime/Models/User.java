package com.example.talktime.Models;

public class User {
   private String profilePic,userName,mail,password,userId,lastMessage,status;

   public User(){

   }

   //SignUp constructor
   public User(String userName, String mail, String password) {
       this.userName = userName;
       this.mail = mail;
       this.password = password;
   }

    public User(String profilePic, String userName, String mail, String password, String userId, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus(){
       return status;
    }
}

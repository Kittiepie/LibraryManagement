/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

/**
 *
 * @author OwO
 *
 */
public class User {

    private int userId;
    private String email;
    private String password;
    private int roleId;
    private String fullName;
    private int genderId;
    private String mobile;
    private String profilePic;
    private boolean isActive;
    private Integer borrowcardId;
    
    

    public User() {
    }

    public User(int userId, String email, String password, int roleId, String fullName, int genderId, String mobile, String profilePic, boolean isActive) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.fullName = fullName;
        this.genderId = genderId;
        this.mobile = mobile;
        this.profilePic = profilePic;
        this.isActive = isActive;
    }

    public User(int userId, String email, String password, int roleId, String fullName, int genderId, String mobile, String profilePic, boolean isActive, Integer borrowcardId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.fullName = fullName;
        this.genderId = genderId;
        this.mobile = mobile;
        this.profilePic = profilePic;
        this.isActive = isActive;
        this.borrowcardId = borrowcardId;
    }
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getBorrowcardId() {
        return borrowcardId;
    }

    public void setBorrowcardId(Integer borrowcardId) {
        this.borrowcardId = borrowcardId;
    }

    public int convertBorrowcardIdToInt(Integer borrowcard_id) {
    if (borrowcard_id == null) {
        return 0; 
    }
    return borrowcard_id;
}
}

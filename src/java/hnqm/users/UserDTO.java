/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.users;

/**
 *
 * @author Minh Hoang
 */
public class UserDTO {
    private String userID,userName,password,roleID,photo,status,phone,promotionStatus;
 

    public UserDTO() {
    }

    public UserDTO(String userID, String userName, String password, String roleID, String photo, String status, String phone) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
        this.photo = photo;
        this.status = status;
        this.phone = phone;
    }

    public UserDTO(String userID, String userName, String password, String roleID, String photo, String status, String phone, String promotionStatus) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
        this.photo = photo;
        this.status = status;
        this.phone = phone;
        this.promotionStatus = promotionStatus;
    }

    public UserDTO(String userID, String userName, String password, String photo, String phone) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.photo = photo;
        this.phone = phone;
    }

   

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }
    
}

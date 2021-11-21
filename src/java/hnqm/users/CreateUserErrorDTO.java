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
public class CreateUserErrorDTO {

    private String userIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String phoneError;
    private String statusError;
    private String rankError;
    
    public CreateUserErrorDTO() {
    }

    public CreateUserErrorDTO(String userIDError, String fullNameError, String passwordError, String confirmError, String phoneError, String statusError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.phoneError = phoneError;
        this.statusError = statusError;
    }

    public CreateUserErrorDTO(String rankError) {
        this.rankError = rankError;
    }
    
    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

   
    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getRankError() {
        return rankError;
    }

    public void setRankError(String rankError) {
        this.rankError = rankError;
    }
    

}

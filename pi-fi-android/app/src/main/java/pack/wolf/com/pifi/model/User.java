package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ryanmoore on 3/2/15.
 */
public class User implements Serializable {

    @SerializedName(value = "_id")
    private Long id;

    @SerializedName(value = "firstname")
    private String firstName;

    @SerializedName(value = "lastname")
    private String lastName;

    @SerializedName(value = "email")
    private String emailAddress;

    @SerializedName(value = "admin")
    private Boolean admin;

    @SerializedName(value = "password_reset_token")
    private String passwordResetToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}

package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanmoore on 3/2/15.
 */
public class User implements Serializable {

    @SerializedName(value = "id")
    private Long id;

    @SerializedName(value = "firstName")
    private String firstName;

    @SerializedName(value = "lastName")
    private String lastName;

    @SerializedName(value = "emailAddress")
    private String emailAddress;

    @SerializedName(value = "username")
    private String username;

    @SerializedName(value = "phoneNumber")
    private String phoneNumber;

    @SerializedName(value = "emailNotifications")
    private Boolean emailNotifications;

    @SerializedName(value = "textNotifications")
    private Boolean textNotifications;

    @SerializedName(value = "pushNotifications")
    private Boolean pushNotifications;

    @SerializedName(value = "profilePicUrl")
    private String profilePicUrl;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(Boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Boolean getTextNotifications() {
        return textNotifications;
    }

    public void setTextNotifications(Boolean textNotifications) {
        this.textNotifications = textNotifications;
    }

    public Boolean getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(Boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}

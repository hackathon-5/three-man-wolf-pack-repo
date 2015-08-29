package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {

    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "refresh_token")
    private String refreshToken;

    @SerializedName(value = "expires_in")
    private Long expiresIn;

    @SerializedName(value = "token_type")
    private String refreshBy;

    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshBy() {
        return refreshBy;
    }

    public void setRefreshBy(String refreshBy) {
        this.refreshBy = refreshBy;
    }
}

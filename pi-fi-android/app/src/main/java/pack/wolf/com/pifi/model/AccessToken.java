package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccessToken implements Serializable {

    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

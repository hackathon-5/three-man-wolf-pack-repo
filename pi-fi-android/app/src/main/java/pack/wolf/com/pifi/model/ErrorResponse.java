package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName(value = "errorMessage")
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName(value = "success")
    private Boolean success;

    @SerializedName(value = "result")
    private T result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

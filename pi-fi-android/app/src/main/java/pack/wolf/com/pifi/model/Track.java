package pack.wolf.com.pifi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Whitney Champion on 8/29/15.
 */
public class Track implements Serializable {

    @SerializedName(value = "_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

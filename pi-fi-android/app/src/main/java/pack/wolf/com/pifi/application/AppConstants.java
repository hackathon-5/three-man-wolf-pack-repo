package pack.wolf.com.pifi.application;

public class AppConstants {

    //env stuff
    public static final String SERVER_PATH           = "http://127.0.0.1:9000/";

    //logger tag
    public static final String LOG_TAG               = "Pifi";

    public static final String METHOD_TOKENS         = "api/1/oauth/token";

    public static final int REQUEST_TIMEOUT = 30000;

    public static final String JSON_CONTENT_TYPE     = "application/json";
    public static final String FORM_CONTENT_TYPE     = "application/x-www-form-urlencoded";

    public static final String REQUEST_PARAM_REFRESH = "refresh_token";
    public static final String REQUEST_PARAM_GRANT   = "grant_type";
    public static final String REQUEST_PARAM_USERNAME= "username";
    public static final String REQUEST_PARAM_PASSWORD= "password";

    public static final String REQUEST_VALUE_PASSWORD= "password";
    public static final String REQUEST_VALUE_REFRESH = "refresh_token";
}
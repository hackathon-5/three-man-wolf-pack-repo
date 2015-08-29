package pack.wolf.com.pifi.util;

import java.util.Calendar;
import java.util.Date;

import pack.wolf.com.pifi.model.AccessToken;

public class TokenUtil {

    public static Boolean isTokenExpired() {

        Boolean expiredOrInvalid = true;
        AccessToken token = SharedPreferenceUtil.getAccessToken();
        if (token != null) {

            Date expirationDate = token.getExpirationDate();
            if (expirationDate != null) {
                Calendar expirationCalendar = Calendar.getInstance();
                expirationCalendar.setTime(expirationDate);

                Calendar currentCalendar = Calendar.getInstance();
                if (expirationCalendar.before(currentCalendar)) {
                    expiredOrInvalid = true;
                } else {
                    expiredOrInvalid = false;
                }
            }
        }
        return false;
    }
}

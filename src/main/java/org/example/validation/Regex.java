package org.example.validation;
import java.util.regex.Pattern;
public class Regex {

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }

    public static boolean validatePhoneNo(String phoneNo) {
        String phoneRegex = "^[0-9]{10}$";
        Pattern pat = Pattern.compile(phoneRegex);
        if (phoneNo == null)
            return false;
        return pat.matcher(phoneNo).matches();
    }

    public static boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }


}

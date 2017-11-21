/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class StringUtil {

    public static boolean isBlank(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile("0\\d{9}");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }

        pattern = Pattern.compile("0\\d{10}");
        matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }

        return false;
    }
}

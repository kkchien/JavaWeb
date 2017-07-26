/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

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
}

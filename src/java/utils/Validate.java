/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Validate {

    // Check tên không null, không rỗng
    public static boolean checkString(String str, int minLen, int maxLen) {
        return str != null && str.trim().length() >= minLen && str.trim().length() <= maxLen;
    }

    // Check email cơ bản
    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    // Check số nguyên >= 0 (dùng cho stock, age, v.v.)
    public static boolean checkPositiveInt(String str) {
        try {
            int value = Integer.parseInt(str);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check số thực > 0 (dùng cho price)
    public static boolean checkPositiveDouble(String str) {
        try {
            double value = Double.parseDouble(str);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check ngày (YYYY-MM-DD) hợp lệ và không ở tương lai
    public static boolean checkDate(String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            return !date.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}

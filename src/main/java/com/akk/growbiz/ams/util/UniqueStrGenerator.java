package com.akk.growbiz.ams.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class UniqueStrGenerator {

    private Random rnd = SecureRandom.getInstanceStrong();
    @Value("${ams.appointmentcode.length}")
    private String appCodeLength;

    public UniqueStrGenerator() throws NoSuchAlgorithmException {
    }

    // The following method tested up to 40 million unique records
    public String getAppointmentCode() {
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        int charsLength = chars.length;

        StringBuilder appointmentCode = new StringBuilder();
        for (int i = 0; i < Integer.parseInt(appCodeLength); i++) {
            int index = rnd.nextInt(charsLength - i - 1);
            // Simple swap
            char a = chars[i + index];
            chars[i + index] = chars[i];
            chars[i] = a;
            appointmentCode.append(a);
        }

        return appointmentCode.toString();
    }
}
//    public static void main(String[] args) {
//
//        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
//        Random rnd = new Random();
//        int charsLength = chars.length;
//        int passLength = 8;
//        StringBuilder password = new StringBuilder();
//        for (int i = 0; i < passLength; i++) {
//            int index = rnd.nextInt(charsLength - i - 1);
//            // Simple swap
//            char a = chars[i + index];
//            chars[i + index] = chars[i];
//            chars[i] = a;
//            password.append(a);
//        }
//
//        System.out.print(password);
//
//        Set s = new HashSet();
//        for (int j = 1 ; j <= 40000000; j ++) {
//            StringBuilder password = new StringBuilder();
//            for (int i = 0; i < passLength; i++) {
//                int index = rnd.nextInt(charsLength - i - 1);
//                // Simple swap
//                char a = chars[i + index];
//                chars[i + index] = chars[i];
//                chars[i] = a;
//                password.append(a);
//            }
//
//           // System.out.println(password);
//            s.add(password);
//        }
//
//        System.out.println("set size-->"+s.size());
//    }

package com.example.jurizo.bitacora.Core.BitacoraCore.Controls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Carlos Rizo on 02/07/2017.
 */

public class Utils {

    public static String EncryptPassword(String s) {

            final String MD5 = "MD5";
            try {
                // Create MD5 Hash
                MessageDigest digest = java.security.MessageDigest
                        .getInstance(MD5);
                digest.update(s.getBytes());
                byte messageDigest[] = digest.digest();

                // Create Hex String
                StringBuilder hexString = new StringBuilder();
                for (byte aMessageDigest : messageDigest) {
                    String h = Integer.toHexString(0xFF & aMessageDigest);
                    while (h.length() < 2)
                        h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";

    }

}

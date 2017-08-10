package com.example.jurizo.bitacora.Controls;

import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static String GetDateTime() {
        Calendar calander = Calendar.getInstance();
        int cDay = calander.get(Calendar.DAY_OF_MONTH);
        int cMonth = calander.get(Calendar.MONTH) + 1;
        int cYear = calander.get(Calendar.YEAR);
        String selectedMonth = "" + cMonth;
        String selectedYear = "" + cYear;
        int cHour = calander.get(Calendar.HOUR);
        int cMinute = calander.get(Calendar.MINUTE);
        int cSecond = calander.get(Calendar.SECOND);
        String datetime =  cYear +  "-" + cMonth + "-" + cDay + " " + cHour + ":" + cMinute + ":" + cSecond;
        return  datetime;
    }

    public static boolean SessionValidate(Session session) throws ParseException {
        boolean session_validate = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sessionDate = sdf.parse(session.getFinish_tocken());
        Date datetimeNow = sdf.parse(GetDateTime());
        if(datetimeNow.before(sessionDate)){
            session_validate = true;
        }
        return session_validate;
    }
}

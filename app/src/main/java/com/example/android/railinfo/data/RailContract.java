package com.example.android.railinfo.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sathirishabh on 28-03-2017.
 */

public final class RailContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.railinfo";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "rails";


    public static final class RailEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);


        public final static String TABLE_NAME = "rails";
        public final static String TABLE_NAME2 = "search";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME1 = "name1";
        public final static String COLUMN_NAME2 = "name2";
        public final static String COLUMN_NAME3 = "name3";
        public final static String COLUMN_NAME4 = "name4";
        public final static String COLUMN_NAME5 = "name5";
        public final static String COLUMN_NAME6 = "name6";

        public final static String COLUMN_FROM = "frm";
        public final static String COLUMN_TO= "destination";
        public final static String COLUMN_PNR="pnr";
        public final static String COLUMN_DATE = "date";


        public final static String COLUMN_GENDER1="gender1";
        public final static String COLUMN_GENDER2="gender2";
        public final static String COLUMN_GENDER3="gender3";
        public final static String COLUMN_GENDER4="gender4";
        public final static String COLUMN_GENDER5="gender5";
        public final static String COLUMN_GENDER6="gender6";
        public final static String COLUMN_AGE1="age1";
        public final static String COLUMN_AGE2="age2";
        public final static String COLUMN_AGE3="age3";
        public final static String COLUMN_AGE4="age4";
        public final static String COLUMN_AGE5="age5";
        public final static String COLUMN_AGE6="age6";
        public final static String COLUMN_TRAINNAME="trainname";
        public final static String COLUMN_CLASS="class";
        public final static String COLUMN_BIRTH="birth";
        public final static int COLUMN_GENDER_MALE=1;
        public final static int COLUMN_GENDER_FEMALE=2;

        public final static int COLUMN_TO_FD = 1;
        public final static int COLUMN_TO_ALD = 2;
        public final static int COLUMN_TO_KANPUR = 3;
        public final static int COLUMN_FROM_FD = 1;
        public final static int COLUMN_FROM_ALD = 2;
        public final static int COLUMN_FROM_KANPUR = 3;
        public final static int UNKNOWN=0;

        public static boolean isValidfrom(Integer from) {

            if(from !=0||from !=1||from !=2)
                return false;
            else
                return true;
        }
    }
}

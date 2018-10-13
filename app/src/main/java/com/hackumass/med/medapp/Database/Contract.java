package com.hackumass.med.medapp.Database;

/**
 * Created by Aryan Singh on 10/13/2018.
 */

public class Contract {

    public class User{
        // THESE ARE NAMES OF COLUMNS
        public static final String TABLE_NAME = "user_only";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "username";
        public static final String COLUMN_SMOKING = "smoking";
        public static final String COLUMN_DRINKING = "drinking";
        public static final String COLUMN_FEELING = "feeling";
    }

    // we put stuff here
    // medicine for all symptoms
    // Eg: 1 -- illness -- meds
    public class SyMeds{
        public static final String TABLE_NAME = "symptoms_meds";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ILLNESS = "illness";
        public static final String COLUMN_MEDS = "all_meds";
    }
}

package com.smlnskgmail.jaman.hashchecker.logic.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smlnskgmail.jaman.hashchecker.logic.database.ormlite.OrmLiteDatabaseHelper;

public class HelperFactory {

    private static DatabaseHelper databaseHelper;

    @Nullable
    public static DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (databaseHelper != null) {
            releaseHelper();
        }
        databaseHelper = OpenHelperManager.getHelper(
                context,
                OrmLiteDatabaseHelper.class
        );
    }

    public static void releaseHelper() {
        databaseHelper.releaseHelper();
        databaseHelper = null;
    }

}

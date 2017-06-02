package com.example.administrator.memosqver20.Model.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.administrator.memosqver20.Model.DB.MemoDbSchema.*;

/**
 * Created by Administrator on 2017-06-01.
 */

public class MemoHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "name.db";

    public MemoHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ MemoTable.TABLE_NAME
                + "( _id integer primary key autoincrement, "
                + MemoTable.Cols.UUID + ", "
                + MemoTable.Cols.DATE + ", "
                + MemoTable.Cols.WORDS_OF_THIS_WEEK + ", "
                + MemoTable.Cols.DAILY_QT + ", "
                + MemoTable.Cols.THANKS_GIVING + ", "
                + MemoTable.Cols.PRAYER + ", "
                + MemoTable.Cols.JOURNAL + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}

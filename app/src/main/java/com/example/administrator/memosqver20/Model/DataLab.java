package com.example.administrator.memosqver20.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.memosqver20.Model.DB.MemoCursorWrapper;
import com.example.administrator.memosqver20.Model.DB.MemoHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.administrator.memosqver20.Model.DB.MemoDbSchema.MemoTable;



public class DataLab {

    // 생성자에 넣어둔다는 것은 이 객체를 생성해서 사용하겠다는 뜻이므로, 어차피 생성자에 넣어줄 것이라면
    // 차라리 스스로의 객체를 반환하는 static 메소드를 만들어 그 안에 모든 것을 저장해 두면 어디서든지 꺼내 사용할 수 있다.

    static DataLab sDataLab;
    SQLiteDatabase db;
    MemoHelper helper;
    Context context;
//--------------------------------------------------------------------------------------------------

    // 생성자
    public DataLab(Context context){

        this.context = context;
        helper = new MemoHelper(context);
        db = helper.getWritableDatabase();
    }

    // 모든 데이터를 담은 하나의 Lab 객체 반환
    public static DataLab getLab(Context context){
        if(sDataLab == null){
            sDataLab = new DataLab(context);
        }
        return sDataLab;
    }

//--------------------------------------------------------------------------------------------------
    // insert
    public void add(Memo memo){
        ContentValues values = getContentValue(memo);
        db.insert(MemoTable.TABLE_NAME, null, values);
    }

    public ContentValues getContentValue(Memo memo){
        ContentValues values = new ContentValues();
        values.put(MemoTable.Cols.UUID, memo.getUuid().toString());
        values.put(MemoTable.Cols.DAILY_QT, memo.getDate().toString()); /** TODO 공부 */
        values.put(MemoTable.Cols.WORDS_OF_THIS_WEEK, memo.getWeek());
        values.put(MemoTable.Cols.DAILY_QT, memo.getQt());
        values.put(MemoTable.Cols.THANKS_GIVING, memo.getThanks());
        values.put(MemoTable.Cols.PRAYER, memo.getPrayer());
        values.put(MemoTable.Cols.JOURNAL, memo.getJournal());

        return values;
    }

    // update
    public void update(Memo memo){
        ContentValues values = getContentValue(memo);
        String whereClause = MemoTable.Cols.UUID + " = ?";
        String[] whereArgs = { memo.getUuid().toString() };
        db.update(MemoTable.TABLE_NAME, values, whereClause, whereArgs);
    }

    // delete
    public void delete(Memo memo){

        String resId = memo.getUuid().toString();
        String whereClause = MemoTable.Cols.UUID + " = ?";
        String[] whereArgs = { resId };
        db.delete(MemoTable.TABLE_NAME, whereClause, whereArgs);

    }

    public void delete(UUID resId){
        String whereClause = MemoTable.Cols.UUID + " = ?";
        String[] whereArgs = { resId.toString() };
        db.delete(MemoTable.TABLE_NAME, whereClause, whereArgs);
    }

//--------------------------------------------------------------------------------------------------

    public MemoCursorWrapper queryMemo(String whereClause, String[] whereArgs){

        Cursor cursor = db.query(MemoTable.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        return new MemoCursorWrapper(cursor);
}

    // Memo
    public Memo getMemo(UUID resId){

        String whereClause = MemoTable.Cols.UUID + " = ?";
        String[] whereArgs = { resId.toString() };
        MemoCursorWrapper cursor = queryMemo(whereClause, whereArgs);

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            Memo memo = cursor.getMemoFromCursor();

            return memo;
        } finally {
            cursor.close();
        }
    }


    // ArrayList<Memo>
    public List<Memo> getMemos(){

        List<Memo> datas = new ArrayList<>();
        MemoCursorWrapper cursor = queryMemo(null, null);
        Memo memo = new Memo();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                datas.add(cursor.getMemoFromCursor());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return datas;
    }

}

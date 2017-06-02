package com.example.administrator.memosqver20.Model.DB;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.administrator.memosqver20.Model.Memo;

import java.util.Date;
import java.util.UUID;

import static com.example.administrator.memosqver20.Model.DB.MemoDbSchema.*;


// 얘가 필요한 이유는 매 번 커서에서 데이터를 뺴 주는 과정이 생기기 때문에 커서만 넣어주면 바로 객체를 반환하는
// 메소드를 만든 것이다.

// 즉, 2가지 면에서 편한 듯 하다.
// 1. 커서 자체로도 사용할 수 있다.
// 2. getMemoFrom 해주면 바로 객체를 반환해 줄 수도 있다.
public class MemoCursorWrapper extends CursorWrapper{

    public MemoCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Memo getMemoFromCursor(){

        String uuid = getString(getColumnIndex(MemoTable.Cols.UUID));
        long date = getLong(getColumnIndex(MemoTable.Cols.DATE));
        String week = getString(getColumnIndex(MemoTable.Cols.WORDS_OF_THIS_WEEK));
        String qt = getString(getColumnIndex(MemoTable.Cols.DAILY_QT));
        String thanks = getString(getColumnIndex(MemoTable.Cols.THANKS_GIVING));
        String prayer = getString(getColumnIndex(MemoTable.Cols.PRAYER));
        String journal = getString(getColumnIndex(MemoTable.Cols.JOURNAL));

        Memo memo = new Memo();
        memo.setUuid(UUID.fromString(uuid));
        memo.setDate(new Date(date));
        memo.setWeek(week);
        memo.setQt(qt);
        memo.setThanks(thanks);
        memo.setPrayer(prayer);
        memo.setJournal(journal);

        return memo;

    }
}

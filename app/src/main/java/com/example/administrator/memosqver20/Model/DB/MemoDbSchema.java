package com.example.administrator.memosqver20.Model.DB;


//테이블 이름, 열 이름을 정의해 놓은 클래스
public class MemoDbSchema {

    public static final class MemoTable {

        public static final String TABLE_NAME = "memo"; //테이블 이름, db이름과 헷갈리지 말 것!

        public static final class Cols {

            public static final String UUID = "uuid";  //UUID
            public static final String WORDS_OF_THIS_WEEK = "word_of_this_week";  //이번주 말씀
            public static final String DAILY_QT = "daily_qt"; //오늘 말씀
            public static final String THANKS_GIVING = "thanksGiving"; //감사
            public static final String PRAYER = "prayer"; //기도
            public static final String JOURNAL = "journal"; //일기
            public static final String DATE = "date";  //날짜


        }


    }


}

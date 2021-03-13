package com.test.quizexampleinterview.database;

/**
 * Created by macstudent on 2017-12-05.
 */
    import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.quizexampleinterview.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
        private static volatile DbHelper dbHelper;
        private static final int DATABASE_VERSION = 1;
        // Database Name
        private static final String DATABASE_NAME = "Quiz";
        // tasks table name
        private static final String TABLE_QUEST = "quest";
        // tasks Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_QUES = "question";
        private static final String KEY_ANSWER = "answer"; //correct option
        private static final String KEY_OPTA= "opta"; //option a
        private static final String KEY_OPTB= "optb"; //option b
        private static final String KEY_OPTC= "optc"; //option c
        private static final String KEY_OPTD= "optd";

        private DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    public static DbHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (dbHelper == null) { //Check for the first time

            synchronized (DbHelper.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (dbHelper == null)
                    dbHelper = new DbHelper(ctx.getApplicationContext());
            }
        }

        return dbHelper;
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                    + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                    +KEY_OPTB +" TEXT," +KEY_OPTC +" TEXT," +KEY_OPTD+" TEXT)";
            db.execSQL(sql);
            Questions q1=new Questions(1,"How to pass the data between activities in Android?","Intent","Content Provider","Broadcast receiver","None of the Above","Intent","An Intent is used to connect one activity to another activity and having a message passing mechanism between activities.");
            addQuestion(db,q1);
            Questions q2=new Questions(2,"How many sizes are supported by Android?","Android supported all sizes","Android does not support all sizes","Android supports small,normal, large and extra-large sizes","Size is undefined in android","Android supports small,normal, large and extra-large sizes","X-large screens are having at least 960dp*720dp resolutions" +"Large screens are having at least 640dp*480dp resolutions"+"Normal screens are having at least 470dp*320dp resolutions"+"Small screens are having at least 426dp*320dp resolutions");
            addQuestion(db,q2);
            Questions q3=new Questions(3,"What is broadcast receiver in android?","It will react on broadcast announcements.","It will do background functionalities as services.","It will pass the data between activities.","None of the Above","It will react on broadcast announcements.","It is a main component of android. It reacts on the system broadcast announcements, and it acts as a gateway between outside application environment with your application.");
            addQuestion(db,q3);
            Questions q4=new Questions(4,"How to store heavy structured data in android?","Shared Preferences","Cursor","SQlite database","Not possible","SQlite database","We can store structured data in SQlite database only. SQlite database is very efficient and faster to read and store the data.");
            addQuestion(db, q4);
            Questions q5=new Questions(5,"How to get current location in android?","Using with GPRS","Using location provider","A & B","Network servers","A & B","GPRS and Location provider is used to fetch the current location of a phone as longitude and latitude.");
            addQuestion(db, q5);
            Questions q6=new Questions(6,"What is DDMS in android?","Dalvik memory server","Device memory server","Dalvik monitoring services","Dalvik debug monitor services","Dalvik debug monitor services","DDMS provides port forwarding, screen capturing, memory mapping, logcat, calls, SMS etc.");
            addQuestion(db, q6);
            Questions q7=new Questions(7,"What are the functionalities of HTTP Client interface?","Connection management","Cookies management","Authentication management","All of the above","All of the above","HTTP Client has the capabilities to manage connections, cookies and Authentication.");
            addQuestion(db,q7);
            Questions q8=new Questions(8,"How many orientations does android support?","4","10","2","None of the Above","4","According to the Google documentation, Android supports 4 types of orientations, those are landscape, portrait, sensor and No orientation");
            addQuestion(db, q8);
            Questions q9=new Questions(9,"How many protection levels are available in the android permission tag?","There are no permission tags available in android","Normal, kernel, application","Normal, dangerous, signature, and signatureOrsystem","None of the Above","Normal, dangerous, signature, and signatureOrsystem","Android is having four levels of protection in android permission tag. They are normal, dangerous, signature, and signatureOrsystem");
            addQuestion(db, q9);
            Questions q10=new Questions(10,"What is anchor view?","Same as list view","provides the information on respective relative positions","Same as relative layout","None of the Above","provides the information on respective relative positions","Anchor View provides the information on respective relative positions of views.");
            addQuestion(db, q10);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
// Create tables again
            onCreate(db);
        }
        // Adding new question
        public void addQuestion(SQLiteDatabase db, Questions quest) {
            ContentValues values = new ContentValues();
            values.put(KEY_QUES, quest.getQUESTION());
            values.put(KEY_ANSWER, quest.getANSWER());
            values.put(KEY_OPTA, quest.getOPTA());
            values.put(KEY_OPTB, quest.getOPTB());
            values.put(KEY_OPTC, quest.getOPTC());
            values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
            db.insert(TABLE_QUEST, null, values);
        }
        public List<Questions> getAllQuestions() {
            List<Questions> quesList = new ArrayList<Questions>();
            SQLiteDatabase db = this.getWritableDatabase();
// Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
            db=this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Questions quest = new Questions();
                    quest.setID(cursor.getInt(0));
                    quest.setQUESTION(cursor.getString(1));
                    quest.setANSWER(cursor.getString(2));
                    quest.setOPTA(cursor.getString(3));
                    quest.setOPTB(cursor.getString(4));
                    quest.setOPTC(cursor.getString(5));
                    quest.setOPTD(cursor.getString(6));
                    quesList.add(quest);
                } while (cursor.moveToNext());
            }
// return quest list
            db.close();
            return quesList;
        }
        public int rowcount()
        {
            int row=0;
            String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            row=cursor.getCount();
            return row;
        }
    }


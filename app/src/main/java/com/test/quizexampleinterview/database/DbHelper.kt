package com.test.quizexampleinterview.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.test.quizexampleinterview.model.Questions
import java.util.*

/**
 * Created by macstudent on 2017-12-05.
 */
class DbHelper private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT," + KEY_OPTC + " TEXT," + KEY_OPTD + " TEXT)")
        db.execSQL(sql)
        val q1 = Questions(1, "How to pass the data between activities in Android?", "Intent", "Content Provider", "Broadcast receiver", "None of the Above", "Intent", "An Intent is used to connect one activity to another activity and having a message passing mechanism between activities.")
        addQuestion(db, q1)
        val q2 = Questions(2, "How many sizes are supported by Android?", "Android supported all sizes", "Android does not support all sizes", "Android supports small,normal, large and extra-large sizes", "Size is undefined in android", "Android supports small,normal, large and extra-large sizes", "X-large screens are having at least 960dp*720dp resolutions" + "Large screens are having at least 640dp*480dp resolutions" + "Normal screens are having at least 470dp*320dp resolutions" + "Small screens are having at least 426dp*320dp resolutions")
        addQuestion(db, q2)
        val q3 = Questions(3, "What is broadcast receiver in android?", "It will react on broadcast announcements.", "It will do background functionalities as services.", "It will pass the data between activities.", "None of the Above", "It will react on broadcast announcements.", "It is a main component of android. It reacts on the system broadcast announcements, and it acts as a gateway between outside application environment with your application.")
        addQuestion(db, q3)
        val q4 = Questions(4, "How to store heavy structured data in android?", "Shared Preferences", "Cursor", "SQlite database", "Not possible", "SQlite database", "We can store structured data in SQlite database only. SQlite database is very efficient and faster to read and store the data.")
        addQuestion(db, q4)
        val q5 = Questions(5, "How to get current location in android?", "Using with GPRS", "Using location provider", "A & B", "Network servers", "A & B", "GPRS and Location provider is used to fetch the current location of a phone as longitude and latitude.")
        addQuestion(db, q5)
        val q6 = Questions(6, "What is DDMS in android?", "Dalvik memory server", "Device memory server", "Dalvik monitoring services", "Dalvik debug monitor services", "Dalvik debug monitor services", "DDMS provides port forwarding, screen capturing, memory mapping, logcat, calls, SMS etc.")
        addQuestion(db, q6)
        val q7 = Questions(7, "What are the functionalities of HTTP Client interface?", "Connection management", "Cookies management", "Authentication management", "All of the above", "All of the above", "HTTP Client has the capabilities to manage connections, cookies and Authentication.")
        addQuestion(db, q7)
        val q8 = Questions(8, "How many orientations does android support?", "4", "10", "2", "None of the Above", "4", "According to the Google documentation, Android supports 4 types of orientations, those are landscape, portrait, sensor and No orientation")
        addQuestion(db, q8)
        val q9 = Questions(9, "How many protection levels are available in the android permission tag?", "There are no permission tags available in android", "Normal, kernel, application", "Normal, dangerous, signature, and signatureOrsystem", "None of the Above", "Normal, dangerous, signature, and signatureOrsystem", "Android is having four levels of protection in android permission tag. They are normal, dangerous, signature, and signatureOrsystem")
        addQuestion(db, q9)
        val q10 = Questions(10, "What is anchor view?", "Same as list view", "provides the information on respective relative positions", "Same as relative layout", "None of the Above", "provides the information on respective relative positions", "Anchor View provides the information on respective relative positions of views.")
        addQuestion(db, q10)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST)
        // Create tables again
        onCreate(db)
    }

    // Adding new question
    fun addQuestion(db: SQLiteDatabase, quest: Questions) {
        val values = ContentValues()
        values.put(KEY_QUES, quest.qUESTION)
        values.put(KEY_ANSWER, quest.aNSWER)
        values.put(KEY_OPTA, quest.oPTA)
        values.put(KEY_OPTB, quest.oPTB)
        values.put(KEY_OPTC, quest.oPTC)
        values.put(KEY_OPTD, quest.oPTD)
        // Inserting Row
        db.insert(TABLE_QUEST, null, values)
    }

    // Select All Query
    val allQuestions:
    // looping through all rows and adding to list
    // return quest list
            List<Questions>
        get() {
            val quesList: MutableList<Questions> = ArrayList()
            var db = this.writableDatabase
            // Select All Query
            val selectQuery = "SELECT  * FROM " + TABLE_QUEST
            db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val quest = Questions()
                    quest.iD = cursor.getInt(0)
                    quest.qUESTION = cursor.getString(1)
                    quest.aNSWER = cursor.getString(2)
                    quest.oPTA = cursor.getString(3)
                    quest.oPTB = cursor.getString(4)
                    quest.oPTC = cursor.getString(5)
                    quest.oPTD = cursor.getString(6)
                    quesList.add(quest)
                } while (cursor.moveToNext())
            }
            // return quest list
            db.close()
            return quesList
        }

    fun rowcount(): Int {
        var row = 0
        val selectQuery = "SELECT  * FROM " + TABLE_QUEST
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        row = cursor.count
        return row
    }

    companion object {
        @Volatile
        private var dbHelper: DbHelper? = null
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "Quiz"

        // tasks table name
        private const val TABLE_QUEST = "quest"

        // tasks Table Columns names
        private const val KEY_ID = "id"
        private const val KEY_QUES = "question"
        private const val KEY_ANSWER = "answer" //correct option
        private const val KEY_OPTA = "opta" //option a
        private const val KEY_OPTB = "optb" //option b
        private const val KEY_OPTC = "optc" //option c
        private const val KEY_OPTD = "optd"
        @JvmStatic
        fun getInstance(ctx: Context): DbHelper? {
            /**
             * use the application context as suggested by CommonsWare.
             * this will ensure that you dont accidentally leak an Activitys
             * context (see this article for more information:
             * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
             */
            if (dbHelper == null) { //Check for the first time
                synchronized(DbHelper::class.java) {   //Check for the second time.
                    //if there is no instance available... create new one
                    if (dbHelper == null) dbHelper = DbHelper(ctx.applicationContext)
                }
            }
            return dbHelper
        }
    }
}
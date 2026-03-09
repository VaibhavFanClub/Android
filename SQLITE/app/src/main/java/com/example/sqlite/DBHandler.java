package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mycourses";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DURATION_COL = "duration";
    private static final String DESCRIPTION_COL = "description";
    private static final String TRACKS_COL = "tracks";
    public DBHandler(@Nullable Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";
        db.execSQL(query);
    }
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public ArrayList<CourseDetail> fetchDetails()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<CourseDetail> courseDetails=new ArrayList<>();
        while(cursor.moveToNext())
        {
            CourseDetail course=new CourseDetail();
            course.id=cursor.getInt(0);
            course.courseName=cursor.getString(1);
            course.courseDescription=cursor.getString(2);
            course.courseDuration=cursor.getString(3);
            course.courseTrack=cursor.getString(4);
            courseDetails.add(course);
        }
        return courseDetails;
    }
    public void updateCourse(CourseDetail courseDetail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DURATION_COL,courseDetail.courseDuration);
        contentValues.put(NAME_COL,courseDetail.courseName);
        contentValues.put(TRACKS_COL,courseDetail.courseTrack);
        contentValues.put(DESCRIPTION_COL,courseDetail.courseDescription);
        db.update(TABLE_NAME,contentValues,ID_COL+" ="+courseDetail.id,null);
    }
    public void deleteCourse(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID_COL+" = ?",new String[]{String.valueOf(id)});
    }

}
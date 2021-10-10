package com.example.notes.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.Dao.Notes_Dao;
import com.example.notes.Entity.Notes_Entity;

@androidx.room.Database(entities = {Notes_Entity.class} , version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract Notes_Dao notesDao();
    public static NotesDatabase INSTANCE;

    //if there is an instance for the database class it will directly return the object
    //else it will instantiate the database object
    public static NotesDatabase getDatabaseInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,
                    "Notes_Table").allowMainThreadQueries().build();
            //allowMainThreadQueries().build(); is alternative method for
            //similar functionality of coroutines in kotlin
        }
        return INSTANCE;
    }

}

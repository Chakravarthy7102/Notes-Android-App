package com.example.notes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.Entity.Notes_Entity;

import java.util.List;

@Dao
public interface Notes_Dao {
    //we call this every method in repository
    @Query("SELECT * FROM Notes_Table")
    LiveData<List<Notes_Entity>> getAllData();

    @Query("SELECT * FROM Notes_Table ORDER BY priority DESC")
    LiveData<List<Notes_Entity>> highToLowDao();

    @Query("SELECT * FROM Notes_Table ORDER BY priority ASC")
    LiveData<List<Notes_Entity>> lowToHighDao();

    @Insert
    void insertNotes(Notes_Entity... notes);

    @Query("DELETE FROM Notes_Table WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes_Entity updateNotes);
}

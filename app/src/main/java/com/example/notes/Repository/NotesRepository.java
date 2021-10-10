package com.example.notes.Repository;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.Notes_Dao;
import com.example.notes.Database.NotesDatabase;
import com.example.notes.Entity.Notes_Entity;

import java.util.List;

public class NotesRepository {
    public Notes_Dao notesDao;
    public LiveData<List<Notes_Entity>> getAllNotes;
    public LiveData<List<Notes_Entity>> lowToHighRepo;
    public LiveData<List<Notes_Entity>> highToLowRepo;
     //class constructor to get the context
    //and get the instances of the other layered classes
    public NotesRepository (Application application){
        NotesDatabase database=NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes=notesDao.getAllData();
        lowToHighRepo=notesDao.lowToHighDao();
        highToLowRepo=notesDao.highToLowDao();
    }
    //method for inserting the data
    public void insertNotesRepo(Notes_Entity notes){
        notesDao.insertNotes(notes);
    }
    //method for deleting the data
    public void deleteNotesRepo(int id){
        notesDao.deleteNotes(id);
    }
    //method for updating the data
    public void updateNotesRepo(Notes_Entity notes){
        notesDao.updateNotes(notes);
    }




}

package com.example.notes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.Entity.Notes_Entity;
import com.example.notes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes_Entity>> getAllDataView;
    public LiveData<List<Notes_Entity>> lowToHighView;
    public LiveData<List<Notes_Entity>> highToLowView;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository=new NotesRepository(application);
        getAllDataView=notesRepository.getAllNotes;
        lowToHighView=notesRepository.lowToHighRepo;
        highToLowView=notesRepository.highToLowRepo;
    }
    //method for inserting the data in View Model
    public void insertNotesView(Notes_Entity notes){
        notesRepository.insertNotesRepo(notes);
    }
    //method for deleting the data inside the View Model
    public void deleteNotesView(int id){
        notesRepository.deleteNotesRepo(id);
    }
    //method for updating the data inside the View Model
    public void updateNotesView(Notes_Entity notes){
        notesRepository.updateNotesRepo(notes);
    }


}


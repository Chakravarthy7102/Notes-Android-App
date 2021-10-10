package com.example.notes.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.icu.util.IndianCalendar;
import android.os.Build;
import android.os.Bundle;

import com.example.notes.Entity.Notes_Entity;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
    private String titleInsert;
    private String subtitleInsert;
    private String noteInsert;
    private ActivityInsertNoteBinding binding;
    private NotesViewModel notesViewModel;
    private String priority="1";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel=new ViewModelProvider(this).get(NotesViewModel.class);
        //green priority button
        binding.greenCircle.setOnClickListener(v -> {
            priority="1";
            binding.greenCircle.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redCircle.setImageResource(0);
            binding.yellowCircle.setImageResource(0);
        });
        //red priority button
        binding.redCircle.setOnClickListener(v -> {
            priority="3";
            binding.greenCircle.setImageResource(0);
            binding.redCircle.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowCircle.setImageResource(0);
        });
        //yellow priority method
        binding.yellowCircle.setOnClickListener(v -> {
            priority="2";
            binding.greenCircle.setImageResource(0);
            binding.redCircle.setImageResource(0);
            binding.yellowCircle.setImageResource(R.drawable.ic_baseline_done_24);
        });
        //done listener
        binding.doneFab.setOnClickListener(v -> {
            titleInsert=binding.titleEditor.getText().toString().trim();
            subtitleInsert=binding.subTitleEditor.getText().toString().trim();
            noteInsert=binding.textEditor.getText().toString().trim();
            createNotes(titleInsert,subtitleInsert,noteInsert,priority);
        });
    }

    //method for creating notes / inserting notes
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotes(String titleInsert, String subtitleInsert, String noteInsert,String priority) {
        //Date date = new Date();
        //dd/MM/yyyy HH:mm:ss optional date and time format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date1 =dtf.format(now);

        Notes_Entity notes1=new Notes_Entity();
        notes1.title=titleInsert;
        notes1.subTitle=subtitleInsert;
        notes1.notes=noteInsert;
        notes1.notesPriority=priority;
        notes1.date=date1;
        notesViewModel.insertNotesView(notes1);
        finish();
    }


}

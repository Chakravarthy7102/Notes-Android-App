package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notes.Entity.Notes_Entity;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityUpdateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateActivity extends AppCompatActivity {
   private ActivityUpdateBinding updateBinding;
   private NotesViewModel notesViewModel;
   private String notesPriority;
   private int viewID;
   private String currentPriority;
   private static final int DELETE=R.id.deleteNotes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateBinding=ActivityUpdateBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(updateBinding.getRoot());
        notesViewModel=new ViewModelProvider(UpdateActivity.this).get(NotesViewModel.class);

        //getting data from the intent into this activity
       String notesTitle=getIntent().getStringExtra("title") ;
       String notesSubTitle=getIntent().getStringExtra("subtitle") ;
       String notes=getIntent().getStringExtra("notes") ;
              currentPriority=getIntent().getStringExtra("priority");
       viewID=getIntent().getIntExtra("id",0);
        updateBinding.titleUpdate.setText(notesTitle);
        updateBinding.subTitleUpdate.setText(notesSubTitle);
        updateBinding.notesUpdate.setText(notes);
       clicksBinding();
       priorityClicks();
        updateBinding.updateFab.setOnClickListener(v -> {
           String mNotesTitle=updateBinding.titleUpdate.getText().toString().trim();
           String mNotesSubTitle=updateBinding.subTitleUpdate.getText().toString().trim();
           String mNotes=updateBinding.notesUpdate.getText().toString().trim();

           updateNotes(mNotesTitle,mNotesSubTitle,mNotes,notesPriority);

        });

    }
    //updating the priority with respect the previous data
    private void clicksBinding() {
        if(!TextUtils.isEmpty(currentPriority)||currentPriority!=null){
            if(currentPriority.equals("1")){
                updateBinding.greenCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);

            }else if(currentPriority.equals("2")){
                updateBinding.yellowCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);
            }else{
                updateBinding.redCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);
            }
        }
    }

    //updating the priority
    private void priorityClicks() {
        updateBinding.greenCircleUpdate.setOnClickListener(v1 -> {
            notesPriority="1";
            updateBinding.greenCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);
            updateBinding.redCircleUpdate.setImageResource(0);
            updateBinding.yellowCircleUpdate.setImageResource(0);
        });
        updateBinding.yellowCircleUpdate.setOnClickListener(v1 -> {
            notesPriority="2";
            updateBinding.greenCircleUpdate.setImageResource(0);
            updateBinding.redCircleUpdate.setImageResource(0);
            updateBinding.yellowCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);
        });
        updateBinding.redCircleUpdate.setOnClickListener(v1 -> {
            notesPriority="3";
            updateBinding.greenCircleUpdate.setImageResource(0);
            updateBinding.redCircleUpdate.setImageResource(R.drawable.ic_baseline_done_24);
            updateBinding.yellowCircleUpdate.setImageResource(0);

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateNotes(String notesTitle, String notesSubTitle, String notes1, String notesPriority1) {
        Notes_Entity updatedNotes=new Notes_Entity();
        updatedNotes.id=viewID;
        updatedNotes.title=notesTitle;
        updatedNotes.subTitle=notesSubTitle;
        updatedNotes.notes=notes1;
        updatedNotes.notesPriority=notesPriority1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date_new =dtf.format(now);
        updatedNotes.date=date_new;
        notesViewModel.updateNotesView(updatedNotes);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        TextView yes,no;
       int id=item.getItemId();
        if (id == DELETE) {
            //for
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(UpdateActivity.this);
            View view= LayoutInflater.from(UpdateActivity.this).
                    inflate(R.layout.delete_bottom_sheet,
                            findViewById(R.id.bottomSheet));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
           //after selecting any one option from the bottomSheet
            yes=view.findViewById(R.id.delete_yes);
            no=view.findViewById(R.id.delete_no);
            yes.setOnClickListener(v -> {
                notesViewModel.deleteNotesView(viewID);
                finish();
            });
            no.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });


        }
        return true;
    }
}
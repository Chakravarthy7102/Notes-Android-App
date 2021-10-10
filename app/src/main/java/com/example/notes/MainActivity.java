package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.multidex.MultiDex;;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import com.example.notes.Activity.InsertNoteActivity;
import com.example.notes.Adapter.NotesAdapter;
import com.example.notes.Entity.Notes_Entity;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding mainBinding;
   private NotesViewModel notesViewModel;
   private NotesAdapter notesAdapter;
   private RecyclerView recyclerView;
   private List<Notes_Entity> getALLNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        //initialize the ViewModel provider
        notesViewModel=new ViewModelProvider(this).get(NotesViewModel.class);
        mainBinding.noFilter.setBackgroundResource(R.drawable.selected_filter);
        recyclerView=findViewById(R.id.notesRecyclerView);
        //changing filters changes order of the data
        changeFilter();
        mainBinding.addFab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
        });
    }
    //setting different methods for different filters
    private void getDefaultNotes(){
        notesViewModel.getAllDataView.observe(MainActivity.this, notes_entities -> {
            mSetAdapter(notes_entities);
            getALLNotesList=notes_entities;
        });
    }
    private void  getLowToHigh() {
        notesViewModel.lowToHighView.observe(MainActivity.this, notes_entities ->{
            mSetAdapter(notes_entities);
            getALLNotesList=notes_entities;
        });
    }
    private void  getHighToLoW() {
        notesViewModel.highToLowView.observe(MainActivity.this, notes_entities -> {
            mSetAdapter(notes_entities);
            getALLNotesList=notes_entities;
        });
    }

    //method for setting the adapter
    private void mSetAdapter(List<Notes_Entity> notes_entities) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        notesAdapter=new NotesAdapter(MainActivity.this,notes_entities);
        recyclerView.setAdapter(notesAdapter);
    }

    private void changeFilter() {
        getDefaultNotes();
        mainBinding.noFilter.setOnClickListener(v -> {
            mainBinding.noFilter.setBackgroundResource(R.drawable.selected_filter);
            mainBinding.highToLowFilter.setBackgroundResource(R.drawable.back_yes);
            mainBinding.lowToHighFilter.setBackgroundResource(R.drawable.back_yes);
            getDefaultNotes();
        });
        mainBinding.highToLowFilter.setOnClickListener(v -> {
            mainBinding.noFilter.setBackgroundResource(R.drawable.back_yes);
            mainBinding.highToLowFilter.setBackgroundResource(R.drawable.selected_filter);
            mainBinding.lowToHighFilter.setBackgroundResource(R.drawable.back_yes);
            getHighToLoW();
        });
        mainBinding.lowToHighFilter.setOnClickListener(v -> {
            mainBinding.noFilter.setBackgroundResource(R.drawable.back_yes);
            mainBinding.highToLowFilter.setBackgroundResource(R.drawable.back_yes);
            mainBinding.lowToHighFilter.setBackgroundResource(R.drawable.selected_filter);
            getLowToHigh();

        });


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }


        });
        return true;
    }
    private void notesFilter(String newText) {
       ArrayList<Notes_Entity> filterNames=new ArrayList<>();
       for(Notes_Entity note:this.getALLNotesList){
           if(note.title.contains(newText)||note.subTitle.contains(newText)){
               filterNames.add(note);
           }
       }
        this.notesAdapter.searchedNotes(filterNames);
    }


}
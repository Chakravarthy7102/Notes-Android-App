package com.example.notes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Activity.UpdateActivity;
import com.example.notes.Entity.Notes_Entity;
import com.example.notes.MainActivity;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    Context context;
    List<Notes_Entity> notes_entities;
    List<Notes_Entity> allNotes;
    public NotesAdapter(Context context, List<Notes_Entity> notes_entities) {
        this.context=context;
        this.notes_entities=notes_entities;
        allNotes=new ArrayList<>(notes_entities);
    }
    //changing the data set from the filtered keyWords and updating the list
    public void searchedNotes(List<Notes_Entity> filteredNotes){
        this.notes_entities=filteredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recylcle_items,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes_Entity currentNotes=notes_entities.get(position);
        holder.titleView.setText(currentNotes.title);
        holder.subtitle.setText(currentNotes.subTitle);
        holder.notesDate.setText(currentNotes.date);
        String currentPriority= currentNotes.notesPriority;

        if(!TextUtils.isEmpty(currentPriority)||currentPriority!=null){
            if(currentPriority.equals("1")){
                holder.priorityView.setBackgroundResource(R.drawable.circle_green);
            }else if(currentPriority.equals("2")){
                holder.priorityView.setBackgroundResource(R.drawable.circle_yellow);
            }else{
                holder.priorityView.setBackgroundResource(R.drawable.circle_red);
            }
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, UpdateActivity.class);
            intent.putExtra("id",currentNotes.id);
            intent.putExtra("title",currentNotes.title);
            intent.putExtra("subtitle",currentNotes.subTitle);
            intent.putExtra("notes",currentNotes.notes);
            intent.putExtra("priority",currentNotes.notesPriority);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes_entities.size();
    }


    static class NotesViewHolder extends RecyclerView.ViewHolder{
        View priorityView;
        TextView titleView;
        TextView subtitle;
        TextView notesDate;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            priorityView= itemView.findViewById(R.id.notesPriority);
            titleView=itemView.findViewById(R.id.notesTitle);
            subtitle= itemView.findViewById(R.id.notesSubTitle);
            notesDate=itemView.findViewById(R.id.notesDate);
        }
    }
}

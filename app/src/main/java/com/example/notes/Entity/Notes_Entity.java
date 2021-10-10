package com.example.notes.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Table")
public class Notes_Entity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "subTitle")
    public String subTitle;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "priority")
    public String notesPriority;

    @ColumnInfo(name = "notes")
    public String notes;
}

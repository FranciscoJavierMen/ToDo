package com.example.todo.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.dao.noteDao;
import com.example.todo.db.noteDatabase;
import com.example.todo.entities.noteEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class noteRepository {

    private noteDao noteDao;
    private LiveData<List<noteEntity>> allNotes;

    public noteRepository(Application application){
        noteDatabase db = noteDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    LiveData<List<noteEntity>> getAllNotes(){
        return allNotes;
    }


}


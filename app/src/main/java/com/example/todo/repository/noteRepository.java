package com.example.todo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.dao.noteDao;
import com.example.todo.db.noteDatabase;
import com.example.todo.entities.noteEntity;

import java.util.Calendar;
import java.util.Date;

public class noteRepository {

    private noteDao noteDao;
    private LiveData<noteEntity> allNotes;
    private noteDatabase noteDatabase;

    public noteRepository(Context context){
        noteDatabase db = noteDatabase.getAppDatabase(context);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insertTask(String title,
                           String description) {

        noteEntity note = new noteEntity();
        note.setTitle(title);
        note.setDescription(description);

        insertTask(note);
    }

    //Método para insertar notas
    public void insertTask(final noteEntity note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.noteDao().insertNote(note);
                return null;
            }
        }.execute();
    }

    //Método para actualizar notas
    public void updateTask(final noteEntity note) {
        Date currentTime = Calendar.getInstance().getTime();
        note.setCreated_at(currentTime);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.noteDao().updateNote(note);
                return null;
            }
        }.execute();
    }

    //Método para eliminar notas
    public void deleteTask(final noteEntity note) {
        final LiveData<noteEntity> task = getTask(note.getId());
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    noteDatabase.noteDao().deleteNote(note);
                    return null;
                }
            }.execute();
        }
    }

    //Método para contar el número de notas
    public void countTask(final noteEntity note) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.noteDao().countNotes();
                return null;
            }
        }.execute();
    }


    public LiveData<noteEntity> getTask(int id) {
        return noteDatabase.noteDao().getNote(id);
    }

    public LiveData<noteEntity> getTasks() {
        return noteDatabase.noteDao().getAllNotes();
    }

}


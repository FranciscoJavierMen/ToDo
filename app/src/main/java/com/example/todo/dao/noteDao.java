package com.example.todo.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.entities.noteEntity;

import java.util.List;


@Dao
public interface noteDao {
    //Selecciona todas las notas
    @Query("SELECT * FROM note_table ORDER BY created_at ASC")
    LiveData<List<noteEntity>> getAllNotes();

    //Selecciona una nota en específico
    @Query("SELECT * FROM note_table WHERE id=:noteId")
    LiveData<List<noteEntity>> getNote(int noteId);

    //Inserta una nueva nota
    @Insert
    void insertNote(noteEntity note);

    //Acualiza una nota
    @Update
    void updateNote(noteEntity note);

    //Elimina una nota
    @Delete
    void deleteNote(noteEntity note);

    //Obtener numero de notas
    @Query("SELECT COUNT(*) from note_table")
    int countNotes();
}

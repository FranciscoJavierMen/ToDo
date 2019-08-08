package com.example.todo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.todo.dao.noteDao;
import com.example.todo.entities.noteEntity;
import com.example.todo.utils.dateConverter;

@Database(entities = {noteEntity.class}, version = 1)
public abstract class noteDatabase extends RoomDatabase {

    public  abstract noteDao noteDao();
    private static noteDatabase INSTANCE;

    @TypeConverters({dateConverter.class})
    public abstract class AppDatabase extends RoomDatabase {
        public abstract noteDao noteDao();
    }


    public static noteDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    noteDatabase.class, "note_database")
            .allowMainThreadQueries()
            .build();
        }

        return INSTANCE;
    }

    //destruye la instancia
    public  static void destroyInstance(){
        INSTANCE = null;
    }
}

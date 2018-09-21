package com.latenightcode.note.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.latenightcode.note.model.Note;

@Database(entities = {Note.class}, version = 2)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbNoteAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        PopulateDbNoteAsyncTask(NoteDatabase db){
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(new Note("Title1", "Description1", 1));
            noteDao.insert(new Note("Title2", "Description2", 2));
            noteDao.insert(new Note("Title3", "Description3", 3));
            return null;
        }
    }

}

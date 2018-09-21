package com.latenightcode.note.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.latenightcode.note.data.NoteRepository;
import com.latenightcode.note.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel{

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}

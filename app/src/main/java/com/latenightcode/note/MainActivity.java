package com.latenightcode.note;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.latenightcode.note.adapter.NotesAdapter;
import com.latenightcode.note.model.Note;
import com.latenightcode.note.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.main_activity_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                notesAdapter = new NotesAdapter(notes, getApplicationContext());
                mRecyclerView.setAdapter(notesAdapter);
                notesAdapter.notifyDataSetChanged();
            }
        });


    }

    public void addNewNote(View view) {
        startActivity(new Intent(this, AddNewNoteActivity.class));
    }

}

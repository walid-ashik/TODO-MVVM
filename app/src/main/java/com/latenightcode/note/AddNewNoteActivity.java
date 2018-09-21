package com.latenightcode.note;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.latenightcode.note.model.Note;
import com.latenightcode.note.viewmodel.NoteViewModel;

public class AddNewNoteActivity extends AppCompatActivity {

    EditText mNewTitle, mNewDesc, mNewPriority;
    private NoteViewModel noteViewModel;
    private String title, desc, priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        mNewTitle = findViewById(R.id.editText_title);
        mNewDesc = findViewById(R.id.editText_description);
        mNewPriority = findViewById(R.id.editText_priority);

        if(getIntent().getExtras() != null){
            title = getIntent().getStringExtra("title");
            desc = getIntent().getStringExtra("desc");
            priority = getIntent().getStringExtra("priority");

            Toast.makeText(this, priority, Toast.LENGTH_SHORT).show();

            mNewTitle.setText(title);
            mNewDesc.setText(desc);
            mNewPriority.setText(priority);
        }



        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }

    public void insertNewNote(View view) {

        String newTitle = mNewTitle.getText().toString();
        String newDesc = mNewDesc.getText().toString();
        String newPriority = mNewPriority.getText().toString();

        if(newTitle.isEmpty() || newDesc.isEmpty() || newPriority.isEmpty()){
            Toast.makeText(this, "input all field", Toast.LENGTH_SHORT).show();
            return;
        }else{
            noteViewModel.insert(new Note(newTitle, newDesc, Integer.valueOf(newPriority)));
            Toast.makeText(this, "updated new note", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void deleteNote(View view) {

    }
}

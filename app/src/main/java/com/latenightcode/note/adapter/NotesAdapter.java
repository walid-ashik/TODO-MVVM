package com.latenightcode.note.adapter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.latenightcode.note.AddNewNoteActivity;
import com.latenightcode.note.R;
import com.latenightcode.note.model.Note;
import com.latenightcode.note.viewmodel.NoteViewModel;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList;
    private Context context;

    public NotesAdapter(List<Note> noteList, Context context){
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        final Note currentNote = noteList.get(position);

        holder.mNoteTitle.setText(currentNote.getTitle());
        holder.mNoteDescription.setText(currentNote.getDescription());
        holder.mNotePriority.setText(String.valueOf(currentNote.getPriority()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AddNewNoteActivity.class);
                i.putExtra("title", currentNote.getTitle());
                i.putExtra("desc", currentNote.getDescription());
                i.putExtra("priority", String.valueOf(currentNote.getPriority()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView mNoteTitle, mNoteDescription, mNotePriority;

        public NotesViewHolder(View itemView) {
            super(itemView);

            mNoteTitle = itemView.findViewById(R.id.item_note_title);
            mNoteDescription = itemView.findViewById(R.id.item_note_description);
            mNotePriority = itemView.findViewById(R.id.item_note_priority);

        }
    }
}

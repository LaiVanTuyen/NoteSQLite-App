package com.example.notesqlite.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notesqlite.R
import com.example.notesqlite.databinding.ActivityAddNoteBinding
import com.example.notesqlite.databinding.ActivityUpdateBinding
import com.example.notesqlite.model.Note
import com.example.notesqlite.model.NoteDatabaseHelper

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteID:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteID = intent.getIntExtra("note_id",-1)
        if (noteID==-1){
            finish()
            return
        }
        val note = db.getNoteById(noteID)
        binding.updateTxtTitle.setText(note.title)
        binding.updateTxtContent.setText(note.content)
        binding.btnSaveUpdate.setOnClickListener{
            val newTitle = binding.updateTxtTitle.text.toString()
            val newContent = binding.updateTxtContent.text.toString()
            val updateNote = Note(noteID,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Change Saved",Toast.LENGTH_SHORT).show()
        }
    }
}
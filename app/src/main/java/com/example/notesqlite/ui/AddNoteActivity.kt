package com.example.notesqlite.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesqlite.model.Note
import com.example.notesqlite.model.NoteDatabaseHelper
import com.example.notesqlite.R
import com.example.notesqlite.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDatabaseHelper(this)
        binding.btnSave.setOnClickListener{
            val title = binding.editTxtTitle.text.toString()
            val content = binding.editTxtContent.text.toString()
            val note = Note(0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Note added",Toast.LENGTH_SHORT).show()
        }
    }
}
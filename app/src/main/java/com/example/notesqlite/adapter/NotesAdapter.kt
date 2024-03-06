package com.example.notesqlite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesqlite.R
import com.example.notesqlite.model.Note
import com.example.notesqlite.model.NoteDatabaseHelper
import com.example.notesqlite.ui.AddNoteActivity
import com.example.notesqlite.ui.UpdateNoteActivity


class NotesAdapter(private var notes:List<Note>, context:Context): RecyclerView.Adapter<NotesAdapter.NotViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NotViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton:ImageView = itemView.findViewById(R.id.btnUpdate)
        val deleteButton:ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.node_item,parent,false)
        return NotViewHolder(view)
    }

    override fun getItemCount(): Int  = notes.size

    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)

        }

        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }




}
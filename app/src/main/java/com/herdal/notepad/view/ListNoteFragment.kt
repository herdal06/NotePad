package com.herdal.notepad.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdal.notepad.R
import com.herdal.notepad.adapter.NoteAdapter
import com.herdal.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list_note.view.*


class ListNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_note, container, false)

        // recylerView
        val adapter = NoteAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        // note viewmodel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.listAllNotes.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        view.floatingActionButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_listNoteFragment_to_addNoteFragment)
        }

        return view
    }
}
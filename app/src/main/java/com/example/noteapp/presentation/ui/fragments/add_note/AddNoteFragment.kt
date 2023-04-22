package com.example.noteapp.presentation.ui.fragments.add_note

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.ui.base.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.presentation.ui.fragments.note.NoteFragment.Companion.ARGS_EDIT
import com.example.noteapp.presentation.utils.UIState
import com.example.noteapp.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteFragment : BaseFragment(R.layout.fragment_add_note) {

    private val viewModel: AddNoteViewModel by viewModels()
    private val binding by viewBinding(FragmentAddNoteBinding::bind)
    private var note: Note? = null
    private var noteIsNull = true


    override fun setupRequests() {
        addOrEdit()
    }

    override fun initSubscribers() {
        setupSubscribers()
        collectEditNote()
    }

    @SuppressLint("SetTextI18n")
    private fun getNote() {
        if (arguments?.getSerializable(ARGS_EDIT) == null) {
            note = Note()
        } else {
            note = arguments?.getSerializable(ARGS_EDIT) as Note
            binding.etTitle.setText(note!!.title)
            binding.etDesc.setText(note!!.descriptions)
            binding.btnSave.text = getString(R.string.edit)
            noteIsNull = false
        }
    }

    private fun addOrEdit() {
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.etTitle.text!!.isNotEmpty() && binding.etDesc.text!!.isNotEmpty()) {
                    note?.title = etTitle.text.toString()
                    note?.descriptions = etDesc.text.toString()
                    if (noteIsNull) {
                        viewModel.createNote(note!!)
                    } else {
                        viewModel.editNote(note!!)
                    }
                } else {
                    context?.showToast(
                        getString(R.string.no_emty)
                    )
                }
            }
        }
    }

    override fun setupSubscribers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun initialize() {
        getNote()
    }

    private fun collectEditNote() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
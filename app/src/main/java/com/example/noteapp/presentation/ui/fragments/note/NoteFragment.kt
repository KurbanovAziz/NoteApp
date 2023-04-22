package com.example.noteapp.presentation.ui.fragments.note


import android.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.ui.base.BaseFragment
import com.example.noteapp.presentation.ui.fragments.note.adapter.NoteAdapter
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.presentation.utils.UIState
import com.example.noteapp.presentation.utils.buildAndShow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteFragment : BaseFragment(R.layout.fragment_note) {

    private val viewModel: NotesViewModel by viewModels()
    private val binding by viewBinding(FragmentNoteBinding::bind)
    private val adapterNote by lazy { NoteAdapter(this::onClick, this::onLongClick) }
    private var title = ""

    override fun initialize() {
        binding.recyclerView.adapter = adapterNote
    }

    override fun setupRequests() {
        viewModel.getAllNotes()
    }

    override fun setupSubscribers() {
        viewModel.getAllNotesState.collectUIState(
            state = {
                binding.progressCircular.isVisible = it is UIState.Loading
            },
            onSuccess = {
                adapterNote.submitList(it)
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deleteNotesState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            setupRequests()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
    }

    private fun onClick(note: Note) {
        findNavController().navigate(R.id.addNoteFragment, bundleOf(ARGS_EDIT to note))
    }

    private fun onLongClick(note: Note) {
        title = note.title
        AlertDialog.Builder(context)
            .setTitle("Are you sure you want to delete $title?")
            .buildAndShow(
                positiveButtonAction = {
                    viewModel.deleteNote(note)
                },
                negativeButtonAction = {}
            )
    }

    companion object {
        const val ARGS_EDIT = "note"
    }
}
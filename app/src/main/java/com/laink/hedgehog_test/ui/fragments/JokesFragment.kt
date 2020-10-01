package com.laink.hedgehog_test.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.laink.hedgehog_test.R
import com.laink.hedgehog_test.adapters.JokesAdapter
import com.laink.hedgehog_test.ui.viewmodels.JokesViewModel
import com.laink.hedgehog_test.util.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.jokes_fragment.*

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.jokes_fragment) {

    private val viewModel: JokesViewModel by viewModels()
    private lateinit var jokesAdapter: JokesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        bt_reload.setOnClickListener {
            val number = jokes_edit_text.text.toString()

            if (checkNumber(number)) {
                viewModel.getSomeJokes(number)

                image_signature.visibility = View.GONE
            }
        }

        observeJokesLoading()
    }

    private fun observeJokesLoading() {
        viewModel.resultJokes.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Response.Success -> {
                    stopProgress()

                    response.data?.let {
                        jokesAdapter.differ.submitList(it.value)
                    }
                }
                is Response.Error -> {
                    stopProgress()
                    response.message?.let { message ->
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                    }
                }
                is Response.Loading -> {
                    startProgress()
                }
            }
        })
    }

    private fun checkNumber(number: String): Boolean {
        val isNumber = number.toIntOrNull() != null

        if (!isNumber) {
            Snackbar.make(requireView(), "Enter a number", Snackbar.LENGTH_LONG).show()
        }

        return isNumber
    }

    private fun setUpRecyclerView() {
        jokesAdapter = JokesAdapter()

        jokes_recyclerview.apply {
            adapter = jokesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun startProgress() {
        progress_bar_jokes.visibility = View.VISIBLE
    }

    private fun stopProgress() {
        progress_bar_jokes.visibility = View.GONE
    }
}
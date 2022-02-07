package com.techand.vboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    private val videoAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val viewModel: HomeViewModel by lazy {
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        }

        setRv(view)

        lifecycleScope.launch {
            viewModel.fetchPosts().collectLatest { pagingData ->
                videoAdapter.submitData(pagingData)
            }
        }

        return view
    }

    private fun setRv(view: View) {
        val rv = view.findViewById<View>(R.id.home_recycler) as RecyclerView
        rv.apply {
            layoutManager = LinearLayoutManager(rv.context)
            adapter = videoAdapter
        }
    }
}
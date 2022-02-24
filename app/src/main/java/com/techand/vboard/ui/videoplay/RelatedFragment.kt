package com.techand.vboard.ui.videoplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.R
import com.techand.vboard.data.models.Content
import com.techand.vboard.ui.home.HomeViewModel
import com.techand.vboard.ui.home.HomeViewModelFactory
import com.techand.vboard.utils.ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class RelatedFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        showLoader()
        showData(view)
        return view
    }

    private fun setRv(view: View, list: List<Content>) {
        val updatedList=list.filter { it.video.videoId.isNotEmpty() && it.video.videoId.isNotBlank() }
        val rv = view.findViewById<View>(R.id.home_recycler) as RecyclerView
        rv.apply {
            layoutManager = LinearLayoutManager(rv.context)
            adapter = RelatedAdapter(updatedList)
        }
    }

    private fun showData(view: View) {
        viewModel.getRelated(ID).observe(this as LifecycleOwner) { playlist ->
            if (playlist.getOrNull() != null)
                setRv(view, playlist.getOrNull()!!)
        }
    }
    private fun showLoader() {
        viewModel.loader.observe(viewLifecycleOwner) {
            if (it)
                loader.visibility = View.VISIBLE
            else
                loader.visibility = View.GONE
        }
    }
}
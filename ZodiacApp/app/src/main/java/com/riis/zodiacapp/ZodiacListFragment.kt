package com.riis.zodiacapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ZodiacListFragment"

class ZodiacListFragment : Fragment() {
    private lateinit var zodiacRecyclerView: RecyclerView

    private val zodiacListViewModel: ZodiacListViewModel by lazy {
        ViewModelProviders.of(this).get(ZodiacListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "POGGIES")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_zodiac_list, container, false)
        zodiacRecyclerView = view.findViewById(R.id.zodiac_recycler_view) as RecyclerView
        zodiacRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

//    companion object {
//        fun newInstance(): ZodiacListFragment {
//            return ZodiacListFragment()
//        }
//    }


}
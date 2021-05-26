package com.riis.zodiacapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ZodiacListFragment"

class ZodiacListFragment : Fragment() {
    private lateinit var zodiacRecyclerView: RecyclerView
    private var adapter: ZodiacAdapter? = ZodiacAdapter(emptyList())

    private val zodiacListViewModel: ZodiacListViewModel by lazy {
        ViewModelProviders.of(this).get(ZodiacListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

//        updateUI()

        return view
    }

    private inner class ZodiacHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val zodiacTextView: TextView = itemView.findViewById(R.id.zodiac_item)

        private lateinit var sign: Sign

        fun bind(zodiac: Sign) {
            sign = zodiac
            zodiacTextView.text = sign.name
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${sign.name} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zodiacListViewModel.signListLiveData.observe(
            viewLifecycleOwner,
            Observer { signs ->
                signs?.let {
                    updateUI(signs)
                }
            }
        )
    }

    private fun updateUI(signs: List<Sign>) {
        adapter = ZodiacAdapter(signs)
        zodiacRecyclerView.adapter = adapter
    }

    private inner class ZodiacAdapter(var zodiac: List<Sign>) : RecyclerView.Adapter<ZodiacHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacHolder {
            val view = layoutInflater.inflate(R.layout.list_item_zodiac, parent, false)
            return ZodiacHolder(view)
        }

        override fun onBindViewHolder(holder: ZodiacHolder, position: Int) {
            val zodiacSign = zodiac[position]
            holder.bind(zodiacSign)
        }

        override fun getItemCount(): Int {
            return zodiac.size
        }
    }

    companion object {
        fun newInstance(): ZodiacListFragment {
            return ZodiacListFragment()
        }
    }
}
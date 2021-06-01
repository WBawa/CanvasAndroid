package com.riis.zodiacapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController


private const val TAG = "ZodiacListFragment"

class ZodiacListFragment : Fragment() {
    private lateinit var zodiacRecyclerView: RecyclerView
    private var adapter: ZodiacAdapter? = ZodiacAdapter(emptyList())

    private var signImages = listOf(
        R.drawable.aries, R.drawable.taurus, R.drawable.gemini,
        R.drawable.cancer, R.drawable.leo, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio,
        R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces
    )

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
        val view = inflater.inflate(R.layout.fragment_zodiac_list, container, false)
        zodiacRecyclerView = view.findViewById(R.id.zodiac_recycler_view) as RecyclerView
        zodiacRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val dividerItemDecorationVertical = DividerItemDecoration(
            zodiacRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        val dividerItemDecorationHorizontal = DividerItemDecoration(
            zodiacRecyclerView.context,
            LinearLayoutManager.HORIZONTAL
        )
        zodiacRecyclerView.addItemDecoration(dividerItemDecorationVertical)
        zodiacRecyclerView.addItemDecoration(dividerItemDecorationHorizontal)

        return view
    }

    private inner class ZodiacHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val zodiacTextView: TextView = itemView.findViewById(R.id.detail_sign_name)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        private lateinit var sign: Sign

        init {
            view.setOnClickListener {
                Toast.makeText(context, "${sign.name} pressed!", Toast.LENGTH_SHORT).show()
                val directions = ZodiacListFragmentDirections.actionListToDetailView(name = sign.name, description = sign.description, symbol = sign.symbol, month = sign.month, id = sign.id, images = signImages.toIntArray())
                findNavController().navigate(directions)
            }
        }

        fun bind(zodiac: Sign) {
            sign = zodiac
            zodiacTextView.text = sign.name
            imageView.setImageResource(signImages[sign.id - 1])
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

//        view.setOnClickListener {
//            Log.d(TAG, "WHAT'S UP HOES")
//            val directions = ZodiacListFragmentDirections.actionListToDetailView(name = "sign.name", description = "sign.description", symbol = "sign.symbol", month = "sign.month")
//            findNavController().navigate(directions)
//        }

    }

    private fun updateUI(signs: List<Sign>) {
        adapter = ZodiacAdapter(signs)
        zodiacRecyclerView.adapter = adapter
    }

    private inner class ZodiacAdapter(var zodiac: List<Sign>) :
        RecyclerView.Adapter<ZodiacHolder>() {
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
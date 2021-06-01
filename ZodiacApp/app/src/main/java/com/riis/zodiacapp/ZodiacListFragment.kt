package com.riis.zodiacapp

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception


private const val TAG = "ZodiacListFragment"

class ZodiacListFragment : Fragment() {
    private lateinit var zodiacRecyclerView: RecyclerView
    private var adapter: ZodiacAdapter? = ZodiacAdapter(emptyList())

    private var signImages = listOf(R.drawable.aries, R.drawable.taurus, R.drawable.gemini,
    R.drawable.cancer, R.drawable.leo, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio,
    R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces)

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

        val dividerItemDecoration = DividerItemDecoration(
            zodiacRecyclerView.context,
            (zodiacRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        zodiacRecyclerView.addItemDecoration(dividerItemDecoration)

        return view
    }

    private inner class ZodiacHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val zodiacTextView: TextView = itemView.findViewById(R.id.sign_name)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        private lateinit var sign: Sign

        init {
            view.setOnClickListener{
                Toast.makeText(context, "${sign.name} pressed!", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(zodiac: Sign) {
            sign = zodiac
            zodiacTextView.text = sign.name

            Log.d(TAG, sign.name)
            Log.d(TAG, "${sign.id}")
            Log.d(TAG, " ")

            try {
                imageView.setImageResource(signImages[sign.id - 1])
            } catch (e: Exception) {
                imageView.setImageResource(R.drawable.pisces)
            }

//            val tmp = context?.assets?.open("images/${sign.name.toLowerCase()}.webp")
//            val tmp : InputStream? = context?.assets?.open("images/aries.png")

//            val bitmapFactory = BitmapFactory.decodeStream(tmp)
//            imageView.setImageBitmap(bitmapFactory)

//            if (sign.name == "Aries") {
//                imageView.setImageResource(R.drawable.aries)
//            } else if (sign.name == "Taurus") {
//                imageView.setImageResource(R.drawable.taurus)
//            } else if (sign.name == "Gemini") {
//                imageView.setImageResource(R.drawable.gemini)
//            } else if (sign.name == "Cancer") {
//                imageView.setImageResource(R.drawable.cancer)
//            } else if (sign.name == "Leo") {
//                imageView.setImageResource(R.drawable.leo)
//            } else if (sign.name == "Virgo") {
//                imageView.setImageResource(R.drawable.virgo)
//            } else if (sign.name == "Libra") {
//
//            }


//           val resId = resources.getIdentifier(
//               "images/${sign.name.toLowerCase()}.webp",
//               "drawable",
//               "drawable"
//           )

//           val d : Drawable = Drawable.createFromStream(tmp, null)
//
//           imageView.setImageDrawable(d)


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
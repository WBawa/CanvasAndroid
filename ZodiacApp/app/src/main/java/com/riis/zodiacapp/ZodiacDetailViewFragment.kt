package com.riis.zodiacapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class ZodiacDetailViewFragment : Fragment() {

    private val safeArgs: ZodiacDetailViewFragmentArgs by navArgs()
    private lateinit var imageView: ImageView
    private lateinit var images: IntArray
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var symbol: TextView
    private lateinit var month: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zodiac_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        images = safeArgs.images

        imageView = view.findViewById(R.id.detailImageView)
        imageView.setImageResource(images[safeArgs.id - 1])

        name = view.findViewById(R.id.detail_sign_name)
        name.text = safeArgs.name

        description = view.findViewById(R.id.detail_description)
        description.text = "Description: ${safeArgs.description}"

        symbol = view.findViewById(R.id.detail_symbol)
        symbol.text = "Symbol: ${safeArgs.symbol}"

        month = view.findViewById(R.id.detail_month)
        month.text = "Month: ${safeArgs.month}"

    }
}
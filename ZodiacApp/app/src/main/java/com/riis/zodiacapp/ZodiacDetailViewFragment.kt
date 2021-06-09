package com.riis.zodiacapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.riis.zodiacapp.api.HoroscopeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "ZodiacDetailView"

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

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://sandipbgt.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val horoscopeApi: HoroscopeApi = retrofit.create(HoroscopeApi::class.java)

        val request: Call<String> = horoscopeApi.fetchHoroscope(safeArgs.name.lowercase())
        Log.d(TAG, safeArgs.name)

        request.enqueue((object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "failed to fetch horoscope", t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received: ${response.body()}")
            }
        }))

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
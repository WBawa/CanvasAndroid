package com.riis.criminalintent2

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"
private const val ARG_DATE = "date"

class TimePickerFragment : DialogFragment() {
    interface Callbacks {
        fun onTimeSelected(date: Date)
    }

    val time = arguments?.getSerializable(ARG_DATE) as Date

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListener = TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
            val resultTime: Date = GregorianCalendar(time.year, time.month, time.day, hourOfDay, minute).time

            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultTime)
            }
        }

        val time = arguments?.getSerializable(ARG_DATE) as Date

        return TimePickerDialog(requireContext(), timeListener, time.hours, time.minutes, false)
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(time: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, time)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}
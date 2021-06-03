package com.riis.criminalintent2

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"
private const val ARG_DATE = "date"
private const val TAG = "TimePickerFragment"

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val tmp = arguments?.getSerializable(ARG_DATE) as Date
        val timeListener = TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
//            val resultTime: Date = GregorianCalendar(time.year, time.month, time.day, hourOfDay, minute).time

            val resultTime = GregorianCalendar(tmp.year, tmp.month, tmp.day, hourOfDay, minute).time
            Log.d(TAG, "${tmp.year}")

            targetFragment?.let { fragment ->
                (fragment as DatePickerFragment.Callbacks).onDateSelected(resultTime)
            }
        }

        val time = arguments?.getSerializable(ARG_DATE) as Date

        return TimePickerDialog(requireContext(), timeListener, time.hours, time.minutes, false)
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, time)
            }

            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}
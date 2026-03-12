package com.example.adviceapi

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView

class AdviceSlipAdapter (var adviceSlipList: List<Slip>) :
    RecyclerView.Adapter<AdviceSlipAdapter.ViewHolder>() {
//    companion object {
//        val EXTRA_ADVICE = "advice"
//    }

    private var adviceSlipCount : Int = 0
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewQuote: TextView
        val textViewId: TextView
        val layout: ConstraintLayout

        init {
            textViewQuote = view.findViewById(R.id.textView_adviceSlipItem_quote)
            textViewId = view.findViewById(R.id.textView_adviceSlipItem_id)
            layout = view.findViewById(R.id.layout_adviceSlipItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.advice_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val adviceSlip = adviceSlipList[position]
        Log.d("adapter", "onBindViewHolder: $adviceSlip")
        viewHolder.textViewQuote.text = adviceSlip.advice
        viewHolder.textViewId.text = "#" + adviceSlip.id.toString()
    }

    override fun getItemCount() = adviceSlipList.size
}

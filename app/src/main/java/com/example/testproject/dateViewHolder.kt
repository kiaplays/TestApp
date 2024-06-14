package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView


class DateAdapter(val dateList: List<Date>) :
    RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        //here we indicate that this list should show list_item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.date_item, parent, false)
        return DateViewHolder(view)
    }



    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dateList[position], position)
    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val backgroundCardView = itemView.findViewById<CardView>(R.id.card_view)
        private val dateTextView = itemView.findViewById<MaterialTextView>(R.id.text_view_date)
        private val dayTextView = itemView.findViewById<MaterialTextView>(R.id.text_view_day)
        fun bind(date: Date, position: Int) {
            dateTextView.text = date.date.toString()
            dayTextView.text = date.day
            if(date.isSelected){
                backgroundCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.purple))
                dateTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                dayTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }else{
                backgroundCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.white))
                dateTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                dayTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))

            }
            itemView.setOnClickListener {
                val allUnselected = dateList.map {
                    it.isSelected = false
                    it
                }
                allUnselected[position].isSelected = true
                notifyDataSetChanged()
            }
        }

    }
}


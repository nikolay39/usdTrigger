package com.example.android.usdTrigger.view

import androidx.recyclerview.widget.DiffUtil
import com.example.android.usdTrigger.repository.database.QuoteDB

class QuoteDiffCallback(
    private val oldList: List<QuoteDB>,
    private val newList: List<QuoteDB>) : DiffUtil.Callback()

{
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return ((oldList[oldItemPosition].date == newList[newItemPosition].date) &&
                (oldList[oldItemPosition].value == newList[newItemPosition].value) )
    }

}
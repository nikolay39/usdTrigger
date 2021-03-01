/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.usdTrigger.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.usdTrigger.databinding.QuoteListItemBinding
import com.example.android.usdTrigger.repository.database.QuoteDB
import java.text.SimpleDateFormat
import java.util.*

class QuotesLiniearAdapter() :
        ListAdapter<QuoteDB, QuotesLiniearAdapter.QuotViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotViewHolder {
        val binding = QuoteListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QuotViewHolder(val binding: QuoteListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: QuoteDB) {
            val date = Date(quote.date)
            val format = SimpleDateFormat("yyyy.MM.dd")
            binding.date.text = format.format(date)
            binding.value.text = quote.value.toString();
        }
    }
}
class DiffCallback : DiffUtil.ItemCallback<QuoteDB>() {
    override fun areItemsTheSame(oldItem: QuoteDB, newItem: QuoteDB):Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: QuoteDB, newItem: QuoteDB):Boolean  =
            (oldItem.date == newItem.date) && (oldItem.value==newItem.value)

}

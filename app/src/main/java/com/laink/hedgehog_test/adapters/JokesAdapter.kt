package com.laink.hedgehog_test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laink.hedgehog_test.R
import com.laink.hedgehog_test.models.Value
import kotlinx.android.synthetic.main.joke_item.view.*

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Value>() {
        override fun areItemsTheSame(oldItem: Value, newItem: Value): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Value, newItem: Value): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.joke_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val Value = differ.currentList[position]

        holder.itemView.joke_text.text = Value.joke
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
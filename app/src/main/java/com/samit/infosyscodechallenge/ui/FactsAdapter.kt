package com.samit.infosyscodechallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samit.infosyscodechallenge.databinding.ItemFactsBinding
import com.samit.infosyscodechallenge.ui.model.FactUI

class FactsAdapter : ListAdapter<FactUI, FactsAdapter.FactsViewHolder>(
    FactsDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        return FactsViewHolder(
            ItemFactsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class FactsViewHolder(
        private val binding: ItemFactsBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(facts: FactUI) {
            binding.facts = facts

            if (facts.image.isNullOrEmpty()) {
                binding.ivFact.visibility = View.GONE
            } else {
                binding.ivFact.visibility = View.VISIBLE
            }
        }
    }
}

class FactsDiffCallback : DiffUtil.ItemCallback<FactUI>() {
    override fun areItemsTheSame(oldItem: FactUI, newItem: FactUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FactUI, newItem: FactUI): Boolean {
        return oldItem == newItem
    }
}
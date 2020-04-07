package com.samit.infosyscodechallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samit.infosyscodechallenge.databinding.ItemFactsBinding
import com.samit.infosyscodechallenge.ui.model.FactUI
import timber.log.Timber

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

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    class FactsViewHolder(
        private val binding: ItemFactsBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(facts: FactUI) {
            binding.facts = facts
            if (facts.title.isNullOrEmpty() &&
                facts.description.isNullOrEmpty() && facts.image.isNullOrEmpty()
            ) {
                Timber.d("This is called")
                //binding.contraintContainer.visibility = View.GONE
                //binding.contraintContainer.layoutParams = RecyclerView.LayoutParams(0, 0)
            } else {
                //binding.contraintContainer.visibility = View.VISIBLE
//                binding.contraintContainer.layoutParams =
//                    RecyclerView.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                    )
            }
            if (facts.image.isNullOrEmpty()) {
                binding.ivFact.visibility = View.GONE
                Timber.d("Image null for ${facts.title}")
            } else {
                binding.ivFact.visibility = View.VISIBLE
            }
            if (facts.title.isNullOrEmpty()) {
                //binding.tvTitle.visibility = View.GONE
            }
            if (facts.description.isNullOrEmpty()) {
                //binding.tvDesc.visibility = View.GONE
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
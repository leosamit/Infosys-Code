package com.samit.infosyscodechallenge.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.samit.infosyscodechallenge.R
import com.samit.infosyscodechallenge.ui.FactsAdapter
import com.samit.infosyscodechallenge.ui.model.FactUI
import timber.log.Timber

@BindingAdapter(value = ["app:factsList"])
fun setFactsList(rv: RecyclerView, items: List<FactUI>?) {

    if (rv.adapter as? FactsAdapter == null) {
        rv.adapter = FactsAdapter()
    }
    (rv.adapter as FactsAdapter).submitList(items)
}


@BindingAdapter("setImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Timber.d("THis glide is called")
    var imageError = false
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions())
//        .error(Glide.with(view).load(R.drawable.placeholder))
        .placeholder(R.drawable.placeholder)
        .into(view)
}
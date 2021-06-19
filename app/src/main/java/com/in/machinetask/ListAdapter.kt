package com.`in`.machinetask

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.`in`.machinetask.databinding.ListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*

class ListAdapter(
    var arrayList: ArrayList<ListViewModel>,
    var context: Context
) :
    RecyclerView.Adapter<ListAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val categoryBinding: ListBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_image, parent, false)
        return MyHolder(categoryBinding)
    }

    override fun onBindViewHolder(myHolder: MyHolder, i: Int) {
        val sampleViewModel = arrayList[i]
        myHolder.bind(sampleViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class MyHolder(sampleBinding: ListBinding) :
        RecyclerView.ViewHolder(sampleBinding.getRoot()) {
        var sampleBinding: ListBinding
        fun bind(sampleViewModel: ListViewModel) {
            sampleBinding.setSamplemodel(sampleViewModel)
            sampleBinding.executePendingBindings()
    /*        val layoutParams: ViewGroup.LayoutParams = sampleBinding.image.getLayoutParams()
            var width=sampleViewModel.width!!
            var height=sampleViewModel.height!!
            layoutParams.width = width
            layoutParams.height = height
            sampleBinding.image.setLayoutParams(layoutParams)*/
            Glide.with(context)
                .load(sampleViewModel.download_url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sampleBinding.image)
        }

        val categoryBinding: ListBinding
            get() = sampleBinding

        init {
            this.sampleBinding = sampleBinding
        }
    }

    fun pxToDp(px: Int): Float {
        return (px / Resources.getSystem().getDisplayMetrics().density) as Float
    }
}


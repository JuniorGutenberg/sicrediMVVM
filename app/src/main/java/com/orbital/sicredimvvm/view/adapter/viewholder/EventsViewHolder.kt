package com.orbital.sicredimvvm.view.adapter.viewholder

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.orbital.core.enums.ExtrasEnum
import com.orbital.sicredimvvm.R
import com.orbital.sicredimvvm.databinding.ItemCardsEventsBinding
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.view.activity.DetailsEventsActivity

class EventsViewHolder (private val binding: ItemCardsEventsBinding, var context: Context):
    RecyclerView.ViewHolder(binding.root){

    fun bind(eventsDTO: EventsData){
        binding.tv.text = eventsDTO.title


        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

        Glide.
        with(context)
            .load(eventsDTO.image)
            .apply(requestOptions)
            .error(R.drawable.ic_img_not_found)
            .into(binding.iv)

        itemView.setOnClickListener {
            val intent = Intent(context, DetailsEventsActivity::class.java)
            intent.putExtra(ExtrasEnum.EVENT.value, eventsDTO)

            context.startActivity(intent)
        }
    }
}
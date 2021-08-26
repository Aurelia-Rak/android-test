package com.evaneos.evaneostest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.databinding.MainActivityListDestinationBinding
import com.evaneos.evaneostest.ui.viewmodel.ItemDestinationViewModel
import com.squareup.picasso.Picasso

class DestinationDataAdapter(
    mContext: Context,
    destinationsList: List<Destination>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private val contexte: Context = mContext
    private val destinationList: List<Destination> = destinationsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mainActivityListDestinationBinding: MainActivityListDestinationBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(contexte),
                R.layout.main_activity_list_destination,
                parent,
                false
            )
        return ViewHolder(mainActivityListDestinationBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)
        holder.bindDestination(destinationList.get(position))
    }

    override fun getItemCount(): Int = destinationList.size

    class ViewHolder(binding: MainActivityListDestinationBinding) :
        RecyclerView.ViewHolder(binding.itemDestination) {
        private var binding: MainActivityListDestinationBinding = binding
        fun bindDestination(destination: Destination) {
            binding.destination = destination
            binding.viewmodel = ItemDestinationViewModel(itemView.context, destination)
            Picasso.get().load(destination.pictureUrl).placeholder(R.drawable.default_image)
                .into(binding.itemImage)
        }
    }
}
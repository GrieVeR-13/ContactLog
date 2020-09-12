package net.grieverc.contactlog.features.presentation.specialty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.grieverc.contactlog.databinding.SpecialtyRowBinding
import net.grieverc.contactlog.features.presentation.view.SpecialtyView

class SpecialtyListAdapter(
    private val inflater: LayoutInflater,
    private val onRowClick: (SpecialtyView) -> Unit
) : ListAdapter<SpecialtyView, SpecialtyRowHolder>(
    SpecialtyListAdapterDiff
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyRowHolder {
        return SpecialtyRowHolder(
            SpecialtyRowBinding.inflate(
                inflater,
                parent,
                false
            )
            ,
            onRowClick
        )
    }

    override fun onBindViewHolder(holder: SpecialtyRowHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private object SpecialtyListAdapterDiff : DiffUtil.ItemCallback<SpecialtyView>() {
    override fun areItemsTheSame(oldItem: SpecialtyView, newItem: SpecialtyView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SpecialtyView, newItem: SpecialtyView): Boolean {
        return oldItem.name == newItem.name && oldItem.description == newItem.description
    }
}
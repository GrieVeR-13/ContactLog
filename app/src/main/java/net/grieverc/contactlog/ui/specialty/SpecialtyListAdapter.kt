package net.grieverc.contactlog.ui.specialty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.grieverc.contactlog.databinding.SpecialtyRowBinding
import net.grieverc.contactlog.repo.SpecialtyModel

class SpecialtyListAdapter(
    private val inflater: LayoutInflater,
    private val onRowClick: (SpecialtyModel) -> Unit
) : ListAdapter<SpecialtyModel, SpecialtyRowHolder>(
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

private object SpecialtyListAdapterDiff : DiffUtil.ItemCallback<SpecialtyModel>() {
    override fun areItemsTheSame(oldItem: SpecialtyModel, newItem: SpecialtyModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SpecialtyModel, newItem: SpecialtyModel): Boolean {
        return oldItem.description == newItem.description
    }
}
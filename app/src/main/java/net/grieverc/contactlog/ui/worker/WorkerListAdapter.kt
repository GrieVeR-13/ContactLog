package net.grieverc.contactlog.ui.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.grieverc.contactlog.databinding.WorkerkRowBinding
import net.grieverc.contactlog.repo.specialty.SpecialtyModel

class WorkerListAdapter(
    val inflater: LayoutInflater
) : ListAdapter<SpecialtyModel, WorkerRowHolder>(
    SpecialtyListAdapterDiff
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerRowHolder {
        return WorkerRowHolder(
            WorkerkRowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: WorkerRowHolder, position: Int) {
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
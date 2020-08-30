package net.grieverc.contactlog.ui.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.grieverc.contactlog.databinding.WorkerRowBinding
import net.grieverc.contactlog.repo.WorkerModel

class WorkerListAdapter(
    private val inflater: LayoutInflater,
    private val onRowClick: (WorkerModel) -> Unit
) : ListAdapter<WorkerModel, WorkerRowHolder>(
    SpecialtyListAdapterDiff
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerRowHolder {
        return WorkerRowHolder(
            WorkerRowBinding.inflate(inflater, parent, false),
            onRowClick
        )
    }

    override fun onBindViewHolder(holder: WorkerRowHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private object SpecialtyListAdapterDiff : DiffUtil.ItemCallback<WorkerModel>() {
    override fun areItemsTheSame(oldItem: WorkerModel, newItem: WorkerModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkerModel, newItem: WorkerModel): Boolean {
        return oldItem.firstName == newItem.firstName &&
                oldItem.surname == oldItem.surname &&
                oldItem.birthDate == oldItem.birthDate
    }
}
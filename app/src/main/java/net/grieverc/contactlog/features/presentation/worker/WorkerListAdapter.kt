package net.grieverc.contactlog.features.presentation.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.grieverc.contactlog.databinding.WorkerRowBinding
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.presentation.view.WorkerView

class WorkerListAdapter(
    private val inflater: LayoutInflater,
    private val onRowClick: (WorkerView) -> Unit
) : ListAdapter<WorkerView, WorkerRowHolder>(
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

private object SpecialtyListAdapterDiff : DiffUtil.ItemCallback<WorkerView>() {
    override fun areItemsTheSame(oldItem: WorkerView, newItem: WorkerView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkerView, newItem: WorkerView): Boolean {
        return oldItem.firstName == newItem.firstName &&
                oldItem.surname == oldItem.surname &&
                oldItem.birthDate == oldItem.birthDate
    }
}
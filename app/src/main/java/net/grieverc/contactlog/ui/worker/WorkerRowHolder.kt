package net.grieverc.contactlog.ui.worker

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.WorkerRowBinding
import net.grieverc.contactlog.repo.WorkerModel

class WorkerRowHolder(
    private val binding: WorkerRowBinding,
    private val onRowClick: (WorkerModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(model: WorkerModel) {
        binding.apply {
            workerDetails.text = String.format(
                binding.root.context.getString(R.string.worker_details),
                model.firstName,
                model.surname,
                model.age
            )
        }
        binding.root.setOnClickListener { onRowClick(model) }
    }
}
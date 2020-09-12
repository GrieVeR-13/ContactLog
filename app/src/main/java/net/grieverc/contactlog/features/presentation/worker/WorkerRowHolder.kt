package net.grieverc.contactlog.features.presentation.worker

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.WorkerRowBinding
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.core.service.DisplayFormatter
import net.grieverc.contactlog.features.presentation.view.WorkerView

class WorkerRowHolder(
    private val binding: WorkerRowBinding,
    private val onRowClick: (WorkerView) -> Unit
) : ViewHolder(binding.root) {

    fun bind(model: WorkerView) {
        binding.apply {
            workerFullName.text = String.format(
                binding.root.context.getString(R.string.worker_full_name),
                DisplayFormatter.formatName(model.firstName),
                DisplayFormatter.formatName(model.surname)
            )

            workerAge.text = model.age?.let {
                String.format(
                    binding.root.context.getString(R.string.worker_age),
                    it
                )
            } ?: ""
        }
        binding.root.setOnClickListener { onRowClick(model) }
    }
}
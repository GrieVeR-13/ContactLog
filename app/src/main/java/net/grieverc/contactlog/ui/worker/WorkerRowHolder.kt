package net.grieverc.contactlog.ui.worker

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.databinding.WorkerkRowBinding
import net.grieverc.contactlog.repo.specialty.SpecialtyModel

class WorkerRowHolder(
    val binding: WorkerkRowBinding
) : ViewHolder(binding.root) {

    fun bind(model: SpecialtyModel) {
        binding.apply {
            workerFirstName.text = model.name
            workerFirstName.text = model.description
        }
    }
}
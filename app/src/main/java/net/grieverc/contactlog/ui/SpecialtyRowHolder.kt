package net.grieverc.contactlog.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.databinding.SpecialtyRowBinding
import net.grieverc.contactlog.repo.SpecialtyModel

class SpecialtyRowHolder(
    val binding: SpecialtyRowBinding
) : ViewHolder(binding.root) {

    fun bind(model: SpecialtyModel) {
        binding.apply {
            specialtyName.text = model.name
            specialtyDescription.text = model.description
        }
    }
}
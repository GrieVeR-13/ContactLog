package net.grieverc.contactlog.ui.specialty

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.databinding.SpecialtyRowBinding
import net.grieverc.contactlog.repo.SpecialtyModel

class SpecialtyRowHolder(
    val binding: SpecialtyRowBinding,
    val onRowClick: (SpecialtyModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(model: SpecialtyModel) {
        binding.apply {
            root.setOnClickListener{ onRowClick(model) }
            specialtyName.text = model.name
            specialtyDescription.text = model.description
        }
    }
}
package net.grieverc.contactlog.features.presentation.specialty

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import net.grieverc.contactlog.databinding.SpecialtyRowBinding
import net.grieverc.contactlog.features.presentation.view.SpecialtyView

class SpecialtyRowHolder(
    private val binding: SpecialtyRowBinding,
    val onRowClick: (SpecialtyView) -> Unit
) : ViewHolder(binding.root) {

    fun bind(model: SpecialtyView) {
        binding.apply {
            root.setOnClickListener{ onRowClick(model) }
            specialtyName.text = model.name
            specialtyDescription.text = model.description
        }
    }
}
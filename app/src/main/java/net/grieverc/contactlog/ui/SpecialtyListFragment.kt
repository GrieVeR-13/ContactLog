package net.grieverc.contactlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.grieverc.contactlog.databinding.SpecialtyListBinding

class SpecialtyListFragment : Fragment() {
    lateinit var binding: SpecialtyListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = SpecialtyListBinding.inflate(inflater, container, false).apply {
        binding = this
    }
        .root
}
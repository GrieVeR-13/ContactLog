package net.grieverc.contactlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.grieverc.contactlog.databinding.SpecialtyRosterBinding
import net.grieverc.contactlog.repo.ContactLogRepository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialtyRosterFragment : Fragment() {
    lateinit var binding: SpecialtyRosterBinding
    private val engine: ContactLogEngine by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = SpecialtyRosterBinding.inflate(inflater, container, false).apply {
        binding = this
    }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SpecialtyListAdapter(layoutInflater)
        adapter.submitList(engine.repository.specialyList)
        binding.specialtyList.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        binding.testButton.setOnClickListener {
            engine.repository.insert()
            engine.repository.load()

        }
    }
}
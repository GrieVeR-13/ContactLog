package net.grieverc.contactlog.ui.worker

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.WorkerRosterBinding
import net.grieverc.contactlog.repo.specialty.SpecialtyModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkerRosterFragment : Fragment() {
    lateinit var binding: WorkerRosterBinding
    private val vm: WorkerRosterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = WorkerRosterBinding.inflate(inflater, container, false).apply {
        binding = this
    }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WorkerListAdapter(layoutInflater)
        vm.specialtyLiveData.observe(viewLifecycleOwner) { specialtyModelList: List<SpecialtyModel> ->
            adapter.submitList(specialtyModelList)
        }

        binding.workerList.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.actions_specialty_roster, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insert_sample_data -> {
                vm.insertSampleData()
                return true
            }
            R.id.delete_all -> {
                vm.clearAll()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
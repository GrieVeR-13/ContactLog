package net.grieverc.contactlog.ui.worker

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.grieverc.contactlog.databinding.WorkerRosterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WorkerRosterFragment : Fragment() {
    private val args: WorkerRosterFragmentArgs by navArgs()

    private lateinit var binding: WorkerRosterBinding
    private val vm: WorkerRosterViewModel by viewModel {
        parametersOf(args.specialtyId)
    }

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
        val adapter = WorkerListAdapter(layoutInflater) {
            findNavController().navigate(WorkerRosterFragmentDirections.actionWorkerRosterFragmentToWorkerDetailsFragment(it.id))
        }
        vm.workerListLiveData.observe(viewLifecycleOwner) {
            binding.specialtyName.text = it.firstOrNull()?.specialty?.name
            adapter.submitList(it)
        }

        binding.workerList.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }
}
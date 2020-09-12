package net.grieverc.contactlog.features.presentation.worker.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.WorkerDetailsBinding
import net.grieverc.contactlog.features.repo.room.DateTypeConverter
import net.grieverc.contactlog.core.service.DisplayFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WorkerDetailsFragment : Fragment() {
    private val args: WorkerDetailsFragmentArgs by navArgs()

    private lateinit var binding: WorkerDetailsBinding
    private val vm: WorkerDetailsViewModel by viewModel {
        parametersOf(args.workerId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = WorkerDetailsBinding.inflate(inflater, container, false).apply {
        binding = this
    }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.workerLiveData.observe(viewLifecycleOwner) {
            binding.workerDetails.text = if (it != null) {
                String.format(
                    getString(R.string.worker_details_full),
                    DisplayFormatter.formatName(it.firstName),
                    DisplayFormatter.formatName(it.surname),
                    DateTypeConverter.fromLocalDate(it.birthDate) ?: "_",
                    it.age ?: "_",
                    it.specialtyName ?: ""
                )
            } else ""
        }
    }
}
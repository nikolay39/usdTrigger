package com.example.android.usdTrigger.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.usdTrigger.databinding.FragmentOverviewBinding
import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.viewmodel.OverviewViewModel
import com.example.android.usdTrigger.view.adapter.QuotesLiniearAdapter
import timber.log.Timber
import javax.inject.Inject


class OverviewFragment : Fragment()  {
    private lateinit var quotesObserver: Observer<List<QuoteDB>>;
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<OverviewViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        Timber.i("fragment attach")
        super.onAttach(context)
        (activity as MainActivity).viewComponent.inject(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val view = binding.root;
        viewModel.init();
        val adapter = QuotesLiniearAdapter()
        binding.recyclerview.adapter = adapter


        quotesObserver = Observer<List<QuoteDB>> { quotes ->
            quotes?.let {updatedQoutes->
                Timber.i("add new data in adapter")
                //binding.recyclerview.adapter = QuotesLiniearAdapter(updatedQoutes)
                Timber.i("$updatedQoutes")
                adapter.submitList(updatedQoutes)
            }
        }
        viewModel.quotes.observe(viewLifecycleOwner, quotesObserver);
        Timber.i("onCreateViewFragment")
        return view
    }
}

package com.example.android.usdTrigger.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.usdTrigger.databinding.FragmentOverviewBinding
import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.viewmodel.OverviewViewModel
import com.example.android.usdTrigger.view.adapter.QuotesLiniearAdapter
import timber.log.Timber
import javax.inject.Inject


const val TAG: String = "OVERVIEW"
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
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val view = binding.root;
        viewModel.init();
        quotesObserver = Observer<List<QuoteDB>> { quotes ->
            quotes?.let {qoutesList->
                binding.recyclerview.adapter = QuotesLiniearAdapter(qoutesList)
            }
        }

        Timber.i("onCreateView create timber")
        return view
    }
    override fun onStart() {
        super.onStart()
        viewModel.quotes.observe(this, quotesObserver);
    }
    override fun onStop() {
        super.onStop()
        viewModel.quotes.removeObserver(quotesObserver);
    }
}

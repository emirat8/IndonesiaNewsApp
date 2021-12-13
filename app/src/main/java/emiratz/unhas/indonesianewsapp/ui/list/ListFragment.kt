package emiratz.unhas.indonesianewsapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import emiratz.unhas.indonesianewsapp.adapter.ListAdapter
import emiratz.unhas.indonesianewsapp.databinding.FragmentListBinding
import emiratz.unhas.indonesianewsapp.model.News
import emiratz.unhas.indonesianewsapp.utils.DataMapper

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var newsAdapter: ListAdapter
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this)[ListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            newsAdapter = ListAdapter()
            setupRecyclerView()
            setNews()
            onItemSelected()

            checkApiResponse()

        }
    }

    private fun setupRecyclerView() {
        with(binding.rvList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun setNews() {
        viewModel.getLatestNews().observe(viewLifecycleOwner, {
            val newsList = DataMapper.mapResponseToModel(it)
            newsAdapter.setData(newsList as ArrayList<News>)
            checkApiResponse()
        })
    }

    private fun onItemSelected() {
        newsAdapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
            override fun onNewsClicked(news: News) {
                val actionToDetail =
                    ListFragmentDirections.actionListFragmentToDetailFragment2(news)
                findNavController().navigate(actionToDetail)
            }
        })
    }

    private fun checkApiResponse() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            showProgressBar(it)
        })
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE
        else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
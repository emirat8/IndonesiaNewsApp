package emiratz.unhas.indonesianewsapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import emiratz.unhas.indonesianewsapp.R
import emiratz.unhas.indonesianewsapp.databinding.FragmentDetailBinding
import emiratz.unhas.indonesianewsapp.model.News
import emiratz.unhas.indonesianewsapp.utils.DataMapper

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val data = args.news
            populateNews(data)

            with(binding) {
                btnBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                btnShare.setOnClickListener {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT,"${data.title} \n ${data.url}")
                    startActivity(shareIntent)
                }
            }
        }
    }

    private fun populateNews(dataNews: News) {
        with(binding) {
            tvNewsDetail.text = dataNews.title
            tvDateTimeLoc.text = DataMapper.newsDateFormatter(dataNews.date)
            tvDescDetail.text = dataNews.desc
            btnNews.setOnClickListener {
                val urlIntent = Intent(Intent.ACTION_VIEW)
                urlIntent.data = Uri.parse(dataNews.url)
                startActivity(urlIntent)
            }

            Glide.with(requireActivity())
                .load(dataNews.image)
                .placeholder(R.drawable.image_load)
                .into(imgNews)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
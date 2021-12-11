package emiratz.unhas.indonesianewsapp.model

import com.google.gson.annotations.SerializedName
import emiratz.unhas.indonesianewsapp.data.response.ArticlesItem

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,

    @field:SerializedName("status")
    val status: String
)

data class Source(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Any
)
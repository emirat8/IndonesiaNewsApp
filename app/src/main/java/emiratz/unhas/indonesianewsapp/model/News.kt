package emiratz.unhas.indonesianewsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val title: String,
    val date: String,
    val image: String?,
    val desc: String?,
    val url: String?
) : Parcelable
package com.jh.holaflytest.data.api.model.comics

import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.domain.model.comics.Thumbnail
import kotlinx.serialization.Serializable
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Serializable
data class ComicDTO(
    var id: String? = null,
    var digitalId: String? = null,
    var title: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var resourceURI: String? = null,
    var thumbnail: ThumbnailDTO? = ThumbnailDTO(),
) {
    fun toDomainComic(): Comic {
        return Comic(
            id = id,
            digitalId = digitalId,
            title = title,
            variantDescription = variantDescription,
            description = description,
            modified = modified,
            resourceURI = resourceURI,
            thumbnail = thumbnail?.toDomainThumbnail(),
        )
    }
}

@Serializable
data class ThumbnailDTO(
    var path: String? = "",
    var extension: String? = ""
) {
    fun toDomainThumbnail(): Thumbnail {
        val httpsPath = if (path!!.startsWith("https")) path
        else path!!.replaceFirst("http", "https")

        return Thumbnail(
            url = "$httpsPath.$extension".toHttpUrl()
        )
    }
}

@Serializable
data class Items(
    var resourceURI: String? = null,
    var name: String? = null,
    var type: String? = null
)

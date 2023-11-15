package com.jh.holaflytest.data.api.model.comicDetail

import com.jh.holaflytest.data.api.model.comics.ThumbnailDTO
import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import kotlinx.serialization.Serializable

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Serializable
data class ComicDetailDTO(
    var id: String? = null,
    var digitalId: String? = null,
    var title: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var resourceURI: String? = null,
    var thumbnail: ThumbnailDTO? = ThumbnailDTO()
) {
    fun toDomainComicDetail(): ComicDetail {
        return ComicDetail(
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

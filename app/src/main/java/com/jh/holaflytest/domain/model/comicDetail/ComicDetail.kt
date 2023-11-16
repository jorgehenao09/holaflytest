package com.jh.holaflytest.domain.model.comicDetail

import com.jh.holaflytest.domain.model.comics.Thumbnail

/**
 * Created by Jorge Henao on 15/11/23.
 */
data class ComicDetail(
    var id: String? = "",
    var digitalId: String? = null,
    var title: String? = null,
    var issueNumber: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var resourceURI: String? = null,
    var thumbnail: Thumbnail? = Thumbnail()
)

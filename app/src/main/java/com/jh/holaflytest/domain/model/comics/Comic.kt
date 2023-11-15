package com.jh.holaflytest.domain.model.comics

import okhttp3.HttpUrl

/**
 * Created by Jorge Henao on 14/11/23.
 */

data class Comic(
    var id: String? = null,
    var digitalId: String? = null,
    var title: String? = null,
    var issueNumber: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var resourceURI: String? = null,
    var thumbnail: Thumbnail? = Thumbnail()
)

data class Thumbnail(
    var url: HttpUrl? = null
)

data class Item(
    var resourceURI: String? = null,
    var name: String? = null,
    var type: String? = null
)

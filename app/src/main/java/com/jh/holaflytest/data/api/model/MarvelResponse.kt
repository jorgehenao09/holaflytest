package com.jh.holaflytest.data.api.model

import kotlinx.serialization.Serializable

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Serializable
data class Envelope<A>(val code: Int, val attributionText: String, val data: A)

@Serializable
data class Paginated<A>(val results: List<A>)

typealias MarvelResponse<A> = Envelope<Paginated<A>>

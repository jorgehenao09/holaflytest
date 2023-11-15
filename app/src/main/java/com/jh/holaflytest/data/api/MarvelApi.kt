package com.jh.holaflytest.data.api

import com.jh.holaflytest.data.api.model.MarvelResponse
import com.jh.holaflytest.data.api.model.comics.ComicDTO
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Jorge Henao on 14/11/23.
 */
interface MarvelApi {

    @GET(COMICS_ENDPOINT)
    suspend fun getSuperHeroComics(@Path("characterId") characterId: Long): MarvelResponse<ComicDTO>
}

private const val COMICS_ENDPOINT = "characters/{characterId}/comics?limit=30"

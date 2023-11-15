package com.jh.holaflytest.data.utils

import com.jh.holaflytest.R
import com.jh.holaflytest.data.localStorage.model.MenuOptionDTO
import com.jh.holaflytest.data.localStorage.model.MenuOptionsDTO

/**
 * Created by Jorge Henao on 13/11/23.
 */
fun getMenuOptionsResponse(): MenuOptionsDTO {
    return MenuOptionsDTO(
        data = listOf(
            MenuOptionDTO(
                id = 123,
                image = R.drawable.ic_iron_man,
                name = "Iron Man"
            ),
            MenuOptionDTO(
                id = 123,
                image = R.drawable.ic_thor,
                name = "Thor"
            ),
            MenuOptionDTO(
                id = 123,
                image = R.drawable.ic_hulk,
                name = "Hulk"
            ),
            MenuOptionDTO(
                id = 123,
                image = R.drawable.ic_capitan_america,
                name = "Capitán América"
            )
        )
    )
}

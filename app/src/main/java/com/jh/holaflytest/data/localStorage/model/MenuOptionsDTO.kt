package com.jh.holaflytest.data.localStorage.model

import com.jh.holaflytest.domain.model.MenuOption
import com.jh.holaflytest.domain.model.MenuOptions

/**
 * Created by Jorge Henao on 12/11/23.
 */
data class MenuOptionsDTO(
    val data: List<MenuOptionDTO>
) {
    fun toDomainMenuOptions(): MenuOptions{
        return MenuOptions(
            options = data.map {
                MenuOption(
                    image = it.image,
                    name = it.name
                )
            }
        )
    }
}

package com.jh.holaflytest.data.localStorage.localFile

import com.jh.holaflytest.R
import com.jh.holaflytest.data.localStorage.model.MenuOptionDTO
import com.jh.holaflytest.data.localStorage.model.MenuOptionsDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Created by Jorge Henao on 12/11/23.
 */
class MenuFileManager {

    suspend fun getMenuOptions(): MenuOptionsDTO = coroutineScope {
        val menuOptions = async {
            MenuOptionsDTO(
                data = listOf(
                    MenuOptionDTO(
                        image = R.drawable.ic_iron_man,
                        name = "Iron Man"
                    ),
                    MenuOptionDTO(
                        image = R.drawable.ic_thor,
                        name = "Thor"
                    ),
                    MenuOptionDTO(
                        image = R.drawable.ic_hulk,
                        name = "Hulk"
                    ),
                    MenuOptionDTO(
                        image = R.drawable.ic_capitan_america,
                        name = "Capitán América"
                    )
                )
            )
        }
        menuOptions.await()
    }
}

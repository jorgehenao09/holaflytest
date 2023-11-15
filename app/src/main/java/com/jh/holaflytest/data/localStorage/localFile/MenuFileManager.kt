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
                        id = 1009368,
                        image = R.drawable.ic_iron_man,
                        name = "Iron Man"
                    ),
                    MenuOptionDTO(
                        id = 1009664,
                        image = R.drawable.ic_thor,
                        name = "Thor"
                    ),
                    MenuOptionDTO(
                        id = 1009351,
                        image = R.drawable.ic_hulk,
                        name = "Hulk"
                    ),
                    MenuOptionDTO(
                        id = 1009220,
                        image = R.drawable.ic_capitan_america,
                        name = "Capitán América"
                    )
                )
            )
        }
        menuOptions.await()
    }
}

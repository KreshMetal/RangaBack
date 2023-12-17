package com.Ranga.App.Entity

import org.example.common.Role
import org.example.common.User

object Constants {
    val EMPTY_COMICS = Comics(
        id = -1,
        name = "",
        desc = "",
        url = "",
        author = "",
    )

    val EMPTY_USER = User(
        login = "",
        pass = "",
        email = "",
        name = "",
        role = Role.CLIENT
    )
}
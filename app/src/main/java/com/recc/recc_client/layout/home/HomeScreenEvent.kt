package com.recc.recc_client.layout.home

sealed class HomeScreenEvent {
    object onLoggedOut: HomeScreenEvent()
}
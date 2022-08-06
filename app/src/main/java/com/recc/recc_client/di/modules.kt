package com.recc.recc_client.di

import com.recc.recc_client.R
import com.recc.recc_client.http.ServerRoutesDefinitions
import com.recc.recc_client.layout.auth.LoginViewModel
import com.recc.recc_client.layout.auth.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*** Koin module for Data injection, it creates both singletons and factories that can be used in the
 * entirety of the application ***/
val screenViewModels = module {
    viewModel {
        LoginViewModel()
    }
    viewModel {
        RegisterViewModel()
    }
}

val httpModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.api_base_endpoint))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ServerRoutesDefinitions::class.java)
    }
}
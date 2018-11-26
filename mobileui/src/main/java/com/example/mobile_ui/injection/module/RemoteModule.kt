package com.example.mobile_ui.injection.module

import com.example.data.repository.ProjectsRemote
import com.example.remote.ProjectsRemoteImpl
import com.example.remote.service.GithubTrendingService
import com.example.remote.service.GthubTrendingServiceFactory
import com.spitch.mobileui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GthubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}
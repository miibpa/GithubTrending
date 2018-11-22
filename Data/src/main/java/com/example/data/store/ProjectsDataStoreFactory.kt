package com.example.data.store

import com.example.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    open fun getDataStore(projectdCached: Boolean,
                          cacheExpired: Boolean) : ProjectsDataStore{
        return if(projectdCached && !cacheExpired){
            projectsCacheDataStore
        }else {
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore() : ProjectsDataStore{
        return projectsCacheDataStore
    }
}
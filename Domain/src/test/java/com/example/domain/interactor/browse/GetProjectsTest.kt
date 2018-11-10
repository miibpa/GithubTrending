package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import com.example.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {
    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository,postExecutionThread)
    }

    @Test
    fun getProjectsCompletes(){
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData(){
        val projectList = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projectList))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projectList)
    }

    private fun stubGetProjects(observable : Observable<List<Project>>){
        whenever(projectsRepository.getProjects()).thenReturn(observable)
    }
}
package com.manuflowers.photoinspiration.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import com.manuflowers.photoinspiration.data.models.Failure
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.models.Success
import com.manuflowers.photoinspiration.getOrAwaitValue
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosFailure
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val photoEntities = mutableListOf(
        PhotoEntity(
            id = "1",
            createdAt = "27.07.20",
            altDescription = "description",
            smallUrl = "small url",
            regularUrl = "regular url",
            userName = "Manuela",
            userProfileImage = "",
            userBio = "user bio",
            userLocation = "Lima",
            userProfileWeb = "www.test.com"
        )
    )

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PhotoInspirationRepository

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(repository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun getPhotosFromDb() = testCoroutineDispatcher.runBlockingTest {
       `when`(repository.getAllPhotosFromDatabase()).thenReturn(flowOf(photoEntities))
        val list = homeViewModel.getAllPhotosToOrder().getOrAwaitValue()
        assertNotNull(list)
    }

    @Test
    fun fetchPhotosSuccess()= testCoroutineDispatcher.runBlockingTest {
        `when`(repository.fetchPhotos(1,20)).thenReturn(Success(flowOf(photoEntities)))
        homeViewModel.getPhotos()
        val liveData = homeViewModel.homeStateLiveData.getOrAwaitValue()
        assertTrue(liveData is PhotosSuccess)
    }

    @Test
    fun fetchPhotosFailure() = testCoroutineDispatcher.runBlockingTest {
        `when`(repository.fetchPhotos(1,20)).thenReturn(Failure(Throwable()))
        homeViewModel.getPhotos()
        val liveData = homeViewModel.homeStateLiveData.getOrAwaitValue()
        assertTrue(liveData is PhotosFailure)
    }
}
package com.manuflowers.photoinspiration.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiService
import com.manuflowers.photoinspiration.getOrAwaitValue
import org.junit.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PhotosInspirationRepositoryImplTest {

    private lateinit var repository: PhotosInspirationRepositoryImpl
    private lateinit var remoteApiManager: RemoteApiManager

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

    @Mock
    lateinit var remoteApiService: RemoteApiService

    @Mock
    lateinit var photosDao: PhotosDao

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteApiManager = RemoteApiManager(remoteApiService)

        repository = PhotosInspirationRepositoryImpl(
            remoteApiManager = remoteApiManager,
            photosDao = photosDao
        )
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun getAllPhotosFromDatabaseIsNotEmpty () = testCoroutineDispatcher.runBlockingTest {
        `when`(repository.getAllPhotosFromDatabase()).thenReturn(flowOf(photoEntities))
        val list = repository.getAllPhotosFromDatabase().asLiveData().getOrAwaitValue()
        assertTrue(list.isNotEmpty())
    }

    @Test
    fun getAllPhotosFromDatabase() = testCoroutineDispatcher.runBlockingTest {
        `when`(repository.getAllPhotosFromDatabase()).thenReturn(flowOf(photoEntities))
        val result = repository.getAllPhotosFromDatabase().asLiveData().getOrAwaitValue()
        assertEquals(photoEntities, result)
    }
}
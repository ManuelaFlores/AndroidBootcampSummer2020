package com.manuflowers.photoinspiration.di

import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiService
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.After
import org.koin.core.qualifier.named
import org.koin.test.get
import retrofit2.Retrofit

class RepositoryModuleTest: KoinTest {
    private val api : RemoteApiService by inject()
    private val baseUrl: String by lazy { get(named("BASE_URL")) as String }
    private val retrofitInstance: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()

    @Before
    fun setup() {
        startKoin {
            modules(repositoryModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test API created`() {
        assertNotNull(api)
    }

    @Test
    fun `Test BASE_URL created`() {
        assert(baseUrl == "https://api.unsplash.com")
    }

    @Test
    fun `Test Retrofit created`() {
        assertNotNull(retrofitInstance)
    }

    @Test
    fun `Test OkHttp created`() {
        assertNotNull(okHttpClient)
    }
}
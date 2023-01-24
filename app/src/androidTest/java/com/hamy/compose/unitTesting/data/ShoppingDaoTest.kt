package com.hamy.compose.unitTesting.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.hamy.compose.getOrAwaitValue
import com.hamy.compose.unitTesting.data.local.ShoppingDao
import com.hamy.compose.unitTesting.data.local.ShoppingItemDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {
    private val testScope = TestScope(Job() + StandardTestDispatcher())

    @get :Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    lateinit var database: ShoppingItemDatabase
    lateinit var dao: ShoppingDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java).allowMainThreadQueries().build()

        dao = database.shoppingDao()
    }

    @Test
    fun insertShoppingItem() = testScope.runTest {

        ShoppingItem("Banana", 1, 100f, "url", 1).apply {
            dao.insertShoppingItem(this)
            assertThat(dao.observeAllShoppingItems().getOrAwaitValue().contains(this))
        }
    }

    @Test
    fun deleteShoppingItem() = testScope.runTest {

        ShoppingItem("Banana", 1, 100f, "url", 1).apply {
            dao.insertShoppingItem(this)
            dao.deleteShoppingItem(this)
            assertThat(dao.observeAllShoppingItems().getOrAwaitValue()).doesNotContain(this)
        }
    }

    @Test
    fun observeTotalPriceSum() = testScope.runTest {
        dao.apply {
            insertShoppingItem(ShoppingItem("Banana", 1, 10f, "url", 1))
            insertShoppingItem(ShoppingItem("Banana", 2, 20f, "url", 2))
            insertShoppingItem(ShoppingItem("Banana", 3, 30f, "url", 3))
            assertThat(observeTotalPrice().getOrAwaitValue()).isEqualTo(1 * 10 + 2 * 20 + 3 * 30)
        }
    }

    fun tearDown() {
        database.close()
    }
}
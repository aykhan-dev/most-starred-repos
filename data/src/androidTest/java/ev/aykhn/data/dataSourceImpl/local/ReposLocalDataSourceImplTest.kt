@file:OptIn(ExperimentalCoroutinesApi::class)

package ev.aykhn.data.dataSourceImpl.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ev.aykhn.data.model.entity.RepoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReposLocalDataSourceImplTest {

    private lateinit var inMemoryDatabase: AppDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        inMemoryDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After
    fun clear() {
        inMemoryDatabase.close()
    }

    @Test
    fun `reposDao should cache items safely`() = runTest {
        val reposDao = inMemoryDatabase.reposDao

        val insertedItems = listOf(
            RepoEntity(
                id = 0,
                name = "",
                description = "",
                starCount = 0,
                username = "",
                userAvatarUrl = "",
            )
        )

        reposDao.insertRepos(insertedItems)

        val cachedItems = reposDao.getRepos().first()

        assertEquals(insertedItems, cachedItems)
    }

}
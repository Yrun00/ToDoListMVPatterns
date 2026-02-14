package com.github.todolistmvpatterns

import android.app.Activity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.todolistmvpatterns.data.Repository
import com.github.todolistmvpatterns.data.RepositoryImpl
import com.github.todolistmvpatterns.data.Task
import com.github.todolistmvpatterns.data.TaskDao
import com.github.todolistmvpatterns.mvc.Controller
import com.github.todolistmvpatterns.mvc.MVCView
import com.github.todolistmvpatterns.mvp.Presenter
import com.github.todolistmvpatterns.mvp.PresenterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "todo.db")
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}

//@Module
//@InstallIn(ActivityComponent::class)
//object MvcModule {
//
//    @Provides
//    @ActivityScoped
//    fun provideController(
//        activity: Activity,
//        repository: Repository,
//    ): Controller = Controller(view = activity as MVCView, repository = repository)
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class PresenterModule {

    @Binds
    @Singleton
    abstract fun bindPresenter(impl: PresenterImpl): Presenter
}
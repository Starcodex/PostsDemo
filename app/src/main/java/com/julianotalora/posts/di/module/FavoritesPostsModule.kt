package com.julianotalora.posts.di.module

import com.julianotalora.posts.api.ApiModule
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.ui.posts.favorites.FavoritesContract
import com.julianotalora.posts.ui.posts.favorites.FavoritesPosts
import com.julianotalora.posts.ui.posts.favorites.FavoritesPostsInteractor
import com.julianotalora.posts.ui.posts.favorites.FavoritesPostsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Bonestack on 25/07/2018.
 */
@Module(includes = arrayOf(ApplicationModule::class,ActivityModule::class,AllPostsModule::class))
class FavoritesPostsModule(private var mFragment: FavoritesPosts) {

    private var fragment: FavoritesPosts

    init{ this.fragment = mFragment }

    @Provides
    fun provideFavoritesPostsInteractor(postDao: PostDao): FavoritesPostsInteractor {
        return FavoritesPostsInteractor(postDao)
    }

    @Provides
    fun provideFavoritesPostsPresenter(interactor: FavoritesPostsInteractor): FavoritesContract.Presenter {
        return FavoritesPostsPresenter(fragment,interactor)
    }

}
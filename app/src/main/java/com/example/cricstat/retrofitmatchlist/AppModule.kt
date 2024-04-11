/*
import com.example.cricstat.retrofitmatchlist.Repository1
import com.example.cricstat.retrofitmatchlist.matchList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   */
/* @Provides
    @Singleton
    fun providesMatchListApi(): matchList {
        return Retrofit.Builder()
            .baseUrl("YOUR_BASE_URL")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(matchList::class.java)
    }*//*


    @Provides
    @Singleton
    fun providesRepository1(matchList: matchList): Repository1 {
        return Repository1(matchList)
    }
}*/

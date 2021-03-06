package io.casey.musikcube.remote.injection

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.casey.musikcube.remote.service.gapless.db.GaplessDb
import io.casey.musikcube.remote.service.playback.impl.streaming.db.OfflineDb
import io.casey.musikcube.remote.service.websocket.WebSocketService
import io.casey.musikcube.remote.service.websocket.model.IDataProvider
import io.casey.musikcube.remote.service.websocket.model.impl.remote.RemoteDataProvider
import io.casey.musikcube.remote.ui.settings.model.ConnectionsDb

@Module
class DataModule {
    @Provides
    fun providesDataProvider(wss: WebSocketService): IDataProvider = RemoteDataProvider(wss)

    @ApplicationScope
    @Provides
    fun providesOfflineDb(context: Context): OfflineDb {
        return Room.databaseBuilder(context,  OfflineDb::class.java, "offline").build()
    }

    @ApplicationScope
    @Provides
    fun providesConnectionsDb(context: Context): ConnectionsDb {
        return Room.databaseBuilder(context, ConnectionsDb::class.java, "connections").build()
    }

    @ApplicationScope
    @Provides
    fun providesGaplessDb(context: Context): GaplessDb {
        return Room.databaseBuilder(context, GaplessDb::class.java, "gapless").build()
    }
}
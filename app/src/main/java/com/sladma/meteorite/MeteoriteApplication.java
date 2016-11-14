package com.sladma.meteorite;

import android.app.Application;
import android.content.Context;

import com.sladma.meteorite.data.local.IMeteoriteRepository;
import com.sladma.meteorite.data.local.MeteoriteRepository;
import com.sladma.meteorite.data.remote.INasaAPI;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Main application class holds global dependencies
 * <p>
 * Created by sladma
 */
public class MeteoriteApplication extends Application {

    private IMeteoriteRepository mMeteoriteRepository;
    private INasaAPI mNasaAPI;

    public static MeteoriteApplication get(Context context) {
        return (MeteoriteApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    public IMeteoriteRepository getMeteoriteRepository() {
        if (mMeteoriteRepository == null) {
            mMeteoriteRepository = new MeteoriteRepository(getNasaAPI(), this);
        }
        return mMeteoriteRepository;
    }

    private INasaAPI getNasaAPI() {
        if (mNasaAPI == null) {
            mNasaAPI = INasaAPI.Factory.create();
        }
        return mNasaAPI;
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}

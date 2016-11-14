package com.sladma.meteorite.data.local;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sladma.common.preferences.SharedPreferencesHelper;
import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.meteorite.data.remote.INasaAPI;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Repository holds all meteorites and filter/sort it
 * <p>
 * Created by sladma
 */
public class MeteoriteRepository implements IMeteoriteRepository {

    private static final String TAG = MeteoriteRepository.class.getSimpleName();
    private static final String UPDATE_TIME_KEY = "UPDATE_TIME";
    private static final long YEAR_2011_TIMESTAMP = 1293840000000L;
    private static final long UPDATE_THRESHOLD = 86400000L; // 1 day

    private final INasaAPI mNasaAPI;
    private final Context mContext;

    public MeteoriteRepository(@NonNull INasaAPI nasaAPI, @NonNull Context context) {
        mNasaAPI = nasaAPI;
        mContext = context;
    }

    @Override
    public void getMeteoritesSince2011SortedByWeight(@NonNull final Callback callback) {
        if (shouldUpdate()) {
            Log.d(TAG, "Updating data");
            fetchDataFromRemoteAndSaveThem(new Callback() {
                @Override
                public void onDone(List<Meteorite> allMeteorites) {
                    Log.d(TAG, "Updating done");
                    // Save update time
                    SharedPreferencesHelper.writeLong(mContext, UPDATE_TIME_KEY, new Date().getTime());
                    callback.onDone(getLocalMeteoritesSince2011SortedByWeight());
                }

                @Override
                public void onError() {
                    Log.d(TAG, "Error occurred while updating");
                    callback.onError();
                }
            });
        } else {
            Log.d(TAG, "Local data are used");
            callback.onDone(getLocalMeteoritesSince2011SortedByWeight());
        }
    }

    private List<Meteorite> getLocalMeteoritesSince2011SortedByWeight() {
        return Realm.getDefaultInstance().where(Meteorite.class)
                .greaterThan("year.timestamp", YEAR_2011_TIMESTAMP)
                .findAllSorted("weight", Sort.DESCENDING);
    }

    private void fetchDataFromRemoteAndSaveThem(@NonNull final Callback callback) {
        mNasaAPI.getAllMeteoriteLandings().enqueue(new retrofit2.Callback<List<Meteorite>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(Call<List<Meteorite>> call, Response<List<Meteorite>> response) {
                Log.d(TAG, "Fetching is done");
                List<Meteorite> meteorites = response.body();
                new StoreAsyncTask(callback).execute(meteorites);
            }

            @Override
            public void onFailure(Call<List<Meteorite>> call, Throwable t) {
                Log.e(TAG, "Error fetching meteorites: " + t);
                callback.onError();
            }
        });
    }

    private boolean shouldUpdate() {
        long lastUpdateTimestamp = SharedPreferencesHelper.readLong(mContext, UPDATE_TIME_KEY, 0);
        return Math.abs(lastUpdateTimestamp - new Date().getTime()) >= UPDATE_THRESHOLD;
    }

    /**
     * Async task to store meteorites into local storage
     */
    private class StoreAsyncTask extends AsyncTask<List<Meteorite>, Void, Void> {

        private final Callback mCallback;

        StoreAsyncTask(@NonNull Callback callback) {
            mCallback = callback;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Meteorite>... lists) {
            Log.d(TAG, "Storing meteorites into local storage");
            List<Meteorite> meteoritesToStore = lists[0];
            Realm realm = Realm.getDefaultInstance();
            for (final Meteorite meteorite : meteoritesToStore) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(meteorite);
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onDone(null);
        }
    }

}

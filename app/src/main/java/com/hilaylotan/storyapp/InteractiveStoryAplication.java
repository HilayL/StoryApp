package com.hilaylotan.storyapp;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InteractiveStoryAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
}

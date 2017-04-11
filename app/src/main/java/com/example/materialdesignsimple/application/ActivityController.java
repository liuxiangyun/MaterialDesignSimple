package com.example.materialdesignsimple.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyun_liu on 2017/4/11.
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    public static void finishAndClearAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}

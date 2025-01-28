package fun.android.notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;

public class App extends Application {
    public static String file_path="";
    public static Handler handler;
    public static int App_Width, App_Height, Status_Bar_Height;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        App_Width = displayMetrics.widthPixels;
        App_Height = displayMetrics.heightPixels;
        context = this.getApplicationContext();
        handler = new Handler();
        file_path = Environment.getExternalStorageDirectory().getPath()+"/";
        @SuppressLint("InternalInsetResource")
        int resourceld = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceld > 0){
            Status_Bar_Height = this.getResources().getDimensionPixelSize(resourceld);
        }
    }
}

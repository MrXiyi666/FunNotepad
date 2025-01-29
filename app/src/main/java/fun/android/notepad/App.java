package fun.android.notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import fun.android.notepad.Fun.权限申请;
import fun.android.notepad.View.View_Main;
import fun.android.notepad.View.View_Menu;

public class App extends Application {
    public static String file_path="";
    public static Handler handler;
    public static int App_Width, App_Height, Status_Bar_Height;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    public static LinearLayout linearLayout;
    public static View_Main view_main;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void init(Activity activity){
        App.activity = activity;
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        App_Width = displayMetrics.widthPixels;
        App_Height = displayMetrics.heightPixels;
        context = activity.getApplicationContext();
        handler = new Handler();
        file_path = Environment.getExternalStorageDirectory().getPath()+"/";
        @SuppressLint({"InternalInsetResource", "DiscouragedApi"})
        int id = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(id > 0){
            Status_Bar_Height = activity.getResources().getDimensionPixelSize(id);
        }
        Window window = App.activity.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        new 权限申请(App.activity);
        linearLayout = activity.findViewById(R.id.main);
        view_main = new View_Menu();
        linearLayout.addView(view_main.getView());
    }
}

package fun.android.notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import java.util.concurrent.TimeUnit;

import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.View.View_Create;
import fun.android.notepad.View.View_Main;
import fun.android.notepad.View.View_Network;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class App extends Application {
    public static String app_path="";
    public static String file_path="";
    public static Handler handler;
    public static int App_Width, App_Height, Status_Bar_Height;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    public static OkHttpClient client;
    public static RelativeLayout relativeLayout;
    public static View_Main view_main;
    public static String text_data = "";
    public static String file_name = "";
    public static String uri="http://mrxiyi.top/FunNotepad/SuiBian/";
    public static int downloads_index=0;
    public static String encode = "utf8";
    public static int text_size = 15;
    public static int scrollView_Y=0;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void init(Activity activity){

        //app_path = activity.getFilesDir().getPath();
        app_path = activity.getExternalFilesDir("").getPath() + "/";
        FunFile.创建文件夹(app_path + "data");
        FunFile.创建文件夹(app_path + "system");

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

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(64);          // 全局并发：64 条 TCP 连接同时跑
        dispatcher.setMaxRequestsPerHost(20);   // 单域名并发：20 条，既不被 CDN 拉黑又能跑满带宽

        client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)  // TCP 握手 30s 足够
                .writeTimeout(60, TimeUnit.SECONDS)    // 上传/发送 60s
                .readTimeout(120, TimeUnit.SECONDS)    // 下载 2min 没数据再抛异常
                .dispatcher(dispatcher)
                .build();

        Window window = App.activity.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        relativeLayout = activity.findViewById(R.id.main);

        if(FunFile.是否存在(App.app_path + "system/url")){
            Fun.addView(new View_Create());
        }else{
            Fun.addView(new View_Network());
        }

    }
}

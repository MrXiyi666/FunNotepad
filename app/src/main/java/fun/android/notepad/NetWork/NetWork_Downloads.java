package fun.android.notepad.NetWork;

import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.View.View_Create;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetWork_Downloads {

    public NetWork_Downloads(){

    }


    public void start(String url, String filename){
        // 1. 非空校验（避免空指针）
        if (url == null || url.isEmpty()) {
            // 可添加错误回调
            return;
        }
        if (filename == null || filename.isEmpty()) {
            return;
        }
        RequestBody requestBody = new FormBody.Builder()
                .add("filename", filename)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        App.client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull java.io.IOException e) {
                // 失败处理（如回调提示）
                e.printStackTrace();
                // 加锁修改计数
                synchronized (App.class) {
                    App.downloads_index = App.downloads_index - 1;
                    if (App.downloads_index <= 0) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            ((View_Create) App.view_main).refresh_list();
                        });
                    }
                }
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if(result.equals("no")){
                        Fun.mess( "云端存储失败");
                        return;
                    }
                    FunFile.写入文件("data/" + filename, result);
                    // 加锁修改计数
                    synchronized (App.class) {
                        App.downloads_index = App.downloads_index - 1;
                        if (App.downloads_index <= 0) {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                ((View_Create) App.view_main).refresh_list();
                            });
                        }
                    }
                }
            }
        });
    }
}

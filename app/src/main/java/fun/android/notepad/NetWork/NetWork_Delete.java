package fun.android.notepad.NetWork;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.Window.Window_Loading;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetWork_Delete {
    private Window_Loading window_loading;
    public NetWork_Delete(){
        window_loading = new Window_Loading();
    }

    public void start(String url, String filename, Dialog dialog){
        window_loading.start();
        if (url == null || url.isEmpty()) {
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
                Fun.mess( "删除失败");
                dialog.dismiss();
                window_loading.close();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    dialog.dismiss();
                    window_loading.close();
                    if(result.equals("no")){
                        Fun.mess( "删除失败");
                        return;
                    }

                    FunFile.删除文件(App.app_path + "data/" + filename);
                    Fun.mess( "删除成功");
                    new Handler(Looper.getMainLooper()).post(() -> {
                        App.view_main.refresh_list();
                    });

                }
            }
        });

    }
}

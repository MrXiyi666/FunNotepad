package fun.android.notepad.NetWork;

import android.util.Log;

import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.List;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetWork_FileList {

    public NetWork_FileList(){

    }

    public void start(String url){
        RequestBody requestBody = new FormBody.Builder()
                .add("filename", "filelist")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        App.client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull java.io.IOException e) {
                e.printStackTrace();
                Fun.mess("刷新失败");
                App.view_main.window_loadings_clear();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.w("filelist", result);
                    response.close();
                    List<String> fileList = Arrays.asList(result.split(","));
                    App.downloads_index = fileList.size();
                    for(String name : fileList){
                        new NetWork_Downloads().start(App.uri + "downloads.php", name);
                    }
                }
            }
        });
    }
}

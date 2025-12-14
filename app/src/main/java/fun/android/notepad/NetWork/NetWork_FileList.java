package fun.android.notepad.NetWork;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import okhttp3.Request;

public class NetWork_FileList {

    public NetWork_FileList(){

    }

    public void start(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 4. 执行请求（后续回调逻辑不变，仅请求体调整）
        App.client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull java.io.IOException e) {
                // 失败处理（如回调提示）
                e.printStackTrace();
                Fun.mess("刷新失败");
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
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

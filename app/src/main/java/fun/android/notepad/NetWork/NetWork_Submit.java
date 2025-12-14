package fun.android.notepad.NetWork;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.View.View_Create;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetWork_Submit {

    public NetWork_Submit() {

    }

    public void start(String url, String filename, String filedata, AlertDialog dialog, boolean tiao) {
        // 1. 非空校验（避免空指针）
        if (url == null || url.isEmpty()) {
            // 可添加错误回调
            return;
        }
        if (filename == null || filename.isEmpty()) {
            filename = "default_" + System.currentTimeMillis() + ".txt"; // 兜底默认文件名
        }
        if (filedata == null) {
            filedata = "";
        }

        // 2. 构建表单请求体（form-data 格式，传输 filename + filedata 两个参数）
        RequestBody requestBody = new FormBody.Builder()
                .add("filename", filename) // 文件名参数
                .add("filedata", filedata) // 文件内容参数
                .build();

        // 3. 构建 Request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 4. 执行请求（后续回调逻辑不变，仅请求体调整）
        App.client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull java.io.IOException e) {
                // 失败处理（如回调提示）
                e.printStackTrace();
                dialog.dismiss();
                FunFile.写入文件("data/" + App.file_name, App.text_data);
                Fun.mess( "云端存储失败\n本地存储成功");
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws java.io.IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    dialog.dismiss();
                    if(result.equals("yes")){
                        Fun.mess( "云端存储成功");
                        FunFile.写入文件("data/" + App.file_name, App.text_data);
                        if(tiao){
                            App.text_data = "";
                            App.file_name = "";
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                App.relativeLayout.removeAllViews();
                                App.view_main = new View_Create();
                                App.relativeLayout.addView(App.view_main.getView());
                            }, 1000);

                        }
                        return;
                    }
                    Fun.mess( "云端存储失败");
                }
            }
        });
    }

}

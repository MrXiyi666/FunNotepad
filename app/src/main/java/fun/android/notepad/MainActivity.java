package fun.android.notepad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.Fun.权限申请;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.activity = this;
        new 权限申请(this);
        FunFile.写入文件("你好啊.txt", "你好啊");
    }

}
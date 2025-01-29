package fun.android.notepad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.View.View_Edit;
import fun.android.notepad.View.View_Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.init(this);
        App.open_file_Launcher = registerForActivityResult(
                new ActivityResultContracts.OpenDocument(),
                uri -> {
                    if (uri == null) {
                       return;
                    }
                    App.FileName = null;
                    App.uri = uri;
                    App.Txt_Data = FunFile.ReadURI(uri);
                    App.FileName = FunFile.GetUriName(uri);
                    App.relativeLayout.removeAllViews();
                    App.view_main = new View_Edit();
                    App.relativeLayout.addView(App.view_main.getView());
                }
        );
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(App.view_main instanceof View_Edit){
                    App.uri = null;
                    App.Txt_Data = "";
                    App.FileName = null;
                    App.relativeLayout.removeAllViews();
                    App.view_main = new View_Menu();
                    App.relativeLayout.addView(App.view_main.getView());
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };

// 将回调添加到 Activity 或 Fragment
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
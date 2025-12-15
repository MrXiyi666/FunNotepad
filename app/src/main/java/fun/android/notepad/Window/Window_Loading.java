package fun.android.notepad.Window;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.R;

public class Window_Loading {
    Dialog dialog;
    public Window_Loading(){

    }

    public void start(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                dialog = new Dialog(App.activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view = View.inflate(App.activity, R.layout.window_loading, null);
                dialog.setContentView(view);
                dialog.setCancelable(true);
                Fun.setWindowTheme(Objects.requireNonNull(dialog.getWindow()));
                dialog.show();
            }
        });
    }

    public void close(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });

    }
}

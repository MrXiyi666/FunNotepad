package fun.android.notepad.Window;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.NetWork.NetWork_Delete;
import fun.android.notepad.R;

public class Window_Delete {

    public Window_Delete(String filename){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = new Dialog(App.activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view = View.inflate(App.activity, R.layout.window_delete, null);
                AppCompatButton button_ok = view.findViewById(R.id.button_ok);

                button_ok.setOnClickListener(V->{
                    new NetWork_Delete().start(App.uri + "delete.php", filename, dialog);
                });

                Fun.setButtonTheme(button_ok);

                dialog.setCancelable(true);
                dialog.setContentView(view);
                Fun.setWindowTheme(Objects.requireNonNull(dialog.getWindow()));
                dialog.show();
            }
        });
    }
}

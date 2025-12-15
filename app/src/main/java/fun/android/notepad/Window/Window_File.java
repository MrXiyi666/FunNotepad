package fun.android.notepad.Window;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.NetWork.NetWork_Submit;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Create;

public class Window_File {
    public Window_File(){
        Dialog dialog = new Dialog(App.activity);
        View view = View.inflate(App.activity, R.layout.window_file_view, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_save = view.findViewById(R.id.button_save);
        AppCompatButton button_close = view.findViewById(R.id.button_close);
        AppCompatButton button_save_or_close = view.findViewById(R.id.button_save_or_close);
        return_icon.setOnClickListener(V-> dialog.dismiss());
        button_save.setOnClickListener(V->{
            new NetWork_Submit().start(App.uri + "submit.php", App.file_name, App.text_data, dialog, false);
        });
        button_close.setOnClickListener(V->{
            App.text_data = "";
            App.file_name = "";
            App.relativeLayout.removeAllViews();
            App.view_main = new View_Create();
            App.relativeLayout.addView(App.view_main.getView());
            dialog.dismiss();
        });
        button_save_or_close.setOnClickListener(V->{
            new NetWork_Submit().start(App.uri + "submit.php", App.file_name, App.text_data, dialog, true);
        });

        Fun.setButtonTheme(button_save);
        Fun.setButtonTheme(button_close);
        Fun.setButtonTheme(button_save_or_close);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        Fun.setWindowTheme(Objects.requireNonNull(dialog.getWindow()));
        dialog.show();
    }
}

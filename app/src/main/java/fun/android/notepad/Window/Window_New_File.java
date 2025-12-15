package fun.android.notepad.Window;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Edit;

public class Window_New_File {

    public Window_New_File(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = new Dialog(App.activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                View view = View.inflate(App.activity, R.layout.window_new_file_view, null);
                ImageView return_icon = view.findViewById(R.id.return_icon);
                EditText edit_view = view.findViewById(R.id.edit_view);
                AppCompatButton button_open_file = view.findViewById(R.id.button_open_file);

                button_open_file.setOnClickListener(V->{
                    App.file_name = edit_view.getText().toString();
                    if(!App.file_name.isEmpty()){
                        App.text_data="";
                        App.relativeLayout.removeAllViews();
                        App.view_main = new View_Edit();
                        App.relativeLayout.addView(App.view_main.getView());
                        dialog.dismiss();
                        return;
                    }
                    Fun.mess("不能为空");

                });

                return_icon.setOnClickListener(V-> dialog.dismiss());

                Fun.setButtonTheme(button_open_file);
                Fun.setEditTheme(edit_view);

                dialog.setContentView(view);
                dialog.setCancelable(true);
                Fun.setWindowTheme(Objects.requireNonNull(dialog.getWindow()));
                dialog.show();
            }
        });
    }
}

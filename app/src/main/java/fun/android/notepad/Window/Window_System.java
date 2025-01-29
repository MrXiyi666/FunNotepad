package fun.android.notepad.Window;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.R;

public class Window_System {
    public Window_System(){
        AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
        View view = View.inflate(App.activity, R.layout.window_system, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_utf8 = view.findViewById(R.id.button_utf8);
        AppCompatButton button_ascii = view.findViewById(R.id.button_ascii);
        AppCompatButton button_gb2312 = view.findViewById(R.id.button_gb2312);
        return_icon.setOnClickListener(_ -> dialog.dismiss());

        button_utf8.setOnClickListener(V->{
           App.encode = "UTF-8";
           dialog.dismiss();
        });

        button_ascii.setOnClickListener(V->{
            App.encode = "ASCII";
            dialog.dismiss();
        });

        button_gb2312.setOnClickListener(V->{
            App.encode = "GB2312";
            dialog.dismiss();
        });
        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}

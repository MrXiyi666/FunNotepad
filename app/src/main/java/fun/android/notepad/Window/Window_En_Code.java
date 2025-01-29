package fun.android.notepad.Window;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.R;

public class Window_En_Code {
    public Window_En_Code(EditText edit_view){
        AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
        View view = View.inflate(App.activity, R.layout.window_encode, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_utf8 = view.findViewById(R.id.button_utf8);
        AppCompatButton button_gb2312 = view.findViewById(R.id.button_gb2312);
        AppCompatButton button_ascii = view.findViewById(R.id.button_ascii);
        return_icon.setOnClickListener(V-> dialog.dismiss());
        App.Txt_Data = edit_view.getText().toString();
        button_utf8.setOnClickListener(V->{
            App.Txt_Data = Fun.转换编码(App.Txt_Data, "UTF-8");
            edit_view.setText(App.Txt_Data);
            dialog.dismiss();
        });

        button_gb2312.setOnClickListener(V->{
            App.Txt_Data = Fun.转换编码(App.Txt_Data, "GB2312");
            edit_view.setText(App.Txt_Data);
            dialog.dismiss();
        });
        button_ascii.setOnClickListener(V->{
            App.Txt_Data = Fun.转换编码(App.Txt_Data, "ASCII");
            edit_view.setText(App.Txt_Data);
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

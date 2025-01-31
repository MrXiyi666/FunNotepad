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
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Edit;
import fun.android.notepad.View.View_Menu;

public class Window_File {
    public Window_File(){
        AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
        View view = View.inflate(App.activity, R.layout.window_file_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_save = view.findViewById(R.id.button_save);
        AppCompatButton button_close = view.findViewById(R.id.button_close);
        AppCompatButton button_save_or_close = view.findViewById(R.id.button_save_or_close);
        return_icon.setOnClickListener(_ -> dialog.dismiss());
        button_save.setOnClickListener(V->{
            FunFile.保存文件();
            dialog.dismiss();
        });
        button_close.setOnClickListener(V->{
            App.uri = null;
            App.Txt_Data = null;
            App.FileName = null;
            App.relativeLayout.removeAllViews();
            App.view_main = new View_Menu();
            App.relativeLayout.addView(App.view_main.getView());
            dialog.dismiss();
        });
        button_save_or_close.setOnClickListener(V->{
            FunFile.保存文件();
            App.uri = null;
            App.Txt_Data = null;
            App.FileName = null;
            App.relativeLayout.removeAllViews();
            App.view_main = new View_Menu();
            App.relativeLayout.addView(App.view_main.getView());
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

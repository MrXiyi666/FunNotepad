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
import java.io.File;
import java.util.Objects;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Edit;

public class Window_New_File {

    public Window_New_File(){
        AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
        View view = View.inflate(App.activity, R.layout.window_new_file_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText edit_view = view.findViewById(R.id.edit_view);
        AppCompatButton button_open_file = view.findViewById(R.id.button_open_file);

        button_open_file.setOnClickListener(V->{
            App.FileName = edit_view.getText().toString();
            if(App.FileName.isEmpty()){
                return;
            }
            if(Fun.提取后缀(App.FileName).isEmpty()){
                App.FileName = App.FileName + ".txt";
            }
            App.uri = null;
            App.Txt_Data = "";
            if(new File(App.file_path + App.FileName).exists()){
                App.Txt_Data = FunFile.读取文件(App.FileName);
            }
            App.relativeLayout.removeAllViews();
            App.view_main = new View_Edit();
            App.relativeLayout.addView(App.view_main.getView());
            dialog.dismiss();
        });

        return_icon.setOnClickListener(V-> dialog.dismiss());
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

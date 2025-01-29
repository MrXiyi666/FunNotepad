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
import fun.android.notepad.R;

public class Window_Edit {

    public Window_Edit(EditText edit_view){
        AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
        View view = View.inflate(App.activity, R.layout.window_edit, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_read_only = view.findViewById(R.id.button_read_only);
        AppCompatButton button_left = view.findViewById(R.id.button_left);
        AppCompatButton button_center = view.findViewById(R.id.button_center);
        AppCompatButton button_end = view.findViewById(R.id.button_end);
        return_icon.setOnClickListener(_ -> dialog.dismiss());
        boolean isReadOnly = (
                !edit_view.isFocusable() &&
                        !edit_view.isFocusableInTouchMode() &&
                        !edit_view.isClickable() &&
                        !edit_view.isCursorVisible()
        );
        if(isReadOnly){
            button_read_only.setText("设为可读");
        }else{
            button_read_only.setText("设为只读");
        }
        button_read_only.setOnClickListener(V->{
            dialog.dismiss();
            if(isReadOnly){
                edit_view.setFocusable(true);
                edit_view.setFocusableInTouchMode(true);
                edit_view.setClickable(true);
                edit_view.setCursorVisible(true);
                return;
            }
            edit_view.setFocusable(false);
            edit_view.setFocusableInTouchMode(false);
            edit_view.setClickable(false);
            edit_view.setCursorVisible(false);

        });
        button_left.setOnClickListener(V->{
            edit_view.setGravity(Gravity.START); // 设置文本左对齐
            dialog.dismiss();
        });
        button_center.setOnClickListener(V->{
            edit_view.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog.dismiss();
        });
        button_end.setOnClickListener(V->{
            edit_view.setGravity(Gravity.END);
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

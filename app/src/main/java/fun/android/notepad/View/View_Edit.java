package fun.android.notepad.View;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.R;
import fun.android.notepad.Window.Window_Edit;
import fun.android.notepad.Window.Window_File;

public class View_Edit extends View_Main{
    private RelativeLayout title;
    private TextView top_view;
    private EditText edit_view;
    private ImageView return_icon;
    private AppCompatButton button_file, button_edit, button_encode, button_style;
    public View_Edit(){
        view = View.inflate(App.activity, R.layout.view_edit, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        title = view.findViewById(R.id.title);
        top_view = view.findViewById(R.id.top_view);
        return_icon = view.findViewById(R.id.return_icon);
        edit_view = view.findViewById(R.id.edit_view);
        button_file = view.findViewById(R.id.button_file);
        button_edit = view.findViewById(R.id.button_edit);
        button_encode = view.findViewById(R.id.button_encode);
        button_style = view.findViewById(R.id.button_style);
    }

    @Override
    public void Event() {
        super.Event();
        // 3. 核心步骤：创建LayoutParams并设置margin
        // 注意：LayoutParams的类型必须和父布局类型匹配（这里父布局是LinearLayout，所以用LinearLayout.LayoutParams）
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.topMargin = App.Status_Bar_Height;
        // 4. 将LayoutParams应用到控件上
        title.setLayoutParams(layoutParams);


        top_view.setText(App.file_name);
        edit_view.setText(App.text_data);
        edit_view.setGravity(Gravity.CENTER_HORIZONTAL);

        return_icon.setOnClickListener(V->{
            Fun.addView(new View_Create());
        });


        button_file.setOnClickListener(V->{
            App.text_data = edit_view.getText().toString();
            new Window_File();
        });
        button_edit.setOnClickListener(V->{
            new Window_Edit(edit_view);

        });
        button_encode.setOnClickListener(V->{

        });
        button_style.setOnClickListener(V->{

        });
    }

    @Override
    public void Resume() {
        super.Resume();
    }

    @Override
    public void Stop() {
        super.Stop();
    }

    @Override
    public void Destroy() {
        super.Destroy();
    }
}

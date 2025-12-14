package fun.android.notepad.View;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.notepad.App;
import fun.android.notepad.R;
import fun.android.notepad.Window.Window_Edit;
import fun.android.notepad.Window.Window_File;

public class View_Edit extends View_Main{
    private TextView top_view;
    private EditText edit_view;
    private AppCompatButton button_file, button_edit, button_encode, button_style;
    public View_Edit(){
        view = View.inflate(App.activity, R.layout.view_edit, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        top_view = view.findViewById(R.id.top_view);
        edit_view = view.findViewById(R.id.edit_view);
        button_file = view.findViewById(R.id.button_file);
        button_edit = view.findViewById(R.id.button_edit);
        button_encode = view.findViewById(R.id.button_encode);
        button_style = view.findViewById(R.id.button_style);
    }

    @Override
    public void Event() {
        super.Event();
        top_view.setPadding(0,  App.Status_Bar_Height, 0, 0);
        top_view.setText(App.file_name);
        edit_view.setText(App.text_data);
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

package fun.android.notepad.View;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import fun.android.notepad.App;
import fun.android.notepad.R;
import fun.android.notepad.Window.Window_New_File;

public class View_Menu extends View_Main{
    private TextView top_view;
    private LinearLayout linear;
    private AppCompatButton button_new_file;
    public View_Menu(){
        view = View.inflate(App.activity, R.layout.view_menu, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        top_view = view.findViewById(R.id.top_view);
        linear = view.findViewById(R.id.linear);
        button_new_file = view.findViewById(R.id.button_new_file);
    }

    @Override
    public void Event() {
        super.Event();
        top_view.setPadding(0,  0, 0, App.Status_Bar_Height);
        for(int i=0; i<100; i++){
            linear.addView(new History_View("文本文档     " + i, linear).getView());
        }
        button_new_file.setOnClickListener(V->{
            new Window_New_File();
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

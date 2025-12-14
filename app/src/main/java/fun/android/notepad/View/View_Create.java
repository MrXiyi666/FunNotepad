package fun.android.notepad.View;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.R;
import fun.android.notepad.Window.Window_New_File;
import fun.android.notepad.Window.Window_System;

public class View_Create extends View_Main{
    private SwipeRefreshLayout swiperefresh;
    private TextView top_view;
    private LinearLayout linear_main, linear;
    private AppCompatButton button_new_file;
    private ImageView img_system;
    public View_Create(){
        view = View.inflate(App.activity, R.layout.view_create, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        top_view = view.findViewById(R.id.top_view);
        linear_main = view.findViewById(R.id.linear_main);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        linear = view.findViewById(R.id.linear);
        img_system = view.findViewById(R.id.img_system);
        button_new_file = view.findViewById(R.id.button_new_file);
    }

    @Override
    public void Event() {
        super.Event();
        top_view.setPadding(0,  App.Status_Bar_Height, 0, 0);
        img_system.setOnClickListener(V->{
            new Window_System();
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Fun.DPToPX(App.activity, App.App_Width), LinearLayout.LayoutParams.MATCH_PARENT);
        linear_main.setLayoutParams(params);
        button_new_file.setOnClickListener(V->{
            new Window_New_File();
        });

        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(false);
        });


        List<String> list_file = FunFile.遍历文件夹(App.app_path + "data");
        for(String name : list_file){
            linear.addView(Fun.getChilde(name), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
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

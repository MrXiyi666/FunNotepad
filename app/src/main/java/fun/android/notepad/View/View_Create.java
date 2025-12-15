package fun.android.notepad.View;

import android.os.Handler;
import android.os.Looper;
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
import fun.android.notepad.NetWork.NetWork_FileList;
import fun.android.notepad.R;
import fun.android.notepad.Window.Window_Loading;
import fun.android.notepad.Window.Window_New_File;

public class View_Create extends View_Main{
    private SwipeRefreshLayout swiperefresh;
    private TextView title_view;
    private LinearLayout linear_main, linear;
    private AppCompatButton button_new_file;
    private ImageView img_system;
    private Window_Loading window_loading;
    public View_Create(){
        view = View.inflate(App.activity, R.layout.view_create, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        title_view = view.findViewById(R.id.title_view);
        linear_main = view.findViewById(R.id.linear_main);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        linear = view.findViewById(R.id.linear);
        img_system = view.findViewById(R.id.img_system);
        button_new_file = view.findViewById(R.id.button_new_file);
        window_loading =  new Window_Loading();
    }

    @Override
    public void Event() {
        super.Event();
        title_view.setPadding(0,  App.Status_Bar_Height, 0, App.Status_Bar_Height);
        img_system.setOnClickListener(V->{
          //  new Window_System();
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Fun.DPToPX(App.activity, App.App_Width), LinearLayout.LayoutParams.MATCH_PARENT);
        linear_main.setLayoutParams(params);

        Fun.setButtonTheme(button_new_file);
        button_new_file.setOnClickListener(V->{
            new Window_New_File();
        });

        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(false);
            window_loading.start();
            new NetWork_FileList().start(App.uri + "file_list.php");

        });


        List<String> list_file = FunFile.遍历文件夹(App.app_path + "data");
        for(int i=0; i< list_file.size(); i++){
            if(i==list_file.size()-1){
                linear.addView(Fun.getChilde(list_file.get(i), true), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                continue;
            }
            linear.addView(Fun.getChilde(list_file.get(i), false), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void refresh_list(){
        super.refresh_list();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                linear.removeAllViews();
                List<String> list_file = FunFile.遍历文件夹(App.app_path + "data");
                for(int i=0; i< list_file.size(); i++){
                    if(i==list_file.size()-1){
                        linear.addView(Fun.getChilde(list_file.get(i), true), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        continue;
                    }
                    linear.addView(Fun.getChilde(list_file.get(i), false), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
                Fun.mess("刷新成功");
                window_loading.close();
            }
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

package fun.android.notepad.View;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.Fun.FunFile;
import fun.android.notepad.R;

public class View_Network extends View_Main{
    AppCompatButton button_ok;
    EditText edit_dizhi;
    public View_Network(){
        view = View.inflate(App.activity, R.layout.view_network, null);
        Create();
        Event();
    }

    @Override
    public void Create() {
        super.Create();
        button_ok = view.findViewById(R.id.button_ok);
        edit_dizhi = view.findViewById(R.id.edit_dizhi);


        Fun.setEditTheme(edit_dizhi);
        Fun.setButtonTheme(button_ok);
        button_ok.setOnClickListener(V->{
            App.uri = edit_dizhi.getText().toString();
            if(!App.uri.isEmpty() ){
                FunFile.写入文件("system/url", App.uri);
                App.relativeLayout.removeAllViews();
                App.view_main = new View_Create();
                App.relativeLayout.addView(App.view_main.getView(),new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return;
            }
            Fun.mess("不能为空");
        });
    }

}
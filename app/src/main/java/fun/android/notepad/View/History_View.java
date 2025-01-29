package fun.android.notepad.View;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import fun.android.notepad.App;
import fun.android.notepad.Fun.Fun;
import fun.android.notepad.R;

public class History_View {
    private View view;
    public History_View(String data, LinearLayout linear){
        view = View.inflate(App.activity, R.layout.history_view, null);
        TextView text_view = view.findViewById(R.id.text_view);
        text_view.setText(data);
        view.setOnClickListener(V->{
            Fun.mess(data);
        });
        view.setOnLongClickListener(V->{
            linear.removeView(view);
            Fun.mess("删除成功");
            return true;
        });
    }

    public View getView(){
        return view;
    }


}

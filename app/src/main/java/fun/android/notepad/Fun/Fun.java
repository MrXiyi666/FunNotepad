package fun.android.notepad.Fun;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import fun.android.notepad.App;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Edit;
import java.util.Objects;

public class Fun {

    public static void mess(String text){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = new Dialog(App.activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view = View.inflate(App.activity, R.layout.window_toast_view, null);
                TextView text_id = view.findViewById(R.id.text_id);
                text_id.setText(text);
                dialog.setContentView(view);
                dialog.setCancelable(true);
                Fun.setWindowTheme(Objects.requireNonNull(dialog.getWindow()));
                dialog.show();
                new Thread(()->{
                    try {
                        Thread.sleep(2000);
                        dialog.dismiss();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }, 1);
    }

    public static int DPToPX(Activity activity, int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return (int)(dp * density);
    }


    public static void setWindowTheme(Window window){
        GradientDrawable roundedBackground = new GradientDrawable();
        roundedBackground.setShape(GradientDrawable.RECTANGLE);
        roundedBackground.setColor(Color.parseColor("#ffffff"));
        float cornerRadius = DPToPX(App.activity, 20);
        roundedBackground.setCornerRadius(cornerRadius);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.0f;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = App.App_Width - App.App_Width / 10;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(roundedBackground);
    }


    public static View getChilde(String name, boolean di_xian){
        View view = View.inflate(App.activity, R.layout.childe_view, null);
        TextView file_name = view.findViewById(R.id.file_name);
        TextView text_data = view.findViewById(R.id.text_data);
        View view_di = view.findViewById(R.id.view_di);

        String data = FunFile.读取文件(App.app_path + "data/" + name);
        file_name.setText(name);
        text_data.setText(data);

        if(di_xian){
            view_di.setVisibility(View.GONE);
        }

        view.setOnClickListener(V->{
            App.file_name = name;
            App.text_data = data;
            if(!App.file_name.isEmpty()){
                App.relativeLayout.removeAllViews();
                App.view_main = new View_Edit();
                App.relativeLayout.addView(App.view_main.getView());
            }
        });

        return view;
    }

}

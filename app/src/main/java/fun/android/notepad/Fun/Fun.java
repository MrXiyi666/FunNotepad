package fun.android.notepad.Fun;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import fun.android.notepad.App;
import fun.android.notepad.R;
import androidx.appcompat.app.AlertDialog;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class Fun {
    public static void mess(String name){
        App.handler.post(()->{
            AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
            View view = View.inflate(App.activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            ScrollView scrollView = view.findViewById(R.id.scrollView);
            TextView di_text = view.findViewById(R.id.di_text);
            return_icon.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Fun.DPToPX(App.activity, App.App_Height / 30));
            di_text.setLayoutParams(params);
            view.post(()->{
                if(text_id.getHeight() > App.App_Height){
                    scrollView.getLayoutParams().height = App.App_Height / 2;
                    scrollView.requestLayout();
                }
            });
            text_id.setText(name);

            return_icon.setOnClickListener(_ -> dialog.dismiss());
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.show();
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });
    }

    public static void mess(String name, int time){
        App.handler.post(()->{
            AlertDialog dialog = new AlertDialog.Builder(App.activity,  R.style.AlertDialog_Loading).create();
            View view = View.inflate(App.activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            ScrollView scrollView = view.findViewById(R.id.scrollView);
            TextView di_text = view.findViewById(R.id.di_text);
            return_icon.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Fun.DPToPX(App.activity, App.App_Height / 30));
            di_text.setLayoutParams(params);
            view.post(()->{
                if(text_id.getHeight() > App.App_Height){
                    scrollView.getLayoutParams().height = App.App_Height / 2;
                    scrollView.requestLayout();
                }
            });
            text_id.setText(name);

            return_icon.setOnClickListener(_ -> dialog.dismiss());
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.show();
            new Thread(()->{
                try {
                    Thread.sleep(time);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });
    }
    public static int DPToPX(Activity activity, int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return (int)(dp * density);
    }

    public static String 提取后缀(String fileName){
        int dotIndex = fileName.lastIndexOf('.');
        boolean hasExtension = dotIndex > 0 && dotIndex < fileName.length() - 1;
        if (hasExtension) {
            return fileName.substring(dotIndex);
        } else {
            return "";
        }
    }

    public static String 检测编码(byte[] bytes) {
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        return encoding+"";
    }

    public static String 转换编码(String data, String encode){
        byte[] bytes;
        try {
            bytes = data.getBytes(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return new String(bytes);
    }
}

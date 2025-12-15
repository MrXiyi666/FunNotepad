package fun.android.notepad.Fun;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import fun.android.notepad.App;
import fun.android.notepad.R;
import fun.android.notepad.View.View_Edit;
import fun.android.notepad.Window.Window_Delete;

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

        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 2. 关闭背景暗化（彻底清除 dim 效果）
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setElevation(0f);
        // 兼容部分机型的 DecorView 高程
        window.getDecorView().setElevation(0f);
        // 4. 清空 DecorView 背景（避免默认样式叠加）
        window.getDecorView().setBackground(null);
        // 5. 关闭窗口装饰（边框/阴影）
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.0f;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = App.App_Width - App.App_Width / 10;
        window.setAttributes(layoutParams);

        GradientDrawable roundedBackground = new GradientDrawable();
        roundedBackground.setShape(GradientDrawable.RECTANGLE);
        roundedBackground.setColor(Color.parseColor("#ffffff")); // 白色背景
        float cornerRadius = DPToPX(App.activity, 20);
        roundedBackground.setCornerRadius(cornerRadius); // 20dp圆角

// ========== 新增：添加2dp黑色描边 ==========
        int strokeWidth = DPToPX(App.activity, 2); // 2dp描边宽度（必须转px适配）
        int strokeColor = Color.parseColor("#000000"); // 黑色描边
        roundedBackground.setStroke(strokeWidth, strokeColor);

        window.setBackgroundDrawable(roundedBackground);


    }

    public static void setEditTheme(EditText editText){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DPToPX(App.activity, 20));
        // 3. 设置描边（宽度px + 颜色）
        int strokeWidth = DPToPX(App.activity, 2); // 2dp描边宽度
        int strokeColor = Color.parseColor("#000000"); // 描边颜色（浅蓝色）
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        gradientDrawable.setColor(Color.parseColor("#ffffff")); // 浅蓝背景
        editText.setBackground(gradientDrawable);
        editText.setPadding(DPToPX(App.activity, 10), DPToPX(App.activity, 5), DPToPX(App.activity, 10), DPToPX(App.activity, 5));
    }


    public static void setButtonTheme(AppCompatButton button) {
        // ==================== 背景样式配置（原有逻辑） ====================
        // 1. 配置默认状态的Drawable（白色背景、黑色描边、圆角）
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(DPToPX(App.activity, 20)); // 20dp圆角
        normalDrawable.setColor(Color.parseColor("#FFFFFF")); // 默认白色背景
        normalDrawable.setStroke(DPToPX(App.activity, 2), Color.parseColor("#000000")); // 2dp黑色描边

        // 2. 配置按下/选中状态的Drawable（灰色背景、黑色描边、圆角）
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(DPToPX(App.activity, 20)); // 与默认状态圆角一致
        pressedDrawable.setColor(Color.parseColor("#808080")); // 按下灰色背景
        pressedDrawable.setStroke(DPToPX(App.activity, 2), Color.parseColor("#000000")); // 保持黑色描边

        // 3. 构建背景状态选择器
        StateListDrawable bgStateList = new StateListDrawable();
        bgStateList.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        bgStateList.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        bgStateList.addState(new int[]{}, normalDrawable);

        // ==================== 文本颜色状态配置（新增逻辑） ====================
        // 定义文本颜色的状态数组：按下/选中时白色，默认黑色
        int[][] states = new int[][]{
                {android.R.attr.state_pressed}, // 按下状态
                {android.R.attr.state_selected}, // 选中状态
                {} // 默认状态
        };
        int[] colors = new int[]{
                Color.parseColor("#FFFFFF"), // 按下/选中时文本白色
                Color.parseColor("#FFFFFF"), // 选中时文本白色
                Color.parseColor("#000000")  // 默认文本黑色
        };
        // 创建文本颜色状态列表
        ColorStateList textColorStateList = new ColorStateList(states, colors);

        // ==================== 应用样式到按钮 ====================
        button.setBackground(bgStateList); // 设置背景
        button.setTextColor(textColorStateList); // 设置文本状态颜色

    }

    public static void setViewTheme(View view){
        // 2. 创建形状并设置属性
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(DPToPX(App.activity, 20));
        shape.setStroke(DPToPX(App.activity, 2), Color.BLACK); // 描边：2px 黑色
        shape.setColor(Color.WHITE); // 背景色（可选）
        view.setBackground(shape);
    }

    public static void setScrollViewTheme(View view){
        // 2. 创建形状并设置属性
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(DPToPX(App.activity, 20));
        shape.setStroke(DPToPX(App.activity, 2), Color.TRANSPARENT); // 描边：2px 黑色
        shape.setColor(Color.WHITE); // 背景色（可选）
        view.setBackground(shape);
    }

    public static View getChilde(String name){
        View view = View.inflate(App.activity, R.layout.childe_view, null);
        TextView file_name = view.findViewById(R.id.file_name);
        TextView text_data = view.findViewById(R.id.text_data);
        LinearLayout linear = view.findViewById(R.id.linear);

        String data = FunFile.读取文件(App.app_path + "data/" + name);
        file_name.setText(name);
        text_data.setText(data.replaceAll("[\r\n]", ""));

        view.setOnClickListener(V->{
            App.file_name = name;
            App.text_data = FunFile.读取文件(App.app_path + "data/" + name);
            if(!App.file_name.isEmpty()){
                App.relativeLayout.removeAllViews();
                App.view_main = new View_Edit();
                App.relativeLayout.addView(App.view_main.getView());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Window_Delete(name);
                return true;
            }
        });
        setViewTheme(linear);
        return view;
    }

}

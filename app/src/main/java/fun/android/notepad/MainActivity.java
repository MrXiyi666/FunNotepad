package fun.android.notepad;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import fun.android.notepad.View.View_Edit;
import fun.android.notepad.View.View_Create;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.init(this);
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(App.view_main instanceof View_Edit){
                    App.relativeLayout.removeAllViews();
                    App.view_main = new View_Create();
                    App.relativeLayout.addView(App.view_main.getView());
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
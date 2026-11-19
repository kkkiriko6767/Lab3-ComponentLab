package com.fjnu.componentlab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 实验三入口界面：五个按钮分别进入五个界面组件的实验。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("实验三 Android 界面组件");

        bind(R.id.btn_listview, ListViewActivity.class);
        bind(R.id.btn_dialog, DialogActivity.class);
        bind(R.id.btn_menu, MenuActivity.class);
        bind(R.id.btn_action_mode, ActionModeActivity.class);
        bind(R.id.btn_recycler, RecyclerViewActivity.class);
    }

    private void bind(int id, final Class<?> target) {
        Button button = findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, target));
            }
        });
    }
}

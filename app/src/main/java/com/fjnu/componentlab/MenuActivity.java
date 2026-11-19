package com.fjnu.componentlab;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 3. 使用 XML 定义菜单：字体大小（10/16/20）、普通菜单项、字体颜色（红/黑）。
 */
public class MenuActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("XML 菜单");
        tvTest = findViewById(R.id.tv_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.font_small) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            Toast.makeText(this, "字体大小：10 号字", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.font_medium) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            Toast.makeText(this, "字体大小：16 号字", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.font_large) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            Toast.makeText(this, "字体大小：20 号字", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_normal) {
            Toast.makeText(this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.color_red) {
            tvTest.setTextColor(0xFFD32F2F);
            Toast.makeText(this, "字体颜色：红色", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.color_black) {
            tvTest.setTextColor(0xFF000000);
            Toast.makeText(this, "字体颜色：黑色", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

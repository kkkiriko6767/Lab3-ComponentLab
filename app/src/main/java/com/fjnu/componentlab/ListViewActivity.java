package com.fjnu.componentlab;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. ListView + SimpleAdapter：动物列表。
 * 列表项左边是动物名称、右边是动物图片；点击后弹出 Toast，同时发送一条通知，
 * 通知的图标使用应用图标，标题为被点击的列表项内容。
 */
public class ListViewActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "lab3_list";
    private static final int REQ_NOTIFICATION = 100;

    private ListView listView;
    private String pendingTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setTitle("ListView + SimpleAdapter");

        listView = findViewById(R.id.list_view);

        String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
        int[] images = {R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
                R.drawable.dog, R.drawable.cat, R.drawable.elephant};

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", names[i]);
            item.put("icon", images[i]);
            data.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_animal,
                new String[]{"name", "icon"},
                new int[]{R.id.tv_name, R.id.iv_icon});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String name = String.valueOf(item.get("name"));
                Toast.makeText(ListViewActivity.this, "你选中了：" + name, Toast.LENGTH_SHORT).show();
                showNotification(name);
            }
        });
    }

    private void showNotification(String name) {
        pendingTitle = name;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQ_NOTIFICATION);
            return;
        }
        notifyNow(name);
    }

    private void notifyNow(String name) {
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "实验三列表通知", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("点击列表项后发送的通知");
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(name)
                .setContentText("你刚刚在动物列表里点击了 " + name)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        manager.notify(1, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_NOTIFICATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (pendingTitle != null) {
                    notifyNow(pendingTitle);
                }
            } else {
                Toast.makeText(this, "没有通知权限，只显示 Toast", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.fjnu.componentlab;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4. 上下文操作模式（ActionMode）：在 ListView 中长按进入选择模式，
 * 顶部出现“N selected”标题栏和删除按钮，可多选后批量删除。
 */
public class ActionModeActivity extends AppCompatActivity {

    private final List<String> data = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five"));

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);
        setTitle("ActionMode 上下文菜单");

        ListView listView = findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this, R.layout.item_action_mode, R.id.tv_name, data) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView icon = view.findViewById(R.id.iv_android);
                icon.setImageResource(R.drawable.ic_android_item);
                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(getCheckedCount(listView) + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    int removed = 0;
                    for (int i = data.size() - 1; i >= 0; i--) {
                        if (listView.getCheckedItemPositions().get(i)) {
                            data.remove(i);
                            removed++;
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ActionModeActivity.this, "已删除 " + removed + " 项", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                listView.clearChoices();
            }
        });
    }

    private int getCheckedCount(ListView listView) {
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (listView.getCheckedItemPositions().get(i)) {
                count++;
            }
        }
        return count;
    }
}

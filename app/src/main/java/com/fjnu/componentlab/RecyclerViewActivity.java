package com.fjnu.componentlab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 5. RecyclerView + Adapter + ViewHolder：动物列表，可点击、可新增。
 * 新增记录后调用 notifyItemInserted，列表立即更新并滚动到新记录，验证无错位。
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private final List<Animal> animals = new ArrayList<>();
    private AnimalAdapter adapter;
    private RecyclerView recyclerView;
    private int addedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setTitle("RecyclerView");

        animals.add(new Animal("Lion", R.drawable.lion));
        animals.add(new Animal("Tiger", R.drawable.tiger));
        animals.add(new Animal("Monkey", R.drawable.monkey));
        animals.add(new Animal("Dog", R.drawable.dog));
        animals.add(new Animal("Cat", R.drawable.cat));
        animals.add(new Animal("Elephant", R.drawable.elephant));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalAdapter(animals);
        recyclerView.setAdapter(adapter);

        Button add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedCount++;
                animals.add(new Animal("新增动物 " + addedCount, R.drawable.cat));
                adapter.notifyItemInserted(animals.size() - 1);
                recyclerView.scrollToPosition(animals.size() - 1);
                Toast.makeText(RecyclerViewActivity.this, "已新增一条记录", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Animal {
        final String name;
        final int imageRes;

        Animal(String name, int imageRes) {
            this.name = name;
            this.imageRes = imageRes;
        }
    }

    class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

        private final List<Animal> data;

        AnimalAdapter(List<Animal> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_animal, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Animal animal = data.get(position);
            holder.name.setText(animal.name);
            holder.icon.setImageResource(animal.imageRes);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecyclerViewActivity.this, animal.name, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView name;
            final ImageView icon;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tv_name);
                icon = itemView.findViewById(R.id.iv_icon);
            }
        }
    }
}

package net.idik.lib.slimadapter.example;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final List<Object> data = new ArrayList<>();

    public static class Food<T> {
        T food;

        public Food(T food) {
            this.food = food;
        }
    }

    static {
        List<String> list = new ArrayList<>();
        list.add("11111");
        list.add("11111");
        list.add("222222");

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1212);
        intList.add(222);

        data.add("hello");
        data.add(",");
        data.add(new User("iDIK", 27));
        data.add("world");
        data.add("!");
        data.add(666666);
        data.add(intList);
        data.add(new Food("foodStr"));
        data.add(34234);
        data.add(666669L);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SlimAdapter.create()
                .register(R.layout.item_user, new SlimInjector<User>() {
                    @Override
                    public void onInject(User data, IViewInjector injector) {
                        injector.text(R.id.name, data.getName())
                                .text(R.id.age, String.valueOf(data.getAge()))
                                .textColor(R.id.age, Color.RED)
                                .textSize(R.id.age, 8);
                    }
                })
                .register(R.layout.item_string, new SlimInjector<Food>() {
                    @Override
                    public void onInject(Food data, IViewInjector injector) {
                        injector.text(R.id.text, data.food.toString());
                    }
                })
                .register(R.layout.item_string, new SlimInjector<List>() {

                    @Override
                    public void onInject(List data, IViewInjector injector) {
                        injector.text(R.id.text, data.get(1).toString());
                    }
                })
                .register(R.layout.item_interger, new SlimInjector<Integer>() {
                    @Override
                    public void onInject(Integer data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString());

                    }
                })
                .register(R.layout.item_string, new SlimInjector<String>() {
                    @Override
                    public void onInject(String data, IViewInjector injector) {
                        injector.text(R.id.text, data);
                    }
                })
                .registerDefault(R.layout.item_string, new SlimInjector() {
                    @Override
                    public void onInject(Object data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString())
                                .longClicked(R.id.text, new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        return false;
                                    }
                                })
                                .with(R.id.text, new IViewInjector.Action<TextView>() {
                                    @Override
                                    public void action(TextView view) {

                                    }
                                })
                                .clicked(R.id.text, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "DEFAULT INJECT", Toast.LENGTH_LONG).show();
                                    }
                                });


                    }
                })
                .attachTo(recyclerView)
                .setData(data)
                .notifyDataSetChanged();
    }
}

package net.idik.lib.slimadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final List<Object> data = new ArrayList<>();

    static {
        data.add("hello");
        data.add(",");
        data.add(666666);
        data.add("world");
        data.add("!");
        data.add(666666);
        data.add(666669L);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SlimAdapter.create()
                .withDefault(R.layout.item_string, new SlimInjector() {
                    @Override
                    protected void onInject(Object data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString())
                                .clicked(R.id.text, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "DEFAULT INJECT", Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                })
                .with(R.layout.item_interger, new SlimInjector<Integer>() {
                    @Override
                    protected void onInject(Integer data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString());

                    }
                })
                .with(R.layout.item_interger, new SlimInjector<String>() {
                    @Override
                    protected void onInject(String data, IViewInjector injector) {
                        injector.text(R.id.text, data);
                    }
                })
                .with(R.layout.item_string, new SlimInjector<CharSequence>() {
                    @Override
                    protected void onInject(CharSequence data, IViewInjector injector) {
                        injector.text(R.id.text, data);
                    }
                })
                .attachTo(recyclerView)
                .setData(data)
                .notifyDataSetChanged();
    }
}

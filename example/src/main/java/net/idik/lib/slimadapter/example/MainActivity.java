package net.idik.lib.slimadapter.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final List<Object> data = new ArrayList<>();

    static {
        data.add(new SectionHeader("My Friends"));
        data.add(new User("Jack", 21, R.drawable.icon1, "123456789XX"));
        data.add(new User("Marry", 17, R.drawable.icon2, "123456789XX"));
        data.add(new SectionHeader("My Images"));
        data.add(new Image(R.drawable.cover1));
        data.add(new Image(R.drawable.cover2));
        data.add(new Image(R.drawable.cover3));
        data.add(new Image(R.drawable.cover4));
        data.add(new Image(R.drawable.cover5));
        data.add(new Image(R.drawable.cover6));
        data.add(new Image(R.drawable.cover7));
        data.add(new Image(R.drawable.cover8));
        data.add(new Image(R.drawable.cover9));
        data.add(new Image(R.drawable.cover10));
        data.add(new Image(R.drawable.cover11));
        data.add(new SectionHeader("My Musics"));
        data.add(new Music("Love story", R.drawable.icon3));
        data.add(new Music("Nothing's gonna change my love for u", R.drawable.icon4));
        data.add(new Music("Just one last dance", R.drawable.icon5));
    }

    private SlimAdapter slimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return slimAdapter.getItem(position) instanceof Image ? 1 : 3;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        slimAdapter = SlimAdapter.create()
                .register(R.layout.item_user, new SlimInjector<User>() {
                    @Override
                    public void onInject(User data, IViewInjector injector) {
                        injector.text(R.id.name, data.getName())
                                .image(R.id.avatar, data.getAvatarRes())
                                .text(R.id.phone, data.getPhone())
                                .textColor(R.id.phone, Color.RED)
                                .textSize(R.id.phone, 12);
                    }
                })
                .register(R.layout.item_setion_header, new SlimInjector<SectionHeader>() {
                    @Override
                    public void onInject(SectionHeader data, IViewInjector injector) {
                        injector.text(R.id.section_title, data.getTitle());
                    }
                })
                .register(R.layout.item_image, new SlimInjector<Image>() {
                    @Override
                    public void onInject(final Image data, IViewInjector injector) {
                        injector.with(R.id.imageView, new IViewInjector.Action<ImageView>() {
                            @Override
                            public void action(ImageView view) {
                                Glide.with(MainActivity.this).load(data.getRes()).into(view);
                            }
                        });
                    }
                })
                .register(R.layout.item_music, new SlimInjector<Music>() {
                    @Override
                    public void onInject(Music data, IViewInjector injector) {
                        injector.text(R.id.name, data.getName())
                                .image(R.id.cover, data.getCoverRes());
                    }
                })
                .attachTo(recyclerView);

        slimAdapter.setData(data).notifyDataSetChanged();
    }
}

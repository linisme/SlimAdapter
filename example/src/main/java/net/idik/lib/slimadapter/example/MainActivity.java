package net.idik.lib.slimadapter.example;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.SlimAdapterEx;
import net.idik.lib.slimadapter.ex.loadmore.SlimMoreLoader;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final List<Object> data = new ArrayList<>();
    private static final List<Object> data1 = new ArrayList<>();

    private List<Object> currentData = null;

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

        data1.addAll(data);
        data1.remove(1);
        data1.remove(5);
        data1.remove(6);
    }

    private SlimAdapter slimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentData = new ArrayList<>(data);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return slimAdapter.getItem(position) instanceof Image ? 1 : 3;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        slimAdapter = SlimAdapter.create(SlimAdapterEx.class)
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
                .enableDiff()
                .enableLoadMore(new SlimMoreLoader(this) {
                    @Override
                    protected void onLoadMore(Handler handler) {
                        SystemClock.sleep(3_000L);
                        if (random.nextInt(10) > 7) {
                            handler.error();
                        } else {
                            handler.loadCompleted(data1);
                            loadTime++;
                        }
                    }

                    @Override
                    protected boolean hasMore() {
                        return loadTime < 3;
                    }
                })
                .attachTo(recyclerView);

        slimAdapter.updateData(currentData);
    }

    private Random random = new Random(System.currentTimeMillis());
    private int loadTime = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_data:
                loadTime = 0;
                currentData = currentData == data ? new ArrayList<>(data1) : new ArrayList<>(data);
                slimAdapter.updateData(currentData);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

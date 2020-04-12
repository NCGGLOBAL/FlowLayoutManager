package com.xiaofeng.androidlibs;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xiaofeng.flowlayoutmanager.Alignment;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FlowLayoutManager flowLayoutManager;
    RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(5, 5, 5, 5);
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_left:
                    flowLayoutManager = new FlowLayoutManager().setAlignment(Alignment.LEFT);
                    flowLayoutManager.setAutoMeasureEnabled(true);

                    recyclerView.setLayoutManager(flowLayoutManager);
                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

                    return true;
                case R.id.navigation_center:
                    flowLayoutManager = new FlowLayoutManager().setAlignment(Alignment.CENTER);
                    flowLayoutManager.setAutoMeasureEnabled(true);

                    recyclerView.setLayoutManager(flowLayoutManager);
                    recyclerView.getAdapter().notifyDataSetChanged();

                    return true;
                case R.id.navigation_rigth:
                    flowLayoutManager = new FlowLayoutManager().setAlignment(Alignment.RIGHT);
                    flowLayoutManager.setAutoMeasureEnabled(true);

                    recyclerView.setLayoutManager(flowLayoutManager);
                    recyclerView.getAdapter().notifyDataSetChanged();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        recyclerView = findViewById(R.id.list);
        flowLayoutManager = new FlowLayoutManager().setAlignment(Alignment.LEFT);
        flowLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(flowLayoutManager);
        recyclerView.setAdapter(new DemoAdapter(DemoUtil.listWords()));
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addItemDecoration(itemDecoration);

    }
}

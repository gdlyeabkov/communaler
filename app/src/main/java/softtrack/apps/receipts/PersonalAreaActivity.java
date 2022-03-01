package softtrack.apps.receipts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class PersonalAreaActivity extends AppCompatActivity {

    public static ViewPager2 currentTab;
    public TabLayout mainTabs;
    public int userId;
    public static PersonalAreaActivity gateway;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        initialize();
    }

    public void initialize() {

        gateway = PersonalAreaActivity.this;

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        userId = extras.getInt("userId");

        mainTabs = findViewById(R.id.mainTabs);
        currentTab = findViewById(R.id.currentTab);

        FragmentManager fm = getSupportFragmentManager();
        ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
        currentTab.setAdapter(sa);
        mainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        currentTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
            // здесь можно инициализировать код при входе на вкладку

            }
        });

    }

}

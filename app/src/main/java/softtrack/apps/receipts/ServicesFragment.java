package softtrack.apps.receipts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class ServicesFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public TabLayout servicesMainTabs;
    public ViewPager2 servicesCurrentTab;

    public ServicesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_services, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        servicesMainTabs = parentActivity.findViewById(R.id.activity_services_main_tabs);
        servicesCurrentTab = parentActivity.findViewById(R.id.activity_services_current_tab);

        FragmentManager fm = parentActivity.getSupportFragmentManager();
        ServicesViewStateAdapter sa = new ServicesViewStateAdapter(fm, getLifecycle());
        servicesCurrentTab.setAdapter(sa);
        servicesMainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                servicesCurrentTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

}

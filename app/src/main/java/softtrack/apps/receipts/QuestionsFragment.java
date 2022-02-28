package softtrack.apps.receipts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class QuestionsFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public TabLayout questionsMainTabs;
    public ViewPager2 questionsCurrentTab;

    public QuestionsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_questions, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        questionsMainTabs = parentActivity.findViewById(R.id.questions_main_tabs);
        questionsCurrentTab = parentActivity.findViewById(R.id.questions_current_tab);

        FragmentManager fm = parentActivity.getSupportFragmentManager();
        QuestionsViewStateAdapter sa = new QuestionsViewStateAdapter(fm, getLifecycle());
        questionsCurrentTab.setAdapter(sa);
        questionsMainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                questionsCurrentTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        questionsCurrentTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

            }
        });
    }

}

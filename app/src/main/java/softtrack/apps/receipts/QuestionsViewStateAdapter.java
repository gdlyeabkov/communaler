package softtrack.apps.receipts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionsViewStateAdapter extends FragmentStateAdapter {

    public QuestionsViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        if (position == 0) {
            return new AskQuestionFragment();
        } else if (position == 1) {
            return new HistoryFragment();
        }
        return new AskQuestionFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}

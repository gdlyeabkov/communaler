package softtrack.apps.receipts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ServicesViewStateAdapter extends FragmentStateAdapter {

    public ServicesViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        if (position == 0) {
            return new ServicesCatalogFragment();
        } else if (position == 1) {
            return new OrderedServicesFragment();
        }
        return new ServicesCatalogFragment();
    }

    @Override
    public int getItemCount() {
        // Hardcoded, use lists
        return 2;
    }

}

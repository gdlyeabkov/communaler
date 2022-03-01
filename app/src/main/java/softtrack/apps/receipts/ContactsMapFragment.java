package softtrack.apps.receipts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactsMapFragment extends Fragment {

    ContactsMapFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contacts_map, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    public void initialize() {

    }

}

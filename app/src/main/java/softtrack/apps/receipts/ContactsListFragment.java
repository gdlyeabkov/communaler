package softtrack.apps.receipts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactsListFragment extends Fragment {

    public ContactsActivity parentActivity;
    public Spinner activityContactsListContainerProvider;
    public LinearLayout activityContactsListContainerBodyFirstCompany;
    public LinearLayout activityContactsListContainerBodySecondCompany;
    public int visible;
    public int unvisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contacts_list, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initalize();

    }

    public void initalize() {
        parentActivity = (ContactsActivity) getActivity();
        activityContactsListContainerProvider = parentActivity.findViewById(R.id.activity_contacts_list_container_provider);
        activityContactsListContainerBodyFirstCompany = parentActivity.findViewById(R.id.activity_contacts_list_container_body_first_company);
        activityContactsListContainerBodySecondCompany = parentActivity.findViewById(R.id.activity_contacts_list_container_body_second_company);
        visible = View.VISIBLE;
        unvisible = View.GONE;
        ArrayAdapter adapter = ArrayAdapter.createFromResource(parentActivity, R.array.contacts_list_provider_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityContactsListContainerProvider.setAdapter(adapter);
        activityContactsListContainerProvider.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int index = activityContactsListContainerProvider.getSelectedItemPosition();
                if (index == 0) {
                    activityContactsListContainerBodyFirstCompany.setVisibility(visible);
                    activityContactsListContainerBodySecondCompany.setVisibility(visible);
                } else if (index == 1) {
                    activityContactsListContainerBodyFirstCompany.setVisibility(visible);
                    activityContactsListContainerBodySecondCompany.setVisibility(unvisible);
                } else if (index == 2) {
                    activityContactsListContainerBodyFirstCompany.setVisibility(unvisible);
                    activityContactsListContainerBodySecondCompany.setVisibility(visible);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

}

package softtrack.apps.receipts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OrderedServicesFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public Spinner activityOrderedServicesContainerAmountNumberField;
    public Spinner activityOrderedServicesContainerPeriodField;

    OrderedServicesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ordered_services, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        activityOrderedServicesContainerAmountNumberField = parentActivity.findViewById(R.id.activity_ordered_services_container_amount_number_field);
        activityOrderedServicesContainerPeriodField = parentActivity.findViewById(R.id.activity_ordered_services_container_period_field);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(parentActivity, R.array.ordered_services_amount_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityOrderedServicesContainerAmountNumberField.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(parentActivity, R.array.ordered_services_period_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityOrderedServicesContainerPeriodField.setAdapter(adapter);
    }

}

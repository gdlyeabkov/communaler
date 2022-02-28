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

public class ServicesCatalogFragment extends Fragment {

    public Spinner activityServicesCatalogContainerAmountNumberField;
    public Spinner activityServicesCatalogContainerTypeField;
    public PersonalAreaActivity parentActivity;

    ServicesCatalogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_services_catalog, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        activityServicesCatalogContainerAmountNumberField = parentActivity.findViewById(R.id.activity_services_catalog_container_amount_number_field);
        activityServicesCatalogContainerTypeField = parentActivity.findViewById(R.id.activity_services_catalog_container_type_field);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(parentActivity,
                R.array.services_catalog_amount_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityServicesCatalogContainerAmountNumberField.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(parentActivity,
                R.array.services_catalog_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityServicesCatalogContainerTypeField.setAdapter(adapter);
    }

}
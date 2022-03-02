package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoreFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public LinearLayout activityMoreContainerProfile;
    public LinearLayout activityMoreContainerContacts;
    public LinearLayout activityMoreContainerPaymentsAndTransfers;
    public int userId = 0;

    public MoreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_more, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        userId = parentActivity.gateway.userId;
        activityMoreContainerProfile = parentActivity.findViewById(R.id.activity_more_container_profile);
        activityMoreContainerContacts = parentActivity.findViewById(R.id.activity_more_container_contacts);
        activityMoreContainerPaymentsAndTransfers = parentActivity.findViewById(R.id.activity_more_container_payments_and_services);
        activityMoreContainerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, ProfileActivity.class);
                intent.putExtra("userId", userId);
                parentActivity.startActivity(intent);
            }
        });
        activityMoreContainerContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, ContactsActivity.class);
                intent.putExtra("userId", userId);
                parentActivity.startActivity(intent);
            }
        });
        activityMoreContainerPaymentsAndTransfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, PaymentsAndTransfersActivity.class);
                intent.putExtra("userId", userId);
                parentActivity.startActivity(intent);
            }
        });
    }

}

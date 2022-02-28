package softtrack.apps.receipts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainPageFragment extends Fragment {

    public LinearLayout activityMainPageContainerBodyActionsTransfer;
    PersonalAreaActivity parentActivity;

    public MainPageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        activityMainPageContainerBodyActionsTransfer = parentActivity.findViewById(R.id.activity_main_page_container_body_actions_transfer);
        activityMainPageContainerBodyActionsTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, TransferActivity.class);
                parentActivity.startActivity(intent);
            }
        });
    }

}

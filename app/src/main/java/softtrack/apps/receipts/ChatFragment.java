package softtrack.apps.receipts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatFragment extends Fragment {


    public PersonalAreaActivity parentActivity;
    public ImageView activityChatContainerInputWrapSendBtn;
    public EditText activityChatContainerInput;
    public LinearLayout activityChatContainerScollBody;

    public ChatFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        activityChatContainerInputWrapSendBtn = parentActivity.findViewById(R.id.activity_chat_container_input_wrap_send_btn);
        activityChatContainerInput = parentActivity.findViewById(R.id.activity_chat_container_input);
        activityChatContainerScollBody = parentActivity.findViewById(R.id.activity_chat_container_scoll_body);
        activityChatContainerInputWrapSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawMessage = activityChatContainerInput.getText();
                String message = rawMessage.toString();
                LinearLayout msgWrap = new LinearLayout(parentActivity);
                msgWrap.setBackgroundColor(Color.rgb(255, 255, 255));
                LinearLayout.LayoutParams msgWrapLayoutParams = new LinearLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT);
                msgWrapLayoutParams.setMargins(15, 15, 0, 15);
                msgWrap.setLayoutParams(msgWrapLayoutParams);
                msgWrap.setOrientation(LinearLayout.HORIZONTAL);
                TextView msg = new TextView(parentActivity);
                LinearLayout.LayoutParams msgLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                msgLayoutParams.setMargins(15, 15, 15, 15);
                msg.setLayoutParams(msgLayoutParams);
                msgWrap.addView(msg);
                msg.setText(message);
                activityChatContainerScollBody.addView(msgWrap);
                activityChatContainerInput.setText("");
            }
        });
    }

}

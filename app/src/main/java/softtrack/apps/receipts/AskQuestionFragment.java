package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AskQuestionFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public Spinner activityAskQuestionContainerProvider;
    public Spinner activityAskQuestionContainerAmountNumber;
    public Spinner activityAskQuestionContainerTopic;
    public ArrayList<String> amountsNumbers;
    public SQLiteDatabase db;

    AskQuestionFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ask_question, container, false);
        return view;
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        activityAskQuestionContainerProvider = parentActivity.findViewById(R.id.activity_ask_question_container_provider);
        activityAskQuestionContainerAmountNumber = parentActivity.findViewById(R.id.activity_ask_question_container_amount_number);
        activityAskQuestionContainerTopic = parentActivity.findViewById(R.id.activity_ask_question_container_topic);
        db = parentActivity.openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(parentActivity,
                R.array.ask_question_provider_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAskQuestionContainerProvider.setAdapter(adapter);
        Cursor amountsCursor = db.rawQuery("Select * from amounts", null);
        amountsCursor.moveToFirst();
        long amounsCount = DatabaseUtils.queryNumEntries(db, "amounts");
        boolean isHaveAmounts = amounsCount >= 1;
        if (isHaveAmounts) {
            amountsNumbers = new ArrayList<String>();
            amountsNumbers.add("Лицевой счет");
            for (int i = 0; i < amounsCount; i++) {
                String number = amountsCursor.getString(2);
                amountsNumbers.add(number);
                amountsCursor.moveToNext();
            }
            adapter = new ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, amountsNumbers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            activityAskQuestionContainerAmountNumber.setAdapter(adapter);
        }
    }

}

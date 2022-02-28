package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {

    public PersonalAreaActivity parentActivity;
    public SQLiteDatabase db;
    public LinearLayout activityHistoryContainerAmounts;
    public int amountDividerColor;
    public int amountDividerHeight;
    public int matchParent;
    public int amountHeight;
    public int amountDetailsBtnWidth;

    HistoryFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history, container, false);
        return view;
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        db = parentActivity.openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        activityHistoryContainerAmounts = parentActivity.findViewById(R.id.activity_history_container_amounts);
        amountDividerColor = Color.rgb(0, 0, 0);
        amountDividerHeight = 1;
        matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        amountHeight = 75;
        amountDetailsBtnWidth = 50;
        Cursor amountsCursor = db.rawQuery("Select * from amounts", null);
        amountsCursor.moveToFirst();
        long amounsCount = DatabaseUtils.queryNumEntries(db, "amounts");
        boolean isHaveAmounts = amounsCount >= 1;
        if (isHaveAmounts) {
            for (int i = 0; i < amounsCount; i++) {
                String number = amountsCursor.getString(2);
                LinearLayout amount = new LinearLayout(parentActivity);
                LinearLayout.LayoutParams amountLayoutParams = new LinearLayout.LayoutParams(matchParent, amountHeight);
                amount.setLayoutParams(amountLayoutParams);
                amount.setOrientation(LinearLayout.HORIZONTAL);
                TextView amountNumber = new TextView(parentActivity);
                amountNumber.setText("ЛС №" + number);
                amount.addView(amountNumber);
                ImageView amountDetailsBtn = new ImageView(parentActivity);
                amountDetailsBtn.setImageResource(R.drawable.chevron_right);
                LinearLayout.LayoutParams amountDetailsBtnLayoutParams = new LinearLayout.LayoutParams(amountDetailsBtnWidth, matchParent);
                amountDetailsBtnLayoutParams.setMargins(465, 0, 0, 0);
                amountDetailsBtn.setLayoutParams(amountDetailsBtnLayoutParams);
                amountDetailsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                amount.addView(amountDetailsBtn);
                activityHistoryContainerAmounts.addView(amount);
                View amountDivider = new View(parentActivity);
                LinearLayout.LayoutParams amountDividerLayoutParams = new LinearLayout.LayoutParams(matchParent, amountDividerHeight);
                amountDivider.setLayoutParams(amountDividerLayoutParams);
                ColorDrawable divider = new ColorDrawable();
                divider.setColor(amountDividerColor);
                amountDivider.setBackground(divider);
                activityHistoryContainerAmounts.addView(amountDivider);
                amountsCursor.moveToNext();
            }
        } else {

        }
    }

}

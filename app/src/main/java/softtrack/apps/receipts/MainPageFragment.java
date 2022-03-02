package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class MainPageFragment extends Fragment {

    public LinearLayout activityMainPageContainerBodyActionsPay;
    public LinearLayout activityMainPageContainerBodyActionsTransfer;
    public TabLayout activityMainPageContainerAmounts;
    public PersonalAreaActivity parentActivity;
    public SQLiteDatabase db;
    public LinearLayout activityMainPageContainerBodyAmount;
    public TextView activityMinPageContainerBodyNotFoundAmountsLabel;
    public int visible;
    public int unvisible;
    public TextView activityMainPageContainerBodyDebt;
    public TextView activityMainPageContainerBodyCost;

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

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (PersonalAreaActivity) getActivity();
        db = parentActivity.openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        activityMainPageContainerBodyActionsPay = parentActivity.findViewById(R.id.activity_main_page_container_body_actions_pay);
        activityMainPageContainerBodyActionsTransfer = parentActivity.findViewById(R.id.activity_main_page_container_body_actions_transfer);
        activityMainPageContainerAmounts = parentActivity.findViewById(R.id.activity_main_page_container_amounts);
        activityMainPageContainerBodyAmount = parentActivity.findViewById(R.id.activity_main_page_container_body_amount);
        activityMinPageContainerBodyNotFoundAmountsLabel = parentActivity.findViewById(R.id.activity_main_page_container_body_not_found_amounts_label);
        activityMainPageContainerBodyDebt = parentActivity.findViewById(R.id.activity_main_page_container_body_debt);
        activityMainPageContainerBodyCost = parentActivity.findViewById(R.id.activity_main_page_container_body_cost);
        visible = View.VISIBLE;
        unvisible = View.GONE;
        activityMainPageContainerBodyActionsPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedTabIndex = activityMainPageContainerAmounts.getSelectedTabPosition();
                TabLayout.Tab selectedAmount = activityMainPageContainerAmounts.getTabAt(selectedTabIndex);
                CharSequence amountData = selectedAmount.getContentDescription();
                String rawAmountId = amountData.toString();
                int amountId = Integer.valueOf(rawAmountId);
                Intent intent = new Intent(parentActivity, PaymentActivity.class);
                int userId = parentActivity.gateway.userId;
                intent.putExtra("userId", userId);
                intent.putExtra("amountId", amountId);
                parentActivity.startActivity(intent);
            }
        });
        activityMainPageContainerBodyActionsTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedTabIndex = activityMainPageContainerAmounts.getSelectedTabPosition();
                TabLayout.Tab selectedAmount = activityMainPageContainerAmounts.getTabAt(selectedTabIndex);
                CharSequence amountData = selectedAmount.getContentDescription();
                String rawAmountId = amountData.toString();
                int amountId = Integer.valueOf(rawAmountId);
                Intent intent = new Intent(parentActivity, TransferActivity.class);
                intent.putExtra("amountId", amountId);
                parentActivity.startActivity(intent);
            }
        });
        activityMainPageContainerAmounts.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                checkActiveTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                checkActiveTab(tab);
            }
        });

        int tabsCount = activityMainPageContainerAmounts.getTabCount();
        for (int i = 0; i < tabsCount; i++) {
            boolean isAmountTab = i > 0;
            if (isAmountTab) {
                activityMainPageContainerAmounts.removeTabAt(i);
            }
        }
//        Cursor amountsCursor = db.rawQuery("Select * from amounts", null);
        int userId = parentActivity.gateway.userId;
        Cursor amountsCursor = db.rawQuery("Select * from amounts where user=" + userId, null);
        amountsCursor.moveToFirst();
//        long amounsCount = DatabaseUtils.queryNumEntries(db, "amounts");
        long amounsCount = amountsCursor.getCount();
        boolean isHaveAmounts = amounsCount >= 1;
        if (isHaveAmounts) {
            activityMinPageContainerBodyNotFoundAmountsLabel.setVisibility(unvisible);
            activityMainPageContainerBodyAmount.setVisibility(visible);
            for (int i = 0; i < amounsCount; i++) {
                int amountId = amountsCursor.getInt(0);
                String provider = amountsCursor.getString(1);
                String number = amountsCursor.getString(2);
                String status = amountsCursor.getString(3);
                int email = amountsCursor.getInt(4);
                String dateTime = amountsCursor.getString(5);
                TabLayout.Tab amount = activityMainPageContainerAmounts.newTab();
                String rawAmountId = String.valueOf(amountId);
                amount.setContentDescription(rawAmountId);
                amount.setIcon(R.drawable.amount);
                amount.setText(number);
                activityMainPageContainerAmounts.addTab(amount);
                boolean isActiveAmount = i == 0;
                if (isActiveAmount) {
                    activityMainPageContainerAmounts.selectTab(amount);
                    int cost = amountsCursor.getInt(6);
                    boolean isAmountDebt = cost < 0;
                    if (isAmountDebt) {
                        activityMainPageContainerBodyDebt.setText("Сумма к оплате");
                        activityMainPageContainerBodyCost.setTextColor(Color.rgb(255, 0, 0));
                    }
                    String rawCost = String.valueOf(cost) + " ₽";
                    activityMainPageContainerBodyCost.setText(rawCost);
                }
                amountsCursor.moveToNext();
            }
        }

    }

    public void checkActiveTab(TabLayout.Tab tab) {
        int tabPosition = tab.getPosition();
        boolean isAddAmount = tabPosition == 0;
        if (isAddAmount) {
            Intent intent = new Intent(parentActivity, AddAmountActivity.class);
            intent.putExtra("userId", parentActivity.gateway.userId);
            parentActivity.startActivity(intent);
        } else {
            CharSequence amountData = tab.getContentDescription();
            String amountId = amountData.toString();
            Cursor amountsCursor = db.rawQuery("Select * from amounts where _id = " + amountId, null);
            boolean amountsCursorExists = amountsCursor != null;
            if (amountsCursorExists) {
                amountsCursor.moveToFirst();
                int countAmountsCursorRecords =  amountsCursor.getCount();
                boolean isAmountsCursorRecordsExists = countAmountsCursorRecords >= 1;
                if (isAmountsCursorRecordsExists) {
                    String provider = amountsCursor.getString(1);
                    String number = amountsCursor.getString(2);
                    String status = amountsCursor.getString(3);
                    int email = amountsCursor.getInt(4);
                    String dateTime = amountsCursor.getString(5);
                    int cost = amountsCursor.getInt(6);
                    boolean isAmountDebt = cost < 0;
                    if (isAmountDebt) {
                        activityMainPageContainerBodyDebt.setText("Сумма к оплате");
                        activityMainPageContainerBodyCost.setTextColor(Color.rgb(255, 0, 0));
                    }
                    String rawCost = String.valueOf(cost) + " ₽";
                    activityMainPageContainerBodyCost.setText(rawCost);
                }
            }
        }
    }

}

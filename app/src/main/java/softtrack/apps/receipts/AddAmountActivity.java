package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Random;

public class AddAmountActivity extends AppCompatActivity {

    public SQLiteDatabase db;
    public Spinner activityAddAmountContainerProviderField;
    public EditText activityAddAmountContainerAmountNumberField;
    public Spinner activityAddAmountContainerRelationStatusField;
    public Button activityAddAmountContainerAddBtn;
    public Switch activityAddAmountContainerReceiveAmountsDrivenEmailSwitch;
    public int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        userId = extras.getInt("userId");

        db = openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        activityAddAmountContainerProviderField = findViewById(R.id.activity_add_amount_container_provider_field);
        activityAddAmountContainerAmountNumberField = findViewById(R.id.activity_add_amount_container_amount_number_field);
        activityAddAmountContainerRelationStatusField = findViewById(R.id.activity_add_amount_container_relation_status_field);
        activityAddAmountContainerAddBtn = findViewById(R.id.activity_add_amount_container_add_btn);
        activityAddAmountContainerReceiveAmountsDrivenEmailSwitch = findViewById(R.id.activity_add_amount_container_receive_amounts_driven_email_switch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.providers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddAmountContainerProviderField.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this,
            R.array.relation_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddAmountContainerRelationStatusField.setAdapter(adapter);
        activityAddAmountContainerAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                Object selectedProvider = activityAddAmountContainerProviderField.getSelectedItem();
                String providerName = selectedProvider.toString();
                Object selectedStatus = activityAddAmountContainerRelationStatusField.getSelectedItem();
                String statusName = selectedStatus.toString();
                String rawDay = String.valueOf(day);
                int rawDayLength = rawDay.length();
                boolean isRawDayLengthNotCorrect = rawDayLength == 1;
                if (isRawDayLengthNotCorrect) {
                    rawDay = "0" + rawDay;
                }
                String rawMonth = String.valueOf(month + 1);
                int rawMonthLength = rawMonth.length();
                boolean isRawMonthLengthNotCorrect = rawMonthLength == 1;
                if (isRawMonthLengthNotCorrect) {
                    rawMonth = "0" + rawMonth;
                }
                String rawYear = String.valueOf(year);
                String rawHours = String.valueOf(hours);
                int rawHoursLength = rawHours.length();
                boolean isRawHoursLengthNotCorrect = rawHoursLength == 1;
                if (isRawHoursLengthNotCorrect) {
                    rawHours = "0" + rawHours;
                }
                String rawMinutes = String.valueOf(minutes);
                int rawMinutesLength = rawMinutes.length();
                boolean isRawMinutesLengthNotCorrect = rawMinutesLength == 1;
                if (isRawMinutesLengthNotCorrect) {
                    rawMinutes = "0" + rawMinutes;
                }
                String amountDate = rawDay + "." + rawMonth + "." + rawYear + "T" + rawHours + ":" + rawMinutes;
                boolean isReceiveEmail = activityAddAmountContainerReceiveAmountsDrivenEmailSwitch.isChecked();
                int rawIsReceiveEmail = isReceiveEmail ? 1 : 0;
                CharSequence rawAmountNumber = activityAddAmountContainerAmountNumberField.getText();
                String amountNumber = rawAmountNumber.toString();
                db.execSQL("INSERT INTO \"amounts\"(provider, number, status, email, datetime, cost, user) VALUES (\"" + providerName + "\", \"" + amountNumber + "\", \"" + statusName + "\", " + rawIsReceiveEmail + ", \"" + amountDate + "\", " + new Random().nextInt() + ", " + userId + ");");
                Intent intent = new Intent(AddAmountActivity.this, PersonalAreaActivity.class);
                intent.putExtra("userId", userId);
                AddAmountActivity.this.startActivity(intent);
            }
        });



    }

}

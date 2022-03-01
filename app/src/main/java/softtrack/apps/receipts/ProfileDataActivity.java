package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileDataActivity extends AppCompatActivity {

    public EditText activityProfileDataContainerFirstNameField;
    public EditText activityProfileDataContainerSecondNameField;
    public EditText activityProfileDataContainerThirdNameField;
    public Spinner activityProfileDataContainerBorn;
    public RadioGroup activityProfileDataContainerGender;
    public int userId = 0;
    public String born ="";
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        db = openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            userId = extras.getInt("userId");
        }
        activityProfileDataContainerFirstNameField = findViewById(R.id.activity_profile_data_container_first_name_field);
        activityProfileDataContainerSecondNameField = findViewById(R.id.activity_profile_data_container_second_name_field);
        activityProfileDataContainerThirdNameField = findViewById(R.id.activity_profile_data_container_third_name_field);
        activityProfileDataContainerBorn = findViewById(R.id.activity_profile_data_container_born);
        activityProfileDataContainerGender = findViewById(R.id.activity_profile_data_container_gender);
        ArrayAdapter adapter = new ArrayAdapter(ProfileDataActivity.this, android.R.layout.simple_spinner_item, new String[] { "" });
        activityProfileDataContainerBorn.setAdapter(adapter);
        activityProfileDataContainerBorn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(ProfileDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String month = String.valueOf(monthOfYear + 1);
                        born = dayOfMonth + "." + month + "." + year;
                    }
                }, year, month, day);
                picker.show();
            }

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(ProfileDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String month = String.valueOf(monthOfYear + 1);
                        born = dayOfMonth + "." + month + "." + year;
                    }
                }, year, month, day);
                picker.show();
            }
        });
//        getSupportActionBar().setTitle("a");
//        getActionBar().setTitle("a");

    }

}

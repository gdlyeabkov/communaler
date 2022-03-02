package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfileDataActivity extends AppCompatActivity {

    public EditText activityProfileDataContainerFirstNameField;
    public EditText activityProfileDataContainerSecondNameField;
    public EditText activityProfileDataContainerThirdNameField;
    public Spinner activityProfileDataContainerBorn;
    public RadioGroup activityProfileDataContainerGender;
    public int userId = 0;
    public String born ="";
    public ArrayList<String> monthLabels;
    public SQLiteDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);

        initialize();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        monthLabels = new ArrayList<String>();
        monthLabels.add("января");
        monthLabels.add("февраля");
        monthLabels.add("марта");
        monthLabels.add("апреля");
        monthLabels.add("мая");
        monthLabels.add("июня");
        monthLabels.add("июля");
        monthLabels.add("августа");
        monthLabels.add("сентября");
        monthLabels.add("октября");
        monthLabels.add("ноября");
        monthLabels.add("декабря");
        Cursor usersCursor = db.rawQuery("Select * from users where _id = " + userId, null);
        usersCursor.moveToFirst();
        String gender = usersCursor.getString(7);
        String firstName = usersCursor.getString(8);
        String secondName = usersCursor.getString(9);
        String thirdName = usersCursor.getString(10);
        String dateBorn = usersCursor.getString(11);
        getSupportActionBar().setTitle("Личные данные");
        if (gender.contains("Мужской")) {
            RadioButton radioButton = findViewById(R.id.activity_profile_data_container_gender_male);
            radioButton.setChecked(true);
        } else if (gender.contains("Женский")) {
            RadioButton radioButton = findViewById(R.id.activity_profile_data_container_gender_female);
            radioButton.setChecked(true);
        }
        activityProfileDataContainerFirstNameField.setText(firstName);
        activityProfileDataContainerSecondNameField.setText(secondName);
        activityProfileDataContainerThirdNameField.setText(thirdName);
        ArrayAdapter adapter = new ArrayAdapter(ProfileDataActivity.this, android.R.layout.simple_spinner_item, new String[] { dateBorn });
        activityProfileDataContainerBorn.setAdapter(adapter);
        activityProfileDataContainerBorn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(ProfileDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String month = monthLabels.get(monthOfYear);
                        born = dayOfMonth + " " + month + " " + year;
                        ArrayAdapter adapter = new ArrayAdapter(ProfileDataActivity.this, android.R.layout.simple_spinner_item, new String[] { born });
                        activityProfileDataContainerBorn.setAdapter(adapter);
                    }
                }, year, month, day);
                picker.show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile_data_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        boolean isSaveMenuItem = itemId == R.id.activity_profile_data_actions_save;
        if (isSaveMenuItem) {
            saveData();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData() {
        CharSequence rawActivityProfileDataContainerFirstNameFieldContent = activityProfileDataContainerFirstNameField.getText();
        String activityProfileDataContainerFirstNameFieldContent = rawActivityProfileDataContainerFirstNameFieldContent.toString();
        CharSequence rawActivityProfileDataContainerSecondNameFieldContent = activityProfileDataContainerSecondNameField.getText();
        String activityProfileDataContainerSecondNameFieldContent = rawActivityProfileDataContainerSecondNameFieldContent.toString();
        CharSequence rawActivityProfileDataContainerThirdNameFieldContent = activityProfileDataContainerThirdNameField.getText();
        String activityProfileDataContainerThirdNameFieldContent = rawActivityProfileDataContainerThirdNameFieldContent.toString();
        Object rawActivityProfileDataContainerBornContent = activityProfileDataContainerBorn.getSelectedItem();
        String activityProfileDataContainerBornContent = rawActivityProfileDataContainerBornContent.toString();
        int rawActivityProfileDataContainerGenderId = activityProfileDataContainerGender.getCheckedRadioButtonId();
        RadioButton rawActivityProfileDataContainerGender  = findViewById(rawActivityProfileDataContainerGenderId);
        CharSequence rawActivityProfileDataContainerGenderContent = rawActivityProfileDataContainerGender.getText();
        String activityProfileDataContainerGenderContent = rawActivityProfileDataContainerGenderContent.toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", activityProfileDataContainerGenderContent);
        contentValues.put("firstname", activityProfileDataContainerFirstNameFieldContent);
        contentValues.put("secondname", activityProfileDataContainerSecondNameFieldContent);
        contentValues.put("thirdname", activityProfileDataContainerThirdNameFieldContent);
        contentValues.put("born", activityProfileDataContainerBornContent);
        db.update("users", contentValues, "_id = ? ", new String[] { Integer.toString(userId) } );
        Intent intent = new Intent(ProfileDataActivity.this, ProfileActivity.class);
        intent.putExtra("userId", userId);
        ProfileDataActivity.this.startActivity(intent);
    }

}

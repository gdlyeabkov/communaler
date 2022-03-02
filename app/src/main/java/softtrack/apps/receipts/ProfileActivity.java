package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    public TextView activityProfileContainerName;
    public TextView activityProfileContainerLogoutLabel;
    public int userId;
    public LinearLayout activityProfileContainerData;
    public LinearLayout activityProfileContainerContacts;
    public LinearLayout activityProfileContainerPassword;
    public LinearLayout activityProfileContainerSecurity;
    public LinearLayout activityProfileContainerAccounts;
    public LinearLayout activityProfileContainerSubs;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            userId = extras.getInt("userId");
        }

        db = openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor usersCursor = db.rawQuery("Select * from users where _id = " + userId, null);
        usersCursor.moveToFirst();
        String name = usersCursor.getString(5);
        activityProfileContainerName = findViewById(R.id.activity_profile_container_name);
        activityProfileContainerLogoutLabel = findViewById(R.id.activity_profile_container_logout_label);
        activityProfileContainerData = findViewById(R.id.activity_profile_container_data);
        activityProfileContainerSubs = findViewById(R.id.activity_profile_container_subs);
        activityProfileContainerSecurity = findViewById(R.id.activity_profile_container_security);
        activityProfileContainerContacts = findViewById(R.id.activity_profile_container_contacts);
        activityProfileContainerAccounts = findViewById(R.id.activity_profile_container_accounts);
        activityProfileContainerPassword = findViewById(R.id.activity_profile_container_password);
        activityProfileContainerName.setText(name);
        activityProfileContainerLogoutLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileDataActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileContactsActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfilePasswordActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileSubsActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileSecurityActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        activityProfileContainerAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileAccountsActivity.class);
                intent.putExtra("userId", userId);
                ProfileActivity.this.startActivity(intent);
            }
        });
        getSupportActionBar().setTitle("Профиль");
    }

}

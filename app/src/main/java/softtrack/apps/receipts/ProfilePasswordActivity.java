package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePasswordActivity extends AppCompatActivity {

    public EditText activityProfilePasswordContainerCurrentPassword;
    public EditText activityProfilePasswordContainerNewPassword;
    public EditText activityProfilePasswordContainerConfirmPassword;
    public Button activityProfilePasswordContainerChangePasswordBtn;
    int userId = 0;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_password);

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
        activityProfilePasswordContainerCurrentPassword = findViewById(R.id.activity_profile_password_container_current_password);
        activityProfilePasswordContainerNewPassword = findViewById(R.id.activity_profile_password_container_new_password);
        activityProfilePasswordContainerConfirmPassword = findViewById(R.id.activity_profile_password_container_confirm_password);
        activityProfilePasswordContainerChangePasswordBtn = findViewById(R.id.activity_profile_password_container_change_password_btn);
        Cursor usersCursor = db.rawQuery("Select * from users where _id = " + userId, null);
        usersCursor.moveToFirst();
        String password = usersCursor.getString(2);
        activityProfilePasswordContainerChangePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawActivityProfilePasswordContainerCurrentPasswordContent = activityProfilePasswordContainerCurrentPassword.getText();
                CharSequence rawActivityProfilePasswordContainerNewPasswordContent = activityProfilePasswordContainerNewPassword.getText();
                CharSequence rawActivityProfilePasswordContainerConfirmPasswordContent = activityProfilePasswordContainerConfirmPassword.getText();
                String activityProfilePasswordContainerCurrentPasswordContent = rawActivityProfilePasswordContainerCurrentPasswordContent.toString();
                String activityProfilePasswordContainerNewPasswordContent = rawActivityProfilePasswordContainerNewPasswordContent.toString();
                String activityProfilePasswordContainerConfirmPasswordContent = rawActivityProfilePasswordContainerConfirmPasswordContent.toString();
                boolean isOldPasswordMatches = activityProfilePasswordContainerCurrentPasswordContent.contains(password) && password.contains(activityProfilePasswordContainerCurrentPasswordContent);
                boolean isNewPasswordsMatches = activityProfilePasswordContainerNewPasswordContent.contains(activityProfilePasswordContainerConfirmPasswordContent) && activityProfilePasswordContainerConfirmPasswordContent.contains(activityProfilePasswordContainerNewPasswordContent);
                boolean isCanUpdatePassword = isOldPasswordMatches && isNewPasswordsMatches;
                if (isCanUpdatePassword) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("password", activityProfilePasswordContainerNewPasswordContent);
                    db.update("users", contentValues, "_id = ? ", new String[] { Integer.toString(userId) } );
                    Intent intent = new Intent(ProfilePasswordActivity.this, ProfileActivity.class);
                    intent.putExtra("userId", userId);
                    ProfilePasswordActivity.this.startActivity(intent);
                } else {

                }
            }
        });
    }

}

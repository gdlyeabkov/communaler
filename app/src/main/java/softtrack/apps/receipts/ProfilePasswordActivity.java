package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
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
                int activityProfilePasswordContainerConfirmPasswordContentLength = activityProfilePasswordContainerConfirmPasswordContent.length();
                boolean isActivityProfilePasswordContainerConfirmPasswordFilled = activityProfilePasswordContainerConfirmPasswordContentLength >= 1;
                int activityProfilePasswordContainerNewPasswordContentLength = activityProfilePasswordContainerNewPasswordContent.length();
                boolean isActivityProfilePasswordContainerNewPasswordFilled = activityProfilePasswordContainerNewPasswordContentLength >= 1;
                int activityProfilePasswordContainerCurrentPasswordContentLength = activityProfilePasswordContainerCurrentPasswordContent.length();
                boolean isActivityProfilePasswordContainerCurrentPasswordFilled = activityProfilePasswordContainerCurrentPasswordContentLength >= 1;
                boolean isFieldsFilled = isActivityProfilePasswordContainerConfirmPasswordFilled && isActivityProfilePasswordContainerNewPasswordFilled && isActivityProfilePasswordContainerCurrentPasswordFilled;
                boolean isCanUpdatePassword = isOldPasswordMatches && isNewPasswordsMatches && isFieldsFilled;
                if (isCanUpdatePassword) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("password", activityProfilePasswordContainerNewPasswordContent);
                    db.update("users", contentValues, "_id = ? ", new String[] { Integer.toString(userId) } );
                    Intent intent = new Intent(ProfilePasswordActivity.this, ProfileActivity.class);
                    intent.putExtra("userId", userId);
                    ProfilePasswordActivity.this.startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfilePasswordActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    builder.setTitle("Сообщение");
                    String msg = "";
                    boolean isOldPasswordNotMatches = !isOldPasswordMatches;
                    if (isOldPasswordNotMatches) {
                        msg += "Поле \"Текущий пароль\" не правильно.\n";
                    }
                    boolean isNewPasswordsNotMatches = !isNewPasswordsMatches;
                    if (isNewPasswordsNotMatches) {
                        msg += "Поля \"Новый пароль\" и  \"Повторите пароль\" не совпадают.\n";
                    }
                    boolean isFieldsNotFilled = !isFieldsFilled;
                    if (isFieldsNotFilled) {
                        boolean isCurrentPasswordNotFilled = !isActivityProfilePasswordContainerCurrentPasswordFilled;
                        if (isCurrentPasswordNotFilled) {
                            msg += "Поля \"Текущий пароль\" не заполнено.\n";
                        }
                        boolean isNewPasswordNotFilled = !isActivityProfilePasswordContainerNewPasswordFilled;
                        if (isNewPasswordNotFilled) {
                            msg += "Поля \"Новый пароль\" не заполнено.\n";
                        }
                        boolean isConfirmPasswordNotFilled = !isActivityProfilePasswordContainerConfirmPasswordFilled;
                        if (isConfirmPasswordNotFilled) {
                            msg += "Поля \"Повторите пароль\" не заполнено.\n";
                        }
                    }
                    builder.setMessage(msg);
                    builder.setCancelable(true);
                    builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        getSupportActionBar().setTitle("Изменить пароль");
    }

}

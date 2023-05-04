package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public TextView activityRegisterContainerNextBtn;
    public EditText activityRegisterContainerEmailField;
    public EditText activityRegisterContainerPhoneField;
    public EditText activityRegisterContainerPasswordField;
    public EditText activityRegisterContainerConfirmPasswordField;
    public EditText activityRegisterContainerNameField;
    public Switch activityRegisterContainerSubscriptionSwitch;
    public TextView activityRegisterContainerHelp;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        db = openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        activityRegisterContainerEmailField = findViewById(R.id.activity_register_container_email_field);
        activityRegisterContainerPhoneField = findViewById(R.id.activity_register_container_phone_field);
        activityRegisterContainerPasswordField = findViewById(R.id.activity_register_container_passwordl_field);
        activityRegisterContainerConfirmPasswordField = findViewById(R.id.activity_register_container_confirm_password_field);
        activityRegisterContainerNameField = findViewById(R.id.activity_register_container_name_field);
        activityRegisterContainerSubscriptionSwitch = findViewById(R.id.activity_register_container_subscription_switch);
        activityRegisterContainerNextBtn = findViewById(R.id.activity_register_container_next_btn);
        activityRegisterContainerHelp = findViewById(R.id.activity_register_container_help);
        activityRegisterContainerHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setMessage("Очень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения\nОчень длинное содержимое соглашения");
                builder.setCancelable(true);
                builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        activityRegisterContainerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence activityRegisterDialogContainerLoginFieldContent = activityRegisterContainerEmailField.getText();
                String loginFieldContent = activityRegisterDialogContainerLoginFieldContent.toString();
                CharSequence activityRegisterDialogContainerPasswordFieldConent = activityRegisterContainerPasswordField.getText();
                String passwordFieldContent = activityRegisterDialogContainerPasswordFieldConent.toString();
                CharSequence activityRegisterContainerConfirmPasswordFieldContent = activityRegisterContainerConfirmPasswordField.getText();
                String confirmPasswordFieldContent = activityRegisterContainerConfirmPasswordFieldContent.toString();
                CharSequence activityRegisterContainerPhoneFieldContent = activityRegisterContainerPhoneField.getText();
                String phoneFieldContent = activityRegisterContainerPhoneFieldContent.toString();
                CharSequence activityRegisterContainerNameFieldContent = activityRegisterContainerNameField.getText();
                String nameFieldContent = activityRegisterContainerNameFieldContent.toString();
                boolean isEmailSubscription = activityRegisterContainerSubscriptionSwitch.isChecked();
                int rawIsEmailSubscription = isEmailSubscription ? 1 : 0;
                boolean isPaswordCompared = passwordFieldContent.contains(confirmPasswordFieldContent) && confirmPasswordFieldContent.contains(passwordFieldContent);
                boolean isFieldsFilled = loginFieldContent.length() >= 1 && phoneFieldContent.length() >= 1 && passwordFieldContent.length() >= 1 && confirmPasswordFieldContent.length() >= 1 && nameFieldContent.length() >= 1;
                boolean isCanRegister = isPaswordCompared && isFieldsFilled;
                if (isCanRegister) {
                    db.execSQL("INSERT INTO \"users\"(login, password, address, phone, name, email, gender, firstname, secondname, thirdname, born) VALUES (\"" + loginFieldContent + "\", \"" + passwordFieldContent + "\", \"\", \"" + phoneFieldContent + "\", \"" + nameFieldContent + "\", " + rawIsEmailSubscription + ", \"\", \"\", \"\", \"\", \"\");");
                    Cursor usersCursor = db.rawQuery("Select * from users", null);
                    usersCursor.moveToLast();
                    int userId = usersCursor.getInt(0);
                    Intent intent = new Intent(RegisterActivity.this, PersonalAreaActivity.class);
                    intent.putExtra("userId", userId);
                    RegisterActivity.this.startActivity(intent);
                } else if (!isFieldsFilled) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    builder.setTitle("Сообщение");
                    String msg = "";
                    boolean isEmailFieldNotFilled = loginFieldContent.length() <= 0;
                    if (isEmailFieldNotFilled) {
                        msg += "Поле \"E-mail\" не заполнено.\n";
                    }
                    boolean isPhoneFieldNotFilled = phoneFieldContent.length() <= 0;
                    if (isPhoneFieldNotFilled) {
                        msg += "Поле \"Мобильный телефон\" не заполнено.\n";
                    }
                    boolean isPasswordFieldNotFilled = passwordFieldContent.length() <= 0;
                    if (isPasswordFieldNotFilled) {
                        msg += "Поле \"Придумайте пароль\" не заполнено.\n";
                    }
                    boolean isConfirmPasswordFieldNotFilled = confirmPasswordFieldContent.length() <= 0;
                    if (isConfirmPasswordFieldNotFilled) {
                        msg += "Поле \"Повторите пароль\" не заполнено.\n";
                    }
                    boolean isNameFieldNotFilled = nameFieldContent.length() <= 0;
                    if (isNameFieldNotFilled) {
                        msg += "Поле \"Имя пользователя\" не заполнено.";
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
    }

}

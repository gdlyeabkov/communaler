package softtrack.apps.receipts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button activityMainContainerLoginBtn;
    public EditText activityMainContainerLoginField;
    public TextView activityMainContainerLoginErrors;
    public EditText activityMainContainerPasswordField;
    public TextView activityMainContainerPasswordErrors;
    public TextView activityMainContainerNotAccountRowRegisterLabel;
    public Button activityMainContainerLoginDrivenLabel;
    public TextView activityMainContainerSitesLabel;
    public LinearLayout activityMainContainerContactsRowContacts;
    public LinearLayout activityMainContainerHelpRowSupport;
    public SQLiteDatabase db;
    public int visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        db = openOrCreateDatabase("communaler.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS amounts (_id INTEGER PRIMARY KEY AUTOINCREMENT, provider TEXT, number TEXT, status TEXT, email INTEGER, datetime TEXT, cost INTEGER, user INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS users (_id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, address TEXT);");
        activityMainContainerLoginBtn = findViewById(R.id.activity_main_container_login_btn);
        activityMainContainerLoginField = findViewById(R.id.activity_main_container_login_field);
        activityMainContainerLoginErrors = findViewById(R.id.activity_main_container_login_errors);
        activityMainContainerPasswordField = findViewById(R.id.activity_main_container_password_field);
        activityMainContainerPasswordErrors = findViewById(R.id.activity_main_container_password_errors);
        activityMainContainerNotAccountRowRegisterLabel = findViewById(R.id.activity_main_container_not_account_row_register_label);
        activityMainContainerLoginDrivenLabel = findViewById(R.id.activity_main_container_login_driven_label);
        activityMainContainerSitesLabel = findViewById(R.id.activity_main_container_sites_label);
        activityMainContainerContactsRowContacts = findViewById(R.id.activity_main_container_help_row_contacts);
        activityMainContainerHelpRowSupport = findViewById(R.id.activity_main_container_help_row_support);
        visible = View.VISIBLE;
        activityMainContainerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawLoginFieldContent = activityMainContainerLoginField.getText();
                String loginFieldContent = rawLoginFieldContent.toString();
                int loginFieldContentLength = loginFieldContent.length();
                boolean isShowLoginFieldErrors = loginFieldContentLength <= 0;
                if (isShowLoginFieldErrors) {
                    activityMainContainerLoginErrors.setVisibility(visible);
                }
                CharSequence rawPasswordFieldContent = activityMainContainerPasswordField.getText();
                String passwordFieldContent = rawPasswordFieldContent.toString();
                int passwordFieldContentLength = passwordFieldContent.length();
                boolean isShowPasswordFieldErrors = passwordFieldContentLength <= 0;
                if (isShowPasswordFieldErrors) {
                    activityMainContainerPasswordErrors.setVisibility(visible);
                }

                Cursor usersCursor = db.rawQuery("Select * from users", null);
                usersCursor.moveToFirst();
                int countUsers = usersCursor.getCount();
                boolean isHaveUsers = countUsers >= 1;
                boolean isLogin = false;
                int userId = 0;
                if (isHaveUsers) {
                    for (int i = 0; i < countUsers; i++) {
                        int id = usersCursor.getInt(0);
                        String login = usersCursor.getString(1);
                        String password = usersCursor.getString(2);
                        if (password.contains(passwordFieldContent) && login.contains(loginFieldContent)) {
                            isLogin = true;
                            userId = id;
                        }
                        usersCursor.moveToNext();
                    }
                } else {
                    isLogin = false;
                }
                boolean isNotErrors = !isShowLoginFieldErrors && !isShowPasswordFieldErrors;
                boolean isCanLogin = isLogin && isNotErrors;
                if (isCanLogin) {
                    Intent intent = new Intent(MainActivity.this, PersonalAreaActivity.class);
                    intent.putExtra("userId", userId);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
        activityMainContainerNotAccountRowRegisterLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_register_dialog, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.setPositiveButton("Зарегестрироваться", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText activityRegisterDialogContainerLoginField = dialogView.findViewById(R.id.activity_register_dialog_container_login_field);
                        CharSequence activityRegisterDialogContainerLoginFieldContent = activityRegisterDialogContainerLoginField.getText();
                        String loginFieldContent = activityRegisterDialogContainerLoginFieldContent.toString();
                        EditText activityRegisterDialogContainerPasswordField = dialogView.findViewById(R.id.activity_register_dialog_container_password_field);
                        CharSequence activityRegisterDialogContainerPasswordFieldConent = activityRegisterDialogContainerPasswordField.getText();
                        String passwordFieldContent = activityRegisterDialogContainerPasswordFieldConent.toString();
                        EditText activityRegisterDialogContainerAddressField = dialogView.findViewById(R.id.activity_register_dialog_container_address_field);
                        CharSequence activityRegisterDialogContainerAddressFieldContent = activityRegisterDialogContainerAddressField.getText();
                        String addressFieldContent = activityRegisterDialogContainerAddressFieldContent.toString();
                        db.execSQL("INSERT INTO \"users\"(login, password, address) VALUES (\"" + loginFieldContent + "\", \"" + passwordFieldContent + "\", \"" + addressFieldContent + "\");");
                        Cursor usersCursor = db.rawQuery("Select * from users", null);
                        usersCursor.moveToLast();
                        int userId = usersCursor.getInt(0);
                        Intent intent = new Intent(MainActivity.this, PersonalAreaActivity.class);
                        intent.putExtra("userId", userId);
                        MainActivity.this.startActivity(intent);
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Регистрация");
                alert.show();
            }
        });
        activityMainContainerLoginDrivenLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://login.mos.ru/sps/login/methods/password?bo=%2Fsps%2Foauth%2Fae%3Fscope%3Dbirthday%2520contacts%2520openid%2520profile%26client_id%3Ddyn%3Amy.mosenergosbyt.ru%3A8b404179-7a2f-4dce-ab1d-368ed7052f5f%26response_type%3Dcode%26state%3Df708dcb4-d9c2-4cbb-8f51-e41ba99f9226%26code_challenge_method%3DS256%26code_challenge%3DNDu8b79TNp7N5gzYRC8vDED4QkMvhoZzylHQKKO2yOY%26redirect_uri%3Dhttps%3A%2F%2Fmy.mosenergosbyt.ru%2Fopen-mobile-app%3Fmode%3Dsudir_auth";
                openSite(url);
            }
        });
        activityMainContainerSitesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog builder = new BottomSheetDialog(MainActivity.this);
                builder.setContentView(R.layout.activity_official_sites_dialog);
                builder.setTitle("Регистрация");
                builder.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Log.d("debug", "назначаю обработчики");
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.activity_official_sites_dialog, null);
                        LinearLayout activityOfficialSitesDialogContainerLinksFirst = dialogView.findViewById(R.id.activity_official_sites_dialog_container_links_first);
                        activityOfficialSitesDialogContainerLinksFirst.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = "https://login.mos.ru/sps/login/methods/password?bo=%2Fsps%2Foauth%2Fae%3Fscope%3Dbirthday%2520contacts%2520openid%2520profile%26client_id%3Ddyn%3Amy.mosenergosbyt.ru%3A8b404179-7a2f-4dce-ab1d-368ed7052f5f%26response_type%3Dcode%26state%3Df708dcb4-d9c2-4cbb-8f51-e41ba99f9226%26code_challenge_method%3DS256%26code_challenge%3DNDu8b79TNp7N5gzYRC8vDED4QkMvhoZzylHQKKO2yOY%26redirect_uri%3Dhttps%3A%2F%2Fmy.mosenergosbyt.ru%2Fopen-mobile-app%3Fmode%3Dsudir_auth";
                                openSite(url);
                            }
                        });
                        LinearLayout activityOfficialSitesDialogContainerLinksSecond = dialogView.findViewById(R.id.activity_official_sites_dialog_container_links_second);
                        activityOfficialSitesDialogContainerLinksSecond.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = "https://login.mos.ru/sps/login/methods/password?bo=%2Fsps%2Foauth%2Fae%3Fscope%3Dbirthday%2520contacts%2520openid%2520profile%26client_id%3Ddyn%3Amy.mosenergosbyt.ru%3A8b404179-7a2f-4dce-ab1d-368ed7052f5f%26response_type%3Dcode%26state%3Df708dcb4-d9c2-4cbb-8f51-e41ba99f9226%26code_challenge_method%3DS256%26code_challenge%3DNDu8b79TNp7N5gzYRC8vDED4QkMvhoZzylHQKKO2yOY%26redirect_uri%3Dhttps%3A%2F%2Fmy.mosenergosbyt.ru%2Fopen-mobile-app%3Fmode%3Dsudir_auth";
                                openSite(url);
                            }
                        });
                    }
                });
                builder.show();
            }
        });
        activityMainContainerContactsRowContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        activityMainContainerHelpRowSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses = new String[]{ "LKK_INFO@mosenergosbyt.ru" };
                String subject = "Обратная связь";
                composeEmail(addresses, subject);
            }
        });
    }

    public void openSite(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

}
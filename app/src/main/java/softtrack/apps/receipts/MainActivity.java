package softtrack.apps.receipts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button activityMainContainerLoginBtn;
    public SQLiteDatabase db;

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
        activityMainContainerLoginBtn = findViewById(R.id.activity_main_container_login_btn);
        activityMainContainerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersonalAreaActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

}
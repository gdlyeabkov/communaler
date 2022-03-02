package softtrack.apps.receipts;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_subs);

        initialize();

    }

    public void initialize() {
        getSupportActionBar().setTitle("Подписки");
    }

}

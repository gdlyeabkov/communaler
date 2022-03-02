package softtrack.apps.receipts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentsAndTransfersActivity extends AppCompatActivity {

    public LinearLayout activityPaymentsAndTransfersContainerCommunication;
    public LinearLayout activityPaymentsAndTransfersContainerNetwork;
    public LinearLayout activityPaymentsAndTransfersContainerCommunals;
    public int userId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_and_transfers);

        initialize();

    }

    public void initialize() {

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            userId = extras.getInt("userId");
        }

        activityPaymentsAndTransfersContainerCommunication = findViewById(R.id.activity_payments_and_transfers_container_communication);
        activityPaymentsAndTransfersContainerNetwork = findViewById(R.id.activity_payments_and_transfers_container_network);
        activityPaymentsAndTransfersContainerCommunals = findViewById(R.id.activity_payments_and_transfers_container_communals);
        activityPaymentsAndTransfersContainerCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentsAndTransfersActivity.this, PersonalAreaActivity.class);
                intent.putExtra("userId", userId);
                PaymentsAndTransfersActivity.this.startActivity(intent);
            }
        });
        activityPaymentsAndTransfersContainerNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentsAndTransfersActivity.this, PersonalAreaActivity.class);
                intent.putExtra("userId", userId);
                PaymentsAndTransfersActivity.this.startActivity(intent);
            }
        });
        activityPaymentsAndTransfersContainerCommunals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentsAndTransfersActivity.this, PersonalAreaActivity.class);
                intent.putExtra("userId", userId);
                PaymentsAndTransfersActivity.this.startActivity(intent);
            }
        });
    }

}

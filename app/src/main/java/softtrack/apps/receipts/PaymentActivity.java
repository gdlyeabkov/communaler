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

public class PaymentActivity extends AppCompatActivity {

    public EditText activityPaymentContainerTotalCostInput;
    public Button activityPaymentContainerPayBtn;
    public int userId = 0;
    public int amountId = 0;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
            amountId = extras.getInt("amountId");
        }

        activityPaymentContainerTotalCostInput = findViewById(R.id.activity_payment_container_total_cost_input);
        activityPaymentContainerPayBtn = findViewById(R.id.activity_payment_container_pay_btn);
        activityPaymentContainerPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawActivityPaymentContainerTotalCostInputContent = activityPaymentContainerTotalCostInput.getText();
                String activityPaymentContainerTotalCostInputContent = rawActivityPaymentContainerTotalCostInputContent.toString();
                int cost = Integer.valueOf(activityPaymentContainerTotalCostInputContent);
                /*Cursor usersCursor = db.rawQuery("Select * from users where _id = " + userId, null);
                usersCursor.moveToFirst();
                int currentCost = usersCursor.getInt(12);*/
                Cursor amountsCursor = db.rawQuery("Select * from amounts where _id = " + amountId, null);
                amountsCursor.moveToFirst();
                int currentCost = amountsCursor.getInt(6);
                int updatedCost = currentCost - cost;
                ContentValues contentValues = new ContentValues();
                contentValues.put("cost", updatedCost);
//                db.update("users", contentValues, "_id = ? ", new String[] { Integer.toString(userId) } );
                db.update("amounts", contentValues, "_id = ? ", new String[] { Integer.toString(amountId) } );
                Intent intent = new Intent(PaymentActivity.this, PersonalAreaActivity.class);
                intent.putExtra("userId", userId);
                PaymentActivity.this.startActivity(intent);
            }
        });
    }

}

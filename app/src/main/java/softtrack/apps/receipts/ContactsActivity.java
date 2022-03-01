package softtrack.apps.receipts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class ContactsActivity extends AppCompatActivity {


    public TabLayout mainTabs;
    public ViewPager2 currentTab;
    public ImageView openContactsBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initialize();

    }

    public void initialize() {
        mainTabs = findViewById(R.id.activity_contacts_main_tabs);
        currentTab = findViewById(R.id.activity_contacts_current_tab);
        openContactsBtn = findViewById(R.id.activity_contacts_open_conctacts_btn);


        FragmentManager fm = getSupportFragmentManager();
        ContactsViewStateAdapter sa = new ContactsViewStateAdapter(fm, getLifecycle());
        currentTab.setAdapter(sa);
        mainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        currentTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

            }
        });
        openContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContactsChooser();
            }
        });
    }

    public void showContactsChooser() {
        BottomSheetDialog alert = new BottomSheetDialog(ContactsActivity.this);
        alert.setContentView(R.layout.activity_contacts_call_dialog);
        alert.setTitle("Сообщение");
        alert.show();
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, ContactsContract.Contacts.CONTENT_URI);
                Uri u = Uri.parse("tel:" + "84995509550");
                Intent intent = new Intent(Intent.ACTION_DIAL, u);
                startActivityForResult(intent, 0);
            }
        });
    }

}

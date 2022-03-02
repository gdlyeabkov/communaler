package softtrack.apps.receipts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class PersonalAreaActivity extends AppCompatActivity {

    public static ViewPager2 currentTab;
    public TabLayout mainTabs;
    public int userId;
    public static PersonalAreaActivity gateway;
    public int visible;
    public int unvisible;
    public MenuItem promoCodeBtn;
    public Menu myMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        initialize();
    }

    public void initialize() {

        gateway = PersonalAreaActivity.this;

        visible = View.VISIBLE;
        unvisible = View.GONE;

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        userId = extras.getInt("userId");

        mainTabs = findViewById(R.id.mainTabs);
        currentTab = findViewById(R.id.currentTab);

        FragmentManager fm = getSupportFragmentManager();
        ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
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
            @SuppressLint("RestrictedApi")
            @Override
            public void onPageSelected(int position) {
                ActionBar actionBar = getSupportActionBar();
                if (position == 0) {
                    actionBar.hide();
                } else if (position == 1) {
                    actionBar.show();
                    if(myMenu != null) {
                        myMenu.findItem(R.id.activity_services_actions_promocode).setVisible(true);
                    }
                    actionBar.setTitle("Услуги");
                } else if (position == 2) {
                    actionBar.show();
                    myMenu.findItem(R.id.activity_services_actions_promocode).setVisible(false);
                    actionBar.setTitle("Вопросы");
                } else if (position == 3) {
                    actionBar.hide();
                } else if (position == 4) {
                    actionBar.show();
                    myMenu.findItem(R.id.activity_services_actions_promocode).setVisible(false);
                    actionBar.setTitle("Еще");
                }
            }
        });
        requestPinCode();
    }

    public void requestPinCode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalAreaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setMessage("Неверно введен логин или пароль.\nПроверьте правильность ввода и\nповторите попытку.\nОбращаем Ваше внимание, что\nавторизоваться по номеру лицевого\nсчета теперь нельзя. Для входа в\nновый Личный кабинетиспользуйте\nВаш номер телефона\nили e-mail в качестве логина и ранее\nиспользуемый Вами пароль.");
        builder.setCancelable(true);
        builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Сообщение");
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInfalter = getMenuInflater();
        menuInfalter.inflate(R.menu.activity_services_actions, menu);
        promoCodeBtn = menu.findItem(R.id.activity_services_actions_promocode);
        myMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        boolean isPromoCodeMenuItem = itemId == R.id.activity_services_actions_promocode;
        if (isPromoCodeMenuItem) {
            Intent intent = new Intent(PersonalAreaActivity.this, PromocodeActivity.class);
            PersonalAreaActivity.this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}

package ch.he_arc.ig.appagoocheck;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {
    ListView lv;
    String[] appPackages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list;

        ArrayList<AppInfo> apps = AppInfo.getListOfInstalledApp(getApplicationContext());
        
        final int max = apps.size();
        String[] appNames = new String[max];
        appPackages = new String[max];
        Drawable[] appImages = new Drawable[max];

        for (int i=0; i<max; i++) {
            appNames[i] = apps.get(i).getName();
            appPackages[i] = apps.get(i).getPackageName();
            appImages[i] = apps.get(i).getIcon();
        }

        CustomList adapter = new CustomList(MainActivity.this, appNames, appImages);

        list=(ListView)findViewById(R.id.appList);
        list.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);

        final String strApps = Arrays.toString(appPackages);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String[] params = {getAccountManager(), strApps.replaceAll("\\s","")};
                        new HTTPRequestTask().execute(params);
                        Toast.makeText(getApplicationContext(), "Installed applications submitted!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private String getAccountManager() {
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = manager.getAccounts();
        String gmail = null;

        for(Account account: accounts)
        {
            if(account.type.equalsIgnoreCase("com.google"))
            {
                gmail = account.name;
                break;
            }
        }
        return gmail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


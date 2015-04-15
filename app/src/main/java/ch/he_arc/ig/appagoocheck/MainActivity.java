package ch.he_arc.ig.appagoocheck;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list;

        ArrayList<AppInfo> apps = AppInfo.getListOfInstalledApp(getApplicationContext());

        final int max = apps.size();
        String[] appNames = new String[max];
        Drawable[] appImages = new Drawable[max];

        for (int i=0; i<max; i++) {
            appNames[i] = apps.get(i).getName();
            appImages[i] = apps.get(i).getIcon();
        }

        CustomList adapter = new CustomList(MainActivity.this, appNames, appImages);

        list=(ListView)findViewById(R.id.appList);
        list.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Tiens!!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

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


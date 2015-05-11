package ch.he_arc.ig.appagoocheck;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by alessio.desanto on 11.05.2015.
 */
public class HTTPRequestTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        System.out.println("------------------------------------->");
        System.out.println(params[1].substring(1, params[1].length() - 1));
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://www.appagoo.com/api/userProfiles/");

        try {
            JSONObject installedApps = new JSONObject();
            installedApps.put("user", params[0]);
            String strApps = Arrays.toString(params);
            installedApps.put("applications", params[1].substring(1, params[1].length() - 1));
            StringEntity se = null;
            se = new StringEntity(installedApps.toString());
            post.setEntity(se);
            HttpResponse response = client.execute(post);
            System.out.println("---------------------------------->");
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String page)
    {
        //onPostExecute
    }
}

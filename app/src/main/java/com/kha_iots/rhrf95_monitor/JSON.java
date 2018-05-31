package com.kha_iots.rhrf95_monitor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JSON extends AsyncTask<String, JSONObject, JSONObject> {
    public static String jsonTAG = "JSON";
    public static MyArrayAdapter adapter;
    private JSONObject jsonObject = null;
    private ListView listView;
    private Context context;

    public JSON(Context context, ListView listView) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... jsonFileURL) {
        try{
            URL url = new URL(jsonFileURL[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) throw new RuntimeException("HttpResponseCode: "+ responseCode);
            else{
                String JSONStringContent = "";
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()) JSONStringContent = JSONStringContent + scanner.nextLine();
                scanner.close();
                Log.d(jsonTAG, JSONStringContent);
                jsonObject = new JSONObject(JSONStringContent);
            }
        }
        catch (MalformedURLException mUE){
        }
        catch (IOException ioE){
        }
        catch (RuntimeException rE){
        }
        catch (JSONException jE) {
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try{
            JSONArray data = jsonObject.getJSONArray("data");
            Integer dataSize = data.length();
            ArrayList<JSONObject> jsonArrayList = new ArrayList<JSONObject>();
            for (int i = dataSize - 1; i >= 0; i--) jsonArrayList.add(data.getJSONObject(i));
            adapter = new MyArrayAdapter(context, R.layout.list_view_item, jsonArrayList);
            listView.setAdapter(adapter);
        }
        catch(JSONException jE){}
    }
}

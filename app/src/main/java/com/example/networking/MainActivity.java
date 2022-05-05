package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private final String TAG = "==>";

    private RecyclerView RecyclerView;
    private ArrayList<Mountains> listOfMountains;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);

        RecyclerView = findViewById(R.id.recyler_view);
        RecyclerView.setAdapter(adapter);
        adapter = new MyAdapter(listOfMountains);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfMountains = new ArrayList<Mountains>();
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Mountains>>() {}.getType();
        ArrayList<Mountains> lista = gson.fromJson(json, type);
        listOfMountains.addAll(lista);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "Antal Berg: "+listOfMountains.size());
        Log.d(TAG, "Första Berget: "+listOfMountains.get(0).toString());
    }
}

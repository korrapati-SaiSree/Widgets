package com.example.acer.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        String[] s=getResources().getStringArray(R.array.arr);
        ArrayAdapter<String> adapter=new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,s);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemname=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, itemname, Toast.LENGTH_SHORT).show();
                sharedPreferences=getSharedPreferences("com.example.acer.widget",MODE_PRIVATE);
                editor=sharedPreferences.edit();
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(itemname);
                editor.putString("Key",stringBuffer.toString());
                editor.apply();
                Intent intent=new Intent(MainActivity.this,SampleWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids=AppWidgetManager.getInstance(MainActivity.this).
                        getAppWidgetIds(new ComponentName(getApplicationContext(),SampleWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(intent);
            }
        });
    }
}

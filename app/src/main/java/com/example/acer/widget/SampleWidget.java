package com.example.acer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
    SharedPreferences sharedPreferences;
   // SharedPreferences.Editor editor;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       for(int appWidget:appWidgetIds){
           sharedPreferences=context.getSharedPreferences("com.example.acer.widget",Context.MODE_PRIVATE);
           String s=sharedPreferences.getString("Key","No data");
           Intent i=new Intent(context,MainActivity.class);
           PendingIntent pendingIntent=PendingIntent.getActivity(context,15,i,0);
           RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.widgetdesign);
           remoteViews.setTextViewText(R.id.textview1,s);
           remoteViews.setOnClickPendingIntent(R.id.textview1,pendingIntent);
           appWidgetManager.updateAppWidget(appWidget,remoteViews);
       }

    }
}

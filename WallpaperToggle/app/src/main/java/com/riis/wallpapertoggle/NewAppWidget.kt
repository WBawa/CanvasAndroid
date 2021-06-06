package com.riis.wallpapertoggle

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Implementation of App Widget functionality.
 */

private const val ACTION_WIDGET_WALLPAPER_ONE = "WallpaperOne"
private const val ACTION_WIDGET_WALLPAPER_TWO = "WallpaperTwo"

class NewAppWidget : AppWidgetProvider() {

    private lateinit var wallpaperManager: WallpaperManager

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        wallpaperManager = WallpaperManager.getInstance(context)
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

        var remoteViews: RemoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

        var active = Intent(context, NewAppWidget::class.java)
        active.action = ACTION_WIDGET_WALLPAPER_ONE
        var actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0)
        remoteViews.setOnClickPendingIntent(R.id.wallpaper1, actionPendingIntent)

        active = Intent(context, NewAppWidget::class.java)
        active.action = ACTION_WIDGET_WALLPAPER_TWO
        actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0)
        remoteViews.setOnClickPendingIntent(R.id.wallpaper2, actionPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

        wallpaperManager = WallpaperManager.getInstance(context)
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        wallpaperManager = WallpaperManager.getInstance(context)
        when (intent?.action) {
            (ACTION_WIDGET_WALLPAPER_ONE) -> {
                Toast.makeText(context, "Wallpaper One", Toast.LENGTH_SHORT).show()
                Log.d("NewAppWidget", "this is a test if wallpaper 1 is working")
                val image: Bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.onda)
                wallpaperManager.setBitmap(image)
            }
            (ACTION_WIDGET_WALLPAPER_TWO) -> {
                Toast.makeText(context, "Wallpaper Two", Toast.LENGTH_SHORT).show()
                Log.d("NewAppWidget", "this is a test if wallpaper TWO is working")
                val image: Bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.yourname)
                wallpaperManager.setBitmap(image)
            }
            else -> {
                super.onReceive(context, intent)
            }
        }
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
//    val widgetText = context.getString(R.string.appwidget_text)
//    val widgetText = "PaperToggle"
//    val widgetText = ""
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)



    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
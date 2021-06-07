package com.riis.wallpapertoggle

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.abs

/**
 * Implementation of App Widget functionality.
 */

private const val ACTION_WIDGET_WALLPAPER_ONE = "WallpaperOne"
private const val ACTION_WIDGET_WALLPAPER_TWO = "WallpaperTwo"

class NewAppWidget : AppWidgetProvider() {

    private lateinit var wallpaperManager: WallpaperManager

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

        var remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

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
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        wallpaperManager = WallpaperManager.getInstance(context)
        when (intent?.action) {
            (ACTION_WIDGET_WALLPAPER_ONE) -> {
//                Toast.makeText(context, "Wallpaper One", Toast.LENGTH_SHORT).show()
//                Log.d("NewAppWidget", "this is a test if wallpaper 1 is working")


                setWallpaper(context, R.drawable.onda)
            }
            (ACTION_WIDGET_WALLPAPER_TWO) -> {
//                Toast.makeText(context, "Wallpaper Two", Toast.LENGTH_SHORT).show()
//                Log.d("NewAppWidget", "this is a test if wallpaper TWO is working")
                setWallpaper(context, R.drawable.yourname)
            }
            else -> {
                super.onReceive(context, intent)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setWallpaper(context: Context?, image: Int) {
        val bitmap: Bitmap = (context?.getDrawable(image) as BitmapDrawable).bitmap

        val phoneHeight: Int = context.resources.displayMetrics.heightPixels
        val phoneWidth: Int = context.resources.displayMetrics.widthPixels

        val imageHeight = bitmap.height
        val imageWidth = bitmap.width

//        val scaled = Bitmap.createScaledBitmap(bitmap, phoneWidth, phoneHeight, false)

        val image = Bitmap.createBitmap(bitmap, (abs(imageWidth - phoneWidth) / 1f).toInt(), 0, imageWidth - (abs(imageWidth - phoneWidth) / 1f).toInt(), imageHeight)

        wallpaperManager.setBitmap(image)
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
//    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
package com.riis.wallpapertoggle

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.TextView

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

        var remoteViews: RemoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)
        var watchWidget: ComponentName = ComponentName(context, NewAppWidget::class.java)

        var intent: Intent = Intent(context, NewAppWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        var pendingIntent: PendingIntent = PendingIntent.getBroadcast()

        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
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
/*
 * Copyright (c) 2015 by k3b.
 *
 * This file is part of AndroFotoFinder.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
 
package de.k3b.android.androFotoFinder;

import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Locale;

import de.k3b.android.widget.LocalizedActivity;

/**
 * Global Settings
 *
 * Created by k3b on 04.06.2015.
 */
public class Global {
    /** LOG_CONTEXT is used as logging source for filtering logging messages that belong to this */
    public static final String LOG_CONTEXT = "k3bFoto";

    public static final String PREF_KEY_USER_LOCALE = "user_locale";

    /**
     * true: addToCompressQue several Log.d(...) to show what is going on.
     * debugEnabled is updated by the SettingsActivity
     */
    public static boolean debugEnabled = false;
    public static boolean debugEnabledViewItem = false;
    public static boolean debugEnabledSql = false;
    public static boolean debugEnabledMemory = false;

    /** The maximum number of **Blue selection markers** in the [Geographic-Map](geographic-map). */
    public static int maxSelectionMarkersInMap = 255;

    /** defines the [Image-View's](Image-View) timing of menu command **slideshow** */
    public static int slideshowIntervalInMilliSecs = 1500;

    /** defines the timespan after which the [Image-View's](Image-View) ActionBar is hidden */
    public static int actionBarHideTimeInMilliSecs = 2000;

    /** If checked [multi selection mode](Gallery-View#Multiselection) in [Gallery-View](Gallery-View) is canceled after a command from Actionbar or Menu */
    public static boolean clearSelectionAfterCommand = false;

    /** true update only if media scanner is not running. false=risky=always allow.  */
    public static final boolean mustCheckMediaScannerRunning = true;

    /** defines the filesystem's directory where [Bookmark files](Bookmarks) are stored and loaded from. */
    public static File reportDir = new File(Environment.getExternalStorageDirectory(), "databases/sql");
    public static final String reportExt = ".query";

    /** defines the filesystem's directory where crash reports are written to. */
    public static File logCatDir = new File(Environment.getExternalStorageDirectory(), "copy/log");

    /** remember last picked geo-s */
    public static File pickHistoryFile = null; // initialized in app.onCreate with local database file
    public static int pickHistoryMax = 25;

    /** false: cmd setGeo => form(GeoEditActivity) => mapPicker */
    public static boolean geoNoEdit = true;

    /** #26 which image resolution should the "non zoomed imageView" have? */
    public static boolean initialImageDetailResolutionHigh = false; // false: MediaStore.Images.Thumbnails.MINI_KIND; true: FULL_SCREEN_KIND;

    public static void debugMemory(String modul, String message) {
        if (Global.debugEnabledMemory) {
            Runtime r = Runtime.getRuntime();
            String formattedMessage = String.format("memory : (total/free/avail) = (%3$dK/%4$dK/%5$dK)\t- %1$s.%2$s",
                    modul, message, r.totalMemory()/1024, r.freeMemory()/1024, r.maxMemory()/1024);

            Log.d(Global.LOG_CONTEXT, formattedMessage);
        }
    }

    public static Locale systemLocale = Locale.getDefault();
}

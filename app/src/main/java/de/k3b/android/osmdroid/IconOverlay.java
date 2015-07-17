package de.k3b.android.osmdroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

/**
 * An icon placed at a particular point on the map's surface.
 * Thanks to ResourceProxy the constructor can be called in a non-gui thread i.e. in AsyncTask.
 *
 * Inspired by org.osmdroid.bonuspack.overlays.Marker.
 *
 * Created by k3b on 16.07.2015.
 */
public class IconOverlay extends Overlay {
    /** Usual values in the (U,V) coordinates system of the icon image */
    public static final float ANCHOR_CENTER=0.5f, ANCHOR_LEFT=0.0f, ANCHOR_TOP=0.0f, ANCHOR_RIGHT=1.0f, ANCHOR_BOTTOM=1.0f;

    /*attributes for standard features:*/
    protected Drawable mIcon        = null;
    protected IGeoPoint mPosition   = null;

    protected float mBearing = 0.0f;
    protected float mAnchorU = ANCHOR_CENTER , mAnchorV=ANCHOR_CENTER;
    protected float mAlpha = 1.0f; //opaque

    protected boolean mFlat = false; //billboard;

    protected Point mPositionPixels = new Point();

    /** save to be called in non-gui-thread */
    protected IconOverlay(final ResourceProxy pResourceProxy) {
        super(pResourceProxy);
    }
        /** save to be called in non-gui-thread */
    public IconOverlay(final ResourceProxy pResourceProxy, IGeoPoint position, Drawable icon) {
        super(pResourceProxy);
        set(position, icon);
    }

    public IconOverlay set(IGeoPoint position, Drawable icon) {
        this.mPosition = position;
        this.mIcon = icon;
        return this;
    }

    /**
     * Draw the icon.
     */
    @Override
    protected void draw(Canvas canvas, MapView mapView, boolean shadow) {
        if (shadow)
            return;
        if (mIcon == null)
            return;

        final Projection pj = mapView.getProjection();

        pj.toPixels(mPosition, mPositionPixels);
        int width = mIcon.getIntrinsicWidth();
        int height = mIcon.getIntrinsicHeight();
        Rect rect = new Rect(0, 0, width, height);
        rect.offset(-(int)(mAnchorU*width), -(int)(mAnchorV*height));
        mIcon.setBounds(rect);

        mIcon.setAlpha((int)(mAlpha*255));

        float rotationOnScreen = (mFlat ? -mBearing : mapView.getMapOrientation()-mBearing);
        drawAt(canvas, mIcon, mPositionPixels.x, mPositionPixels.y, false, rotationOnScreen);
    }
}

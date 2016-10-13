package com.mopub.mobileads.resource;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import com.unity3d.ads.android.R;

public class DrawableConstants {
    public static final int TRANSPARENT_GRAY = -2013265920;

    public static class BlurredLastVideoFrame {
        public static final int ALPHA = 100;
    }

    public static class CloseButton {
        public static final String DEFAULT_CLOSE_BUTTON_TEXT = "";
        public static final int EDGE_PADDING = 16;
        public static final int IMAGE_PADDING_DIPS = 5;
        public static final Cap STROKE_CAP;
        public static final int STROKE_COLOR = -1;
        public static final float STROKE_WIDTH = 8.0f;
        public static final int TEXT_COLOR = -1;
        public static final int TEXT_RIGHT_MARGIN_DIPS = 7;
        public static final float TEXT_SIZE_SP = 20.0f;
        public static final Typeface TEXT_TYPEFACE;
        public static final int WIDGET_HEIGHT_DIPS = 46;

        static {
            STROKE_CAP = Cap.ROUND;
            TEXT_TYPEFACE = Typeface.create("Helvetica", 0);
        }
    }

    public static class CtaButton {
        public static final int BACKGROUND_ALPHA = 51;
        public static final int BACKGROUND_COLOR = -16777216;
        public static final Style BACKGROUND_STYLE;
        public static final int CORNER_RADIUS_DIPS = 6;
        public static final String DEFAULT_CTA_TEXT = "Learn More";
        public static final int HEIGHT_DIPS = 38;
        public static final int MARGIN_DIPS = 16;
        public static final int OUTLINE_ALPHA = 51;
        public static final int OUTLINE_COLOR = -1;
        public static final int OUTLINE_STROKE_WIDTH_DIPS = 2;
        public static final Style OUTLINE_STYLE;
        public static final Align TEXT_ALIGN;
        public static final int TEXT_COLOR = -1;
        public static final float TEXT_SIZE_SP = 15.0f;
        public static final Typeface TEXT_TYPEFACE;
        public static final int WIDTH_DIPS = 150;

        static {
            BACKGROUND_STYLE = Style.FILL;
            OUTLINE_STYLE = Style.STROKE;
            TEXT_TYPEFACE = Typeface.create("Helvetica", 0);
            TEXT_ALIGN = Align.CENTER;
        }
    }

    public static class GradientStrip {
        public static final int END_COLOR;
        public static final int GRADIENT_STRIP_HEIGHT_DIPS = 72;
        public static final int START_COLOR;

        static {
            START_COLOR = Color.argb(R.styleable.Theme_checkboxStyle, END_COLOR, END_COLOR, END_COLOR);
            END_COLOR = Color.argb(END_COLOR, END_COLOR, END_COLOR, END_COLOR);
        }
    }

    public static class PrivacyInfoIcon {
        public static final int LEFT_MARGIN_DIPS = 12;
        public static final int TOP_MARGIN_DIPS = 12;
    }

    public static class ProgressBar {
        public static final int BACKGROUND_ALPHA = 128;
        public static final int BACKGROUND_COLOR = -1;
        public static final Style BACKGROUND_STYLE;
        public static final int HEIGHT_DIPS = 4;
        public static final int NUGGET_WIDTH_DIPS = 4;
        public static final int PROGRESS_ALPHA = 255;
        public static final int PROGRESS_COLOR;
        public static final Style PROGRESS_STYLE;

        static {
            BACKGROUND_STYLE = Style.FILL;
            PROGRESS_COLOR = Color.parseColor("#FFCC4D");
            PROGRESS_STYLE = Style.FILL;
        }
    }

    public static class RadialCountdown {
        public static final int BACKGROUND_ALPHA = 128;
        public static final int BACKGROUND_COLOR = -1;
        public static final Style BACKGROUND_STYLE;
        public static final int CIRCLE_STROKE_WIDTH_DIPS = 3;
        public static final int PADDING_DIPS = 5;
        public static final int PROGRESS_ALPHA = 255;
        public static final int PROGRESS_COLOR = -1;
        public static final Style PROGRESS_STYLE;
        public static final int RIGHT_MARGIN_DIPS = 16;
        public static final int SIDE_LENGTH_DIPS = 45;
        public static final float START_ANGLE = -90.0f;
        public static final Align TEXT_ALIGN;
        public static final int TEXT_COLOR = -1;
        public static final float TEXT_SIZE_SP = 18.0f;
        public static final int TOP_MARGIN_DIPS = 16;

        static {
            BACKGROUND_STYLE = Style.STROKE;
            PROGRESS_STYLE = Style.STROKE;
            TEXT_ALIGN = Align.CENTER;
        }
    }

    public static class SocialActions {
        public static final int ADS_BY_LEFT_MARGIN_DIPS = 6;
        public static final int SOCIAL_ACTIONS_LEFT_MARGIN_DIPS = 16;
    }
}

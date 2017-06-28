package Utils;

import android.graphics.Color;

/**
 * Created by alexpuhalschi on 28/06/2017.
 */

public class ColorUtils {
    public static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;

        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);

        return Color.rgb((int) r, (int) g, (int) b);
    }
}

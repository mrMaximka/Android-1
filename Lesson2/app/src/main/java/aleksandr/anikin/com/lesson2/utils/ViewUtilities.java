package aleksandr.anikin.com.lesson2.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Aleksandr Anikin
 */
public final class ViewUtilities {

    public static void makeToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    private ViewUtilities() {
    }
}

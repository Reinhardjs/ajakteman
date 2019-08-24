package tutor.ajakteman.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionHelper {

    public static int PLACE_PICKER_REQUEST = 123;

    public static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET
    };

    public static boolean hasPermission(Activity activity, String perm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return(PackageManager.PERMISSION_GRANTED == activity.checkSelfPermission(perm));
        } else {
            return true;
        }
    }

    public static boolean canAccessLocation(Activity activity) {
        return(hasPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                && hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                && hasPermission(activity, Manifest.permission.INTERNET));
    }

    public static void requestLocationPermission(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(LOCATION_PERMS, 77);
        }
    }

}

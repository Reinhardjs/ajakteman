package tutor.ajakteman.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import tutor.ajakteman.R;

public class Utils {

//    public static float pixelsToDps(Context context, float px){
//        return px / context.getResources().getDisplayMetrics().density;
//    }
//
//    public static float dpsToPixels(Context context, float dp){
//        return dp * context.getResources().getDisplayMetrics().density;
//    }

    public static float pixelsToDps(float px){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float dpsToPixels(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * metrics.density;
        return px;
    }

    public static AlertDialog buildLocationDetailDialog(Context context, String placename, String address){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.location_detail_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView placeText = dialogView.findViewById(R.id.placeNameText);
        placeText.setText(placename);

        TextView addressText = dialogView.findViewById(R.id.addressText);
        addressText.setText(address);

        AlertDialog alertDialog = dialogBuilder.create();

        return alertDialog;
    }
//http://stackoverflow.com/questions/4605527/converting-pixels-to-dp
//The above method results accurate method compared to below methods
//http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android

//    private int convertDpToPx(int dp){
//        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
//
//    }
//
//    private int convertPxToDp(int px){
//        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
//    }
}

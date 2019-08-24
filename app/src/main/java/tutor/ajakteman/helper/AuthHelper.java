package tutor.ajakteman.helper;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class AuthHelper {
    public static void setAccountType(Context context, String accountType){
        context.getSharedPreferences("_", context.MODE_PRIVATE).edit().putString("accountType", accountType).apply();
    }

    public static void deleteAccountType(Context context){
        context.getSharedPreferences("_", MODE_PRIVATE).edit().remove("accountType").apply();
    }

    public static String getAccountType(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("accountType", "no-account");
    }
}

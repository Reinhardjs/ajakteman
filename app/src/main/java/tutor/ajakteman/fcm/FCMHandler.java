package tutor.ajakteman.fcm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class FCMHandler {

    public static void enableFCM(){
        // Enable FCM via enable Auto-init service which generate new token and receive in FCMService
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    public static void disableFCM(){
        // Disable auto init
        FirebaseMessaging.getInstance().setAutoInitEnabled(false);
    }

    public static void deleteInstanceId(Context context){
        context.getSharedPreferences("_", MODE_PRIVATE).edit().remove("fcm_token").apply();
        new Thread(() -> {
            try {
                // Remove InstanceID initiate to unsubscribe all topic
                // TODO: May be a better way to use FirebaseMessaging.getInstance().unsubscribeFromTopic()
                FirebaseInstanceId.getInstance().deleteInstanceId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fcm_token", "empty");
    }

    public static void requestNewToken(Context context){

        String fcmtoken = getToken(context);
        if (fcmtoken.equals("empty")){
            Log.d("MYAPP", "GENERATING NEW KEY");
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.d("MYAPP", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            Log.d("MYAPP", "GENERATED TOKEN : " + token);

                            context.getSharedPreferences("_", context.MODE_PRIVATE).edit().putString("fcm_token", token).apply();


                            // LOG TO FIREBASE DATABASE
                            String userID = String.valueOf(FirebaseAuth.getInstance()
                                    .getCurrentUser().getUid());

                            DatabaseReference myConnectionsRef = FirebaseDatabase.getInstance()
                                    .getReference("users/siswa")
                                    .child(userID).child("fcm_token");

                            myConnectionsRef.setValue(token);
                        }
                    });
        } else {
            Log.d("MYAPP", "OLD REGISTERED TOKEN : " + fcmtoken);
        }
    }

}
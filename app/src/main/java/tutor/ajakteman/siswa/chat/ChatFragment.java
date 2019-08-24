package tutor.ajakteman.siswa.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.iid.FirebaseInstanceId;

import tutor.ajakteman.R;
import tutor.ajakteman.fcm.FCMHandler;

import static tutor.ajakteman.helper.PermissionHelper.PLACE_PICKER_REQUEST;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat, container, false);

//        RatingBar ratingBar = rootView.findViewById(R.id.rating_bar);
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating,
//                                        boolean fromUser) {
//                System.out.println("Hwre");
//
//                ratedValue = String.valueOf(ratingBar.getRating());
//                rateMessage.setText("Rating : "
//                        + ratedValue + "/5");
//            }
//        });

        Button tombol = rootView.findViewById(R.id.button);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                LatLng pointA = new LatLng(-7.949612, 112.606576);
                LatLng pointB = new LatLng(-7.949612, 112.619445);
                builder.setLatLngBounds(new LatLngBounds(pointA, pointB));
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        Button enableButton = rootView.findViewById(R.id.enable);
        enableButton.setOnClickListener(v -> {
            FCMHandler.enableFCM();
        });

        Button disableButton = rootView.findViewById(R.id.disable);
        disableButton.setOnClickListener(v -> {
            Context context = getContext();
            FCMHandler.disableFCM();
        });

        Button checkID = rootView.findViewById(R.id.checkID);
        checkID.setOnClickListener(v -> {
            Log.d("MYAPP", "ID CHECK : " + FirebaseInstanceId.getInstance().getId());
        });

        Button checkToken = rootView.findViewById(R.id.checkToken);
        checkToken.setOnClickListener(v -> {
            String fcmtoken = FCMHandler.getToken(getContext());
            Log.d("MYAPP", "TOKEN CHECK1 : " + FirebaseInstanceId.getInstance().getToken());
            Log.d("MYAPP", "TOKEN CHECK2 : " + fcmtoken);
        });

        Button deleteInstance = rootView.findViewById(R.id.deleteInstence);
        deleteInstance.setOnClickListener(v -> {
            Toast.makeText(getContext(), "DELETE INSTANCE SUCCESS", Toast.LENGTH_SHORT).show();
            FCMHandler.deleteInstanceId(getContext());
        });




        Button manualGenerate = rootView.findViewById(R.id.manualGenerate);
        manualGenerate.setOnClickListener(v -> {





        });

        return rootView;
    }


}

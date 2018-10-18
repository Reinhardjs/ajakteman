package tutor.ajakteman.siswa.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tutor.ajakteman.R;

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

        return rootView;
    }



}

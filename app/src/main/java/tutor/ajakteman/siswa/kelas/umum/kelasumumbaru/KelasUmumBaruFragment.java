package tutor.ajakteman.siswa.kelas.umum.kelasumumbaru;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import tutor.ajakteman.R;
import tutor.ajakteman.adapter.SpinnerArrayAdapter;
import tutor.ajakteman.data.Kelas;
import tutor.ajakteman.helper.Utils;
import tutor.ajakteman.siswa.kelas.lobbykelas.LobbyKelasActivity;

import static android.app.Activity.RESULT_OK;
import static tutor.ajakteman.helper.PermissionHelper.PLACE_PICKER_REQUEST;

public class KelasUmumBaruFragment extends Fragment {

    String userID;
    DatabaseReference databaseReference;
    TextView locationTextView;
    private String placename, latitude, longitude, address;
    private ImageView viewDetail;
    private ProgressBar progressBar;
    private LinearLayout dateContainer;
    private Button createButton;
    private View selectLocation;
    private Map<String, String> selectedLocation = new HashMap<>();
    private Map<String, String> selectedTime = new HashMap<>();

    public KelasUmumBaruFragment() {
        // Required empty public constructor
    }

    private SpinnerArrayAdapter getAdapter(String... strings){
        return new SpinnerArrayAdapter(getContext(),
                R.layout.spinner_item, new ArrayList<String>(
                Arrays.asList(strings)
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_kelas_umum_baru, container, false);

        progressBar = root.findViewById(R.id.progressBar);

        userID = String.valueOf(FirebaseAuth.getInstance()
                .getCurrentUser().getUid());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final Spinner spinnerJenjang = root.findViewById(R.id.spinnerJenjang);
        final Spinner spinnerKelas = root.findViewById(R.id.spinnerKelas);
        final Spinner spinnerJurusan = root.findViewById(R.id.spinnerJurusan);
        final Spinner spinnerPelajaran = root.findViewById(R.id.spinnerPelajaran);

        if (getArguments() != null)
        {

            spinnerJenjang.setAdapter(getAdapter(getArguments().getString("jenjang")));
            spinnerKelas.setAdapter(getAdapter(getArguments().getString("kelas")));
            spinnerJurusan.setAdapter(getAdapter(getArguments().getString("jurusan")));
            spinnerPelajaran.setAdapter(getAdapter(getArguments().getString("pelajaran")));
        }

        else {

            final SpinnerArrayAdapter kelasAdapter = getAdapter("Kelas");
            spinnerKelas.setAdapter(kelasAdapter);

            spinnerJurusan.setAdapter(
                    getAdapter(
                            getResources().getStringArray(R.array.spinner_jurusan_arrays)
                    )
            );

            spinnerJenjang.setAdapter(
                    getAdapter(
                            getResources().getStringArray(R.array.spinner_jenjang_arrays)
                    )
            );

            spinnerJenjang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();

                    if (selectedItem.equals("SMP")){
                        kelasAdapter.resetList(new ArrayList<String>(
                                        Arrays.asList(getResources().getStringArray(R.array.spinner_kelas_arrays_smp))
                                )
                        );
                    } else if (selectedItem.equals("SMA")){
                        kelasAdapter.resetList(new ArrayList<String>(
                                        Arrays.asList(getResources().getStringArray(R.array.spinner_kelas_arrays_sma))
                                )
                        );
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerPelajaran.setAdapter(
                    getAdapter(
                            getResources().getStringArray(R.array.spinner_pelajaran_arrays)
                    )
            );

        }

        final TextView dayText = root.findViewById(R.id.dayText);
        final TextView monthText = root.findViewById(R.id.monthText);
        final TextView yearText = root.findViewById(R.id.yearText);

        createButton = root.findViewById(R.id.createButton);
        createButton.setOnClickListener(view -> {

            String jenjang, kelas, jurusan, pelajaran;
            jenjang = (String) spinnerJenjang.getSelectedItem();
            kelas = (String) spinnerKelas.getSelectedItem();
            jurusan = (String) spinnerJurusan.getSelectedItem();
            pelajaran = (String) spinnerPelajaran.getSelectedItem();

            if (getArguments() == null){

                // Validation only if fragment started from MyClassFragment
                // ###################################################################################################
                boolean jenjangBool = jenjang.equals(spinnerJenjang.getItemAtPosition(0));
                boolean kelasBool = kelas.equals(spinnerKelas.getItemAtPosition(0));
                boolean jurusanBool = jurusan.equals(spinnerJurusan.getItemAtPosition(0));
                boolean pelajaranBool = pelajaran.equals(spinnerPelajaran.getItemAtPosition(0));

                if (jenjangBool || kelasBool || pelajaranBool) {
                    Toast.makeText(getContext(), "Silakan pilih item untuk semua list", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spinnerJenjang.getSelectedItem().equals("SMP")){

                } else if (spinnerJenjang.getSelectedItem().equals("SMA")){
                    if (jurusanBool){
                        Toast.makeText(getContext(), "Silakan pilih item untuk semua list", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // ###################################################################################################

            }


            DatabaseReference kelasRef = null;

            if (jenjang.equals("SMP")){
                kelasRef = databaseReference.child("kelas").child(jenjang).child(kelas);
            } else if (jenjang.equals("SMA")) {
                kelasRef = databaseReference.child("kelas").child(jenjang).child(jurusan).child(kelas);
            }

            String kelasKeyID = kelasRef.push().getKey();
            kelasRef.child(kelasKeyID).child("lobby").child(userID).child("status")
                    .setValue("class-starter");

            kelasRef.child(kelasKeyID).child("pelajaran").setValue(pelajaran);

            DatabaseReference userRef = databaseReference
                    .child("users").child("siswa").child(userID).child("kelas")
                    .child(kelasKeyID).child("status");
            userRef.setValue("class-starter");


            Kelas kelasObj = new Kelas(kelasKeyID);
            kelasObj.setJenjang(jenjang);
            kelasObj.setPelajaran(pelajaran);
            kelasObj.setKelas(kelas);
            kelasObj.setJurusan(jenjang.equals("SMA")? jurusan : "");
            kelasObj.setLokasi(selectedLocation);
            kelasObj.setWaktu(selectedTime);

            DatabaseReference kelasDetailRef = databaseReference
                    .child("detail-kelas")
                    .child(kelasKeyID);

            kelasDetailRef.setValue(kelasObj);


            Intent intent = new Intent(getActivity(), LobbyKelasActivity.class);
            intent.putExtra("KELAS-OBJECT", kelasObj);
            intent.putExtra("DATE-DAY", dayText.getText().toString());
            intent.putExtra("DATE-MONTH", monthText.getText().toString());
            intent.putExtra("DATE-YEAR", yearText.getText().toString());
            startActivityForResult(intent, 1);
        });

        dateContainer = root.findViewById(R.id.dateContainer);
        dateContainer.setOnClickListener(view -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()),
                    (datePickerView, year, monthOfYear, dayOfMonth) -> {

                            dayText.setText(String.valueOf(dayOfMonth));
                            monthText.setText(String.valueOf(monthOfYear + 1));
                            yearText.setText(String.valueOf(year - 2000));

                            selectedTime.put("hari", String.valueOf(dayOfMonth));
                            selectedTime.put("bulan", String.valueOf(monthOfYear + 1));
                            selectedTime.put("tahun", String.valueOf(year));

                    }, mYear, mMonth, mDay);

            datePickerDialog.show();
        });

        locationTextView = root.findViewById(R.id.address);
        selectLocation = root.findViewById(R.id.selectLocation);

        selectLocation.setOnClickListener(view -> {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            LatLng pointA = new LatLng(-7.949612, 112.606576);
            LatLng pointB = new LatLng(-7.949612, 112.619445);
            builder.setLatLngBounds(new LatLngBounds(pointA, pointB));
            try {
                startActivityForResult(builder.build(Objects.requireNonNull(getActivity())), PLACE_PICKER_REQUEST);
                selectLocation.setEnabled(false);
                viewDetail.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        });

        viewDetail = root.findViewById(R.id.viewDetail);
        viewDetail.setOnClickListener(view -> {

            AlertDialog detailDialog = Utils.buildLocationDetailDialog(getActivity(), placename, address);
            detailDialog.show();

        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fragmentManager.popBackStack();
        }


        // https://github.com/androidmads/PlacePickerSample/blob/master/app/src/main/java/com/androidmads/placepickersample/MainActivity.java
        if (requestCode == PLACE_PICKER_REQUEST) {

            selectLocation.setEnabled(true);
            progressBar.setVisibility(View.GONE);


            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, Objects.requireNonNull(getActivity()));
                placename = String.format("%s", place.getName());
                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);
                address = String.format("%s", place.getAddress());

                StringBuilder stBuilder = new StringBuilder();
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);

                selectedLocation.put("latitude", latitude);
                selectedLocation.put("longitude", longitude);
                selectedLocation.put("namatempat", placename);
                selectedLocation.put("alamat", address);

                viewDetail.setVisibility(View.VISIBLE);

                if (placename.contains("°") && placename.contains("\"S") && placename.contains("\"E")){
                    Log.d("MYAPP", stBuilder.toString());
//                    Toast.makeText(getActivity().getApplicationContext(), stBuilder.toString(), Toast.LENGTH_LONG).show();
                    locationTextView.setText(address);
                } else {
                    Log.d("MYAPP", stBuilder.toString());
//                    Toast.makeText(getActivity().getApplicationContext(), stBuilder.toString(), Toast.LENGTH_LONG).show();
                    locationTextView.setText(placename);
                }
            }
        }
    }

}
package tutor.ajakteman.siswa.kelas.umum.hasilcarikelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import tutor.ajakteman.R;
import tutor.ajakteman.siswa.kelas.umum.kelasumumbaru.KelasUmumBaruFragment;

public class HasilCariKelasPresenter implements HasilCariKelasContract.Presenter {

    private HasilCariKelasContract.View mView;

    public HasilCariKelasPresenter(HasilCariKelasContract.View view){
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void startFragment(Bundle arguments) {
        KelasUmumBaruFragment fragment = new KelasUmumBaruFragment();
        fragment.setArguments(arguments);

        FragmentManager fragmentManager = ((Fragment)mView).getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void start() {

    }
}

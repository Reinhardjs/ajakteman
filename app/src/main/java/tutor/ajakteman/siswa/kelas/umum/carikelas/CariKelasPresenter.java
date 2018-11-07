package tutor.ajakteman.siswa.kelas.umum.carikelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import tutor.ajakteman.R;
import tutor.ajakteman.siswa.kelas.umum.hasilcarikelas.HasilCariKelasFragment;

public class CariKelasPresenter implements CariKelasContract.Presenter {

    private CariKelasContract.View mCariKelasView;

    public CariKelasPresenter(CariKelasContract.View cariKelasView) {
        mCariKelasView = cariKelasView;
        mCariKelasView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void startFragment(Bundle arguments) {
        HasilCariKelasFragment fragment = new HasilCariKelasFragment();
        fragment.setArguments(arguments);

        FragmentManager fragmentManager = ((Fragment)mCariKelasView).getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.root_frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

package tutor.ajakteman.siswa.kelas.umum.carikelas;

import android.os.Bundle;

import tutor.ajakteman.BasePresenter;
import tutor.ajakteman.BaseView;

public class CariKelasContract {

    interface Presenter extends BasePresenter {

        public void startFragment(Bundle arguments);

    }

    interface View extends BaseView<Presenter> {

    }

}

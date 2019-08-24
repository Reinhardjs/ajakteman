package tutor.ajakteman.siswa.kelas.umum.hasilcarikelas;

import android.os.Bundle;

import tutor.ajakteman.BasePresenter;
import tutor.ajakteman.BaseView;

public class HasilCariKelasContract {

    public interface Presenter extends BasePresenter {

        void startFragment(Bundle arguments);

    }

    public interface View extends BaseView<Presenter> {



    }

}

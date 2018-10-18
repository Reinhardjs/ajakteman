package tutor.ajakteman.siswa.kelas.umum.carikelas;

public class CariKelasPresenter implements CariKelasContract.Presenter {

    private CariKelasContract.View mCariKelasView;

    public CariKelasPresenter(CariKelasContract.View cariKelasView) {
        mCariKelasView = cariKelasView;
        mCariKelasView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}

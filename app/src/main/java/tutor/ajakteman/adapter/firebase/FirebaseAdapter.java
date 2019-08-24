package tutor.ajakteman.adapter.firebase;

import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.RestrictTo;

import com.firebase.ui.database.ChangeEventListener;
import com.firebase.ui.database.ObservableSnapshotArray;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
interface FirebaseAdapter<T> extends ChangeEventListener, LifecycleObserver {

    void startListening();

    void stopListening();

    ObservableSnapshotArray<T> getSnapshots();

    T getItem(int position);

}
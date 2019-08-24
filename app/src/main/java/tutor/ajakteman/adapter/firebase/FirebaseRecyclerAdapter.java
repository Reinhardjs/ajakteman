package tutor.ajakteman.adapter.firebase;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public abstract class FirebaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements FirebaseAdapter<T>{

    private static final String TAG = "FirebaseRecyclerAdapter";
    private final ObservableSnapshotArray<T> mSnapshots;
    private final List<T> list, backupList;
    private boolean isFiltarable;

    public FirebaseRecyclerAdapter(FirebaseRecyclerOptions<T> options, boolean isFiltarable) {
        mSnapshots = options.getSnapshots();
        list = new ArrayList<>();
        backupList = new ArrayList<>();

        if (options.getOwner() != null) {
            options.getOwner().getLifecycle().addObserver(this);
        }

        this.isFiltarable = isFiltarable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListening() {
        if (!mSnapshots.isListening(this)) {
            mSnapshots.addChangeEventListener(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopListening() {
        mSnapshots.removeChangeEventListener(this);
        notifyDataSetChanged();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void cleanup(LifecycleOwner source) {
        source.getLifecycle().removeObserver(this);
    }

    @Override
    public void onChildChanged(ChangeEventType type, DataSnapshot snapshot, int newIndex, int oldIndex) {
        if (mSnapshots.size() >= newIndex) return;
        T model = mSnapshots.get(newIndex);
        onChildUpdate(model, type, snapshot, newIndex, oldIndex);
    }

    protected void onChildUpdate(T model, ChangeEventType type, DataSnapshot snapshot, int newIndex, int oldIndex) {
        switch (type) {
            case ADDED:
                addItem(snapshot.getKey(), model);
                notifyItemInserted(newIndex);
                break;
            case CHANGED:
                addItem(snapshot.getKey(), model, newIndex);
                notifyItemChanged(newIndex);
                break;
            case REMOVED:
                removeItem(newIndex);
                notifyItemRemoved(newIndex);
                break;
            case MOVED:
                moveItem(snapshot.getKey(), model, newIndex, oldIndex);
                notifyItemMoved(oldIndex, newIndex);
                break;
            default:
                throw new IllegalStateException("Incomplete case statement");
        }
    }

    private void moveItem(String key, T t, int newIndex, int oldIndex) {
        list.remove(oldIndex);
        list.add(newIndex, t);
//        if (isFiltarable) {
//            backupList.remove(oldIndex);
//            backupList.add(newIndex, t);
//        }
    }

    private void removeItem(int newIndex) {
        list.remove(newIndex);
//        if (isFiltarable)
//            backupList.remove(newIndex);
    }

    private void addItem(String key, T t, int newIndex) {
        list.remove(newIndex);
        list.add(newIndex, t);
//        if (isFiltarable) {
//            backupList.remove(newIndex);
//            backupList.add(newIndex, t);
//        }
    }

    private void addItem(String id, T t) {
        list.add(t);
//        if (isFiltarable)
//            backupList.add(t);
    }

    @Override
    public void onDataChanged() {

    }

    @Override
    public void onError(DatabaseError error) {
        Log.w(TAG, error.toException());
    }

    @Override
    public ObservableSnapshotArray<T> getSnapshots() {
        return mSnapshots;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    protected abstract void onBindViewHolder(VH holder, int position, T model);

}

package tutor.ajakteman.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import tutor.ajakteman.helper.Utils;

public class KelasRecyclerView extends RecyclerView {

    public KelasRecyclerView(@NonNull Context context) {
        super(context);
    }

    public KelasRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KelasRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        heightSpec = MeasureSpec.makeMeasureSpec((int) Utils.dpsToPixels(300), MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, heightSpec);
    }
}

package pl.edu.pwr.swim.chilczuk.filmcatalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by widelec on 21.05.17.
 */

public class DetailsFragmentClass2 extends Fragment {
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.activity_film_detail, container, false);
    }
}

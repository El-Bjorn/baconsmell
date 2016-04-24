package org.bjorn.smellslikebacon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bjorn on 4/22/16.
 */
public class DirectionsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_directions,container,false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.directionsLayout);
        String[] directions = Recipes.directions[index].split("`");
        displayDirections(directions,linearLayout);

        return view;
    }

    private void displayDirections(String[] directions, ViewGroup container){
        for (String step : directions){
            TextView textView = new TextView(getActivity());
            textView.setText("- "+step);
            container.addView(textView);
        }
    }
}

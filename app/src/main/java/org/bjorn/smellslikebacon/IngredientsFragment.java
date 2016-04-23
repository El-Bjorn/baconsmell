package org.bjorn.smellslikebacon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by bjorn on 4/22/16.
 */
public class IngredientsFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "checked_boxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ingredientsLayout);
        String [] ingredients = Recipes.ingredients[index].split("`");
        mCheckBoxes = new CheckBox[ingredients.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        addCheckboxes(ingredients, linearLayout, checkedBoxes);

        //addCheckboxes(ingredients,linearLayout);

        return view;
    }

    private void addCheckboxes(String[] ingredients, ViewGroup container, boolean[] checkedBoxes){
        int i = 0;
        for (String ingredient : ingredients){
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setBackgroundColor(Color.WHITE);
            mCheckBoxes[i].setTextColor(Color.BLACK);
            mCheckBoxes[i].setPadding(8,16,8,16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText("      " + ingredient);
            container.addView(mCheckBoxes[i]);
            if (checkedBoxes[i]){
                mCheckBoxes[i].toggle();
            }
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes){
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES,stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}

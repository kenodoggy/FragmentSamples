package com.kenodoggy.navigationdrawer;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private static final String POSITION = "nav_item_position";
    private static final String TITLE = "item_title";
    private static final String IMAGE = "item_image";
    private static final String DESCRIPTION = "item_description";

    private int mPosition;
    private String mTitle;
    private String mImage;
    private String mDescription;

    /**
     * factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(JSONObject puppy, int position) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        args.putString(TITLE, puppy.optString("title"));
        args.putString(IMAGE, puppy.optString("image"));
        args.putString(DESCRIPTION, puppy.optString("description"));
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(POSITION);
            mTitle = getArguments().getString(TITLE);
            mImage = getArguments().getString(IMAGE);
            mDescription = getArguments().getString(DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView title = (TextView) view.findViewById(R.id.fragment_detail_title);
        TextView description = (TextView) view.findViewById(R.id.fragment_detail_description);
        ImageView imageView = ((ImageView) view.findViewById(R.id.fragment_detail_image));

        title.setText(mTitle);
        description.setText(mDescription);
        int imageId = getResources().getIdentifier(mImage, "drawable", getActivity().getPackageName());
        imageView.setImageResource(imageId);

        getActivity().setTitle(mTitle);

        return view;
    }


}

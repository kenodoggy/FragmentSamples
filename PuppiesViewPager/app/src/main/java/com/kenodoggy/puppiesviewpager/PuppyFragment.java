package com.kenodoggy.puppiesviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class PuppyFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_POSITION = "position";
    private int mPageNumber = 0;

    private String[] mPuppyImages =
        {
            "corgi",
            "dachshund",
            "golden_retriever",
            "pomeranian",
            "pug",
            "yorkie"
        };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position
     * @return A new instance of fragment PuppyFragment.
     */
    public static PuppyFragment newInstance(int position) {
        PuppyFragment fragment = new PuppyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public PuppyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageNumber = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_puppy, container, false);

        int imageId = getResources().getIdentifier(mPuppyImages[mPageNumber],
                "drawable", getActivity().getPackageName());
        ImageView image = (ImageView) view.findViewById(R.id.puppy_picture);
        image.setImageResource(imageId);

        return view;
    }
}

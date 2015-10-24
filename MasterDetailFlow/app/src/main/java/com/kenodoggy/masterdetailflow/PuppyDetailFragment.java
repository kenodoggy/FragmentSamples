package com.kenodoggy.masterdetailflow;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenodoggy.masterdetailflow.model.Content;
import com.kenodoggy.masterdetailflow.model.Puppy;

import org.json.JSONObject;

/**
 * A fragment representing a single Puppy detail screen.
 * This fragment is either contained in a {@link PuppyListActivity}
 * in two-pane mode (on tablets) or a {@link PuppyDetailActivity}
 * on handsets.
 */
public class PuppyDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_POSITION = "position";

    /**
     * The dummy content this fragment is presenting.
     */
    private Puppy mPuppy;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PuppyDetailFragment() {
    }

    public static PuppyDetailFragment newInstance(int position) {
        PuppyDetailFragment fragment = new PuppyDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_POSITION)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mPuppy = createPuppy(getArguments().getInt(ARG_POSITION));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mPuppy.getTitle());
            }
        }
    }

    private Puppy createPuppy(int position) {
        JSONObject pup = Content.puppiesJSON.optJSONObject(position);
        return new Puppy(
                pup.optString("title"),
                pup.optString("image"),
                pup.optString("description")
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_puppy_detail, container, false);

        // display the puppy content
        if (mPuppy != null) {

            TextView description = (TextView) view.findViewById(R.id.fragment_puppy_detail_description);
            ImageView imageView = ((ImageView) view.findViewById(R.id.fragment_puppy_detail_image));

            description.setText(mPuppy.getDescription());
            int imageId = getResources().getIdentifier(mPuppy.getImage(), "drawable", getActivity().getPackageName());
            imageView.setImageResource(imageId);

            getActivity().setTitle(mPuppy.getTitle());
        }

        return view;
    }
}

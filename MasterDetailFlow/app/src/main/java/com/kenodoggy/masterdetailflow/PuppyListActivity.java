package com.kenodoggy.masterdetailflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * An activity representing a list of Puppies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PuppyDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PuppyListFragment} and the item details
 * (if present) is a {@link PuppyDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link PuppyListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PuppyListActivity extends AppCompatActivity
        implements PuppyListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.puppy_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PuppyListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.puppy_list))
                    .setActivateOnItemClick(true);


            if (savedInstanceState == null) {
                // load the detail fragment with the first pup
                onItemSelected(0);
            }
        }
    }

    /**
     * Callback method from {@link PuppyListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int position) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            PuppyDetailFragment fragment = PuppyDetailFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.puppy_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PuppyDetailActivity.class);
            detailIntent.putExtra(PuppyDetailFragment.ARG_POSITION, position);
            startActivity(detailIntent);
        }
    }
}

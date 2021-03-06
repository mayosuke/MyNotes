package jp.mayosuke.android.mynotes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NoteListActivity extends Activity {
    private static final String TAG = NoteListActivity.class.getSimpleName();

    private static final int REQUEST_CREATE_NEW_NOTE = 0;
    public static final int REQUEST_EDIT_NOTE = 1;

    private ListFragment mListFragment;

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate():savedInstanceState=" + savedInstanceState);
        super.onCreate(savedInstanceState);
        mListFragment = (ListFragment) getFragmentManager().findFragmentById(android.R.id.content);
        if (mListFragment == null) {
            mListFragment = new MyListFragment();
        }
        mListFragment.setListAdapter(new MyListAdapter());
        getFragmentManager().beginTransaction().add(android.R.id.content, mListFragment).commit();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop()");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(TAG, "onCreateOptionsMenu():menu=" + menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected():item=" + item);

        switch (item.getItemId()) {
        case R.id.menu_new_note:
            final Intent intent = new Intent(this, NoteDetailActivity.class);
            startActivityForResult(intent, REQUEST_CREATE_NEW_NOTE);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.v(TAG, "onConfigurationChanged():newConfig=" + newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState():outState=" + outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, "onRestoreInstanceState():savedInstanceState=" + savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        Log.v(TAG, "onActivityResult():requestCode=" + requestCode + ",resultCode=" + resultCode + ",data=" + data);
        if (resultCode == RESULT_OK) {
            BaseAdapter adapter = (BaseAdapter) mListFragment.getListAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    private class MyListAdapter extends BaseAdapter {
        private final Notes mNotes = Notes.getNotes();

        @Override
        public int getCount() {
            return mNotes.getNotesCount();
        }

        @Override
        public Object getItem(int position) {
            return mNotes.getNote(position);
        }

        @Override
        public long getItemId(int position) {
            return mNotes.getNote(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view;
            if (convertView == null) {
                view = View.inflate(NoteListActivity.this, android.R.layout.simple_list_item_1, null);
                final TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setSingleLine();
                text.setEllipsize(TruncateAt.END);
            } else {
                view = convertView;
            }

            final TextView text = (TextView)view.findViewById(android.R.id.text1);
            text.setText(mNotes.getNote(position));

            return view;
        }
    }

    @TargetApi(11)
    public static class MyListFragment extends ListFragment {

        public MyListFragment() {
            // Do nothing so far
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.v(TAG, "onListItemClick():position=" +position + ",id=" + id);
            final Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
            intent.putExtra(Notes.EXTRA_NOTES_ID, position);
            startActivityForResult(intent, REQUEST_EDIT_NOTE);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.v(TAG, "onActivityResult():requestCode=" + requestCode + ",resultCode=" + resultCode + ",data=" + data);
        }
    }
}

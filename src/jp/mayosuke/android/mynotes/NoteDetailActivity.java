package jp.mayosuke.android.mynotes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NoteDetailActivity extends Activity {
    private static final String TAG = NoteDetailActivity.class.getSimpleName();

    private static final String INSTANCE_STATE_CONTENT = "jp.mayosuke.android.mynotes.NoteDetailActivity.mContent";

    private EditText mContent;

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate():savedInstanceState=" + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        mContent = (EditText)findViewById(R.id.note_detail_content);
        if (mContent == null) {
            throw new NullPointerException("You should provide EditText for this activity.");
        }
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_STATE_CONTENT)) {
            final CharSequence content = savedInstanceState.getCharSequence(INSTANCE_STATE_CONTENT);
            mContent.setText(content);
        }
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
        getMenuInflater().inflate(R.menu.activity_note_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected():item=" + item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.v(TAG, "onConfigurationChanged():newConfig=" + newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState():outState=" + outState);
        outState.putCharSequence(INSTANCE_STATE_CONTENT, mContent.getText());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, "onRestoreInstanceState():savedInstanceState=" + savedInstanceState);
        final CharSequence content = savedInstanceState.getCharSequence(INSTANCE_STATE_CONTENT);
        mContent.setText(content);
        super.onRestoreInstanceState(savedInstanceState);
    }

}

package jp.mayosuke.android.mynotes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteDetailActivity extends Activity {
    private static final String TAG = NoteDetailActivity.class.getSimpleName();

    private static final String INSTANCE_STATE_CONTENT = "jp.mayosuke.android.mynotes.NoteDetailActivity.mContent";

    private EditText mContent;
    private int mNoteId = -1;

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
        } else if (getIntent().hasExtra(Notes.EXTRA_NOTES_ID)) {
            mNoteId = getIntent().getIntExtra(Notes.EXTRA_NOTES_ID, -1);
            if (mNoteId != -1) {
                mContent.setText(Notes.getNotes().getNote(mNoteId));
            }
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
        switch (item.getItemId()) {
        case R.id.menu_save_note:
            handleSaveNote();
            break;
        case R.id.menu_delete_note:
            handleDeleteNote();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "onBackPressed()");
        handleSaveNote();
    }

    private void handleSaveNote() {
        Toast.makeText(this, "ノートを保存します。", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, getData());
        super.onBackPressed();
    }

    private void handleDeleteNote() {
        Toast.makeText(this, "ノートを削除します。", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED, getData());
        super.onBackPressed();
    }

    private Intent getData() {
        final Intent data = new Intent();
        data.putExtra(Notes.EXTRA_NOTES_ID, mNoteId);
        data.putExtra(Notes.EXTRA_NOTE_CONTENT, mContent.getText().toString());
        return data;
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

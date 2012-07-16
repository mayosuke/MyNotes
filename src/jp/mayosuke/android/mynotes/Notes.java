package jp.mayosuke.android.mynotes;

import java.util.ArrayList;
import java.util.List;

class Notes {
    private static final Notes sNotes = new Notes();

    static Notes getNotes() {
        return sNotes;
    }

    private Notes() {}  // Avoid instantiation

    private final List<CharSequence> mContents = new ArrayList<CharSequence>();

    {
        this.mContents.add("dummy1dummy1dummy1dummy1dummy1dummy1");
        this.mContents.add("dummy2dummy2dummy2dummy2dummy2dummy2");
        this.mContents.add("dummy3dummy3dummy3dummy3dummy3dummy3");
    }

    CharSequence getNote(final int position) {
        return mContents.get(position);
    }

    int getNotesCount() {
        return mContents.size();
    }

    void addNote(final CharSequence content) {
        mContents.add(content);
    }

    void removeNote(final int position) {
        mContents.remove(position);
    }

    void clearNotes() {
        mContents.clear();
    }
}

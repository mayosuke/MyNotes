package jp.mayosuke.android.mynotes;

import java.util.ArrayList;
import java.util.List;

public class Notes {
    private static final List<String> sNotes = new ArrayList<String>();
    public static List<String> getNotes() {
        return sNotes;
    }

    private Notes() {}  // Avoid instantiation
}

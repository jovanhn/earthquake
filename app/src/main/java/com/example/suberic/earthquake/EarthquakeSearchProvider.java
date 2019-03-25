package com.example.suberic.earthquake;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class EarthquakeSearchProvider extends ContentProvider {


    private static final int SEARCH_SUGGESTIONS = 1;
    private static final String EARTHQUAKE_AUTH = "com.example.suberic.earthquake";

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(EARTHQUAKE_AUTH, SearchManager.SUGGEST_URI_PATH_QUERY,
                SEARCH_SUGGESTIONS);
        uriMatcher.addURI(EARTHQUAKE_AUTH, SearchManager.SUGGEST_URI_PATH_QUERY + "/*",
                SEARCH_SUGGESTIONS);
        uriMatcher.addURI(EARTHQUAKE_AUTH, SearchManager.SUGGEST_URI_PATH_SHORTCUT,
                SEARCH_SUGGESTIONS);
        uriMatcher.addURI(EARTHQUAKE_AUTH, SearchManager.SUGGEST_URI_PATH_SHORTCUT + "/*",
                SEARCH_SUGGESTIONS);
    }
    @Override
    public boolean onCreate() {
        EarthquakeDatabaseAccessor.getInstance(getContext().getApplicationContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) == SEARCH_SUGGESTIONS) {
            String searchQuery = "%" + uri.getLastPathSegment() + "%";
            EarthquakeDAO earthquakeDAO
                    = EarthquakeDatabaseAccessor
                    .getInstance(getContext().getApplicationContext())
                    .earthquakeDAO();
            Cursor cursor = earthquakeDAO.generateSearchSuggestions(searchQuery);

            // Return a cursor of search suggestions.
            return cursor;
        }
        return null;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case SEARCH_SUGGESTIONS :
                return SearchManager.SUGGEST_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

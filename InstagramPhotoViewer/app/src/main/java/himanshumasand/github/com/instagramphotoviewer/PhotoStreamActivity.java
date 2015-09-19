package himanshumasand.github.com.instagramphotoviewer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotoStreamActivity extends ActionBarActivity {

    public static final String URL_GET_PHOTOS = "https://api.instagram.com/v1/media/popular?client_id=1bd13cb5cfd34ab59a6ee0b514dd41d1";

    private ArrayList<Photo> photos;
    private PhotosAdapter photosAdapter;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_stream);

        //Link adapter to view
        photos = new ArrayList<>();
        photosAdapter = new PhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);

        //send api request
        fetchPopularPhotos();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchPopularPhotos();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void  fetchPopularPhotos() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_GET_PHOTOS, null, new JsonHttpResponseHandler() {
            //onSuccess
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("DEBUG" , "RECEIVED: Data for popular photos: " + response.toString());
                JSONArray photosJSON = null;
                photos = new ArrayList<>();
                try {
                    photosJSON = response.getJSONArray("data");
                    for(int i = 0; i < photosJSON.length(); i++) {
                        createPhotoObjectFromJSON(photosJSON.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                photosAdapter.clear();
                photosAdapter.addAll(photos);
                photosAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);

            }

            //onFailure
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //Log Error
            }
        });
    }

    private void createPhotoObjectFromJSON(JSONObject object) {
        try {
            String username = object.getJSONObject("user").getString("username");
            String userProfilePicUrl = object.getJSONObject("user").getString("profile_picture");
            String caption = object.getJSONObject("caption").getString("text");
            String imageUrl = object.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            int imageWidth = object.getJSONObject("images").getJSONObject("standard_resolution").getInt("width");
            int imageHeight = object.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
            long createdTime = object.getLong("created_time");
            int likesCount = object.getJSONObject("likes").getInt("count");

            Photo photo = new Photo(username, userProfilePicUrl, caption, imageUrl, imageWidth, imageHeight, createdTime, likesCount);
            photos.add(photo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_stream, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

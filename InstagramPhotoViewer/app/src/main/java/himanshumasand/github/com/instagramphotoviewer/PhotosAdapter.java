package himanshumasand.github.com.instagramphotoviewer;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Himanshu on 9/16/2015.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    public PhotosAdapter(Context context, List<Photo> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }
}

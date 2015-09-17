package himanshumasand.github.com.instagramphotoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Himanshu on 9/16/2015.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private static class ViewHolder {
        ImageView image;
        TextView caption;
    }

    public PhotosAdapter(Context context, List<Photo> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);

        final ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.getImageUrl()).into(viewHolder.image);
        viewHolder.caption.setText(photo.getCaption());

        return convertView;
    }
}

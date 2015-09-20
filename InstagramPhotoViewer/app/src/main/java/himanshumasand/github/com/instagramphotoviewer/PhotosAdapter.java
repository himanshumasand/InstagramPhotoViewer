package himanshumasand.github.com.instagramphotoviewer;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Himanshu on 9/16/2015.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private static class ViewHolder {
        ImageView profilePic;
        TextView userName;
        TextView time;
        ImageView image;
        TextView likes;
        TextView usernameBottom;
        TextView caption;
        TextView commentUsername;
        TextView comment;
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
            viewHolder.profilePic = (ImageView) convertView.findViewById(R.id.ivProfilePic);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tvTime);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.tvLikesCount);
            viewHolder.usernameBottom = (TextView) convertView.findViewById(R.id.tvUserNameCaption);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.commentUsername = (TextView) convertView.findViewById(R.id.tvCommentUsername);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.tvComment);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.getImageUrl()).placeholder(getContext().getResources().getDrawable(R.drawable.default_image)).resize(getContext().getResources().getDisplayMetrics().widthPixels, 0).into(viewHolder.image);

        viewHolder.profilePic.setImageResource(0);
        Picasso.with(getContext()).load(photo.getUserProfilePicUrl()).into(viewHolder.profilePic);

        getCaptionTextWithColors(viewHolder.caption, photo.getCaption());

        viewHolder.userName.setText(photo.getUsername());
        viewHolder.usernameBottom.setText(photo.getUsername());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        viewHolder.likes.setText(formatter.format(photo.getLikesCount()) + " likes");

        viewHolder.time.setText(getTimerValue(photo.getCreatedTime()));

        viewHolder.commentUsername.setText(photo.getCommentUsername());
        viewHolder.comment.setText(photo.getComment());


        return convertView;
    }

    private TextView getCaptionTextWithColors(TextView coloredCaption, String caption) {
        coloredCaption.setText("");
        String[] words = caption.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if(words[i].charAt(0) == '@' || words[i].charAt(0) == '#') {
                Spannable word = new SpannableString(words[i]);
                word.setSpan(new ForegroundColorSpan(0xFF38688F), 0, word.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                coloredCaption.append(word);
                coloredCaption.append(" ");
            }
            else {
                coloredCaption.append(words[i] + " ");
            }
        }
        return coloredCaption;
    }

    private String getTimerValue(long createdTime) {
        String time;

        long currentUnixTime = System.currentTimeMillis() / 1000L;
        long timeDifference = (currentUnixTime - createdTime);

        int days = (int) timeDifference/(60*60*24);
        int hours = (int)timeDifference/(60*60);
        int mins = (int) timeDifference/(60);

        if(days > 0) {
            time = String.valueOf(days) + "d";
        }
        else if(hours > 0) {
            time = String.valueOf(hours) + "h";
        }
        else {
            time = String.valueOf(mins) + "m";
        }
        return time;
    }
}

package himanshumasand.github.com.instagramphotoviewer;

/**
 * Created by Himanshu on 9/15/2015.
 */
public class Photo {

    private String username;
    private String userProfilePicUrl;
    private String caption;
    private String imageUrl;
    private int imageWidth;
    private int imageHeight;
    private long createdTime;
    private int likesCount;

    public Photo(String username, String userProfilePic, String caption, String imageUrl, int imageWidth, int imageHeight, long createdTime, int likesCount) {
        this.username = username;
        this.userProfilePicUrl = userProfilePic;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.createdTime = createdTime;
        this.likesCount = likesCount;
    }

    public String getUsername() {
        return username;
    }

    public String getCaption() {
        return caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getUserProfilePicUrl() {
        return userProfilePicUrl;
    }

    public long getCreatedTime() {
        return createdTime;
    }
}

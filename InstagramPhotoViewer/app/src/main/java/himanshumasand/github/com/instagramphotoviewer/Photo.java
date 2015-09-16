package himanshumasand.github.com.instagramphotoviewer;

/**
 * Created by Himanshu on 9/15/2015.
 */
public class Photo {

    private String username;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount;

    public Photo(String username, String caption, String imageUrl, int imageHeight, int likesCount) {
        this.username = username;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
    }
}

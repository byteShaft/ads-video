package byteshaft.com.advideos;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;

public class ThumbnailCreationTask extends AsyncTask<Void, Void, Bitmap> {

    private Context mContext;
    private int mPosition;
    private MainActivity.ViewHolder mHolder;

    public ThumbnailCreationTask(Context context, MainActivity.ViewHolder holder, int position) {
        mContext = context;
        mPosition = position;
        mHolder = holder;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {

        return ThumbnailUtils.createVideoThumbnail
                (MainActivity.path + "/Videos/" + MainActivity.filesInFolder.get(mHolder.position),
                        MediaStore.Video.Thumbnails.MICRO_KIND);

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (mHolder.position == mPosition) {
            mHolder.thumbnail.setImageBitmap(bitmap);
        }
    }
}

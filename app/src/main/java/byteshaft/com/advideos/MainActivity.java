package byteshaft.com.advideos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    public File path = Environment.getExternalStorageDirectory();
    public static ArrayList<String> filesInFolder;
    private CustomVideoView customVideoView;
    public static final String KEY = "path";
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.video_list);
        filesInFolder = GetFiles(path + "/Videos");
        ArrayAdapter<String> arrayAdapter = new VideoListAdapter(this,
                android.R.layout.simple_list_item_1, filesInFolder);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new ListItemCLickListener());
    }

    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File file = new File(DirectoryPath);
        file.mkdirs();
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
            MyFiles.add(files[i].getName());
        return MyFiles;
    }

    private class ListItemCLickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), CustomVideoView.class);
            intent.putExtra(KEY, path + "/Videos/" + parent.getItemAtPosition(position).toString());
            intent.putExtra("position",position);
            startActivity(intent);
        }
    }


class VideoListAdapter extends ArrayAdapter<String> {

    public VideoListAdapter(Context context, int resource, ArrayList<String> videos) {
        super(context, resource, videos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = getLayoutInflater();
            convertView = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail
                (path + "/Videos/" + filesInFolder.get(position), MediaStore.Video.Thumbnails.MICRO_KIND);
        holder.thumbnail.setImageBitmap(bitmap);
        return convertView;
    }

}

class ViewHolder {
    public ImageView thumbnail;
}
}

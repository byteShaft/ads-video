package byteshaft.com.advideos;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    public File path = Environment.getExternalStorageDirectory();
    ArrayList<String> FilesInFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.video_list);
        FilesInFolder = GetFiles(path + "/Videos");
        ArrayAdapter<String> arrayAdapter = new VideoListAdapter(this,
                android.R.layout.simple_list_item_1, FilesInFolder);
        mListView.setAdapter(arrayAdapter);
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
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.Thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}

class ViewHolder {
    public TextView title;
    public ImageView thumbnail;
}
}

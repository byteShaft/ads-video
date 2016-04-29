package byteshaft.com.advideos;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class CustomVideoView extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    public VideoView videoView;
    public String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view);
        videoView = (VideoView) findViewById(R.id.view);
        String path = getIntent().getStringExtra(MainActivity.KEY);
        position = getIntent().getStringExtra(MainActivity.POSITION);
        videoView.setVideoPath(path);
        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
    }
}

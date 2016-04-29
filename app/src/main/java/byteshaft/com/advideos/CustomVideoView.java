package byteshaft.com.advideos;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
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

    @TargetApi(19)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (android.os.Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }



    private void enterPasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CustomVideoView.this);
        // Setting Dialog Title
        alertDialog.setTitle("Enter Password");

        // outside touch disable
        alertDialog.setCancelable(false);

        final EditText input = new EditText(CustomVideoView.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                    }
                });

        final AlertDialog dialog = alertDialog.create();
        dialog.show();
        // Showing Alert Message
        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = input.getText().toString().trim();

                String savedPassword =  Helpers.getPreferenceManager().getString("password", "");

                if (savedPassword.equals(password)) {
                    // kill app
                    android.os.Process.killProcess(android.os.Process.myPid());
                    finish();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        enterPasswordDialog();
        super.onBackPressed();
    }
}

package byteshaft.com.advideos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class BootListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent bootIntent = new Intent(context, MainActivity.class);
        bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        bootIntent.putExtra("play", true);
        context.startActivity(bootIntent);
    }
}

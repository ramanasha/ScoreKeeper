package paparadva.scorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = LauncherActivity.class.getSimpleName();

    private static final int REQUEST_PLAYER_NUMBER = 0;
    private static final int REQUEST_PLAYER_NAMES = 1;

    public static final String EXTRA_PLAYER_NUMBER = "paparadva.scorekeeper.PLAYER_NUMBER";
    public static final String EXTRA_PLAYER_NAMES = "paparadva.scorekeeper.PLAYER_NAMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Intent getPlayerNum = new Intent(this, PlayerNumberActivity.class);
        startActivityForResult(getPlayerNum, REQUEST_PLAYER_NUMBER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case REQUEST_PLAYER_NUMBER: {
                    int playerNum = data.getIntExtra(Intent.EXTRA_RETURN_RESULT, 0);

                    Intent getNames = new Intent(this, PlayerNamesActivity.class);
                    getNames.putExtra(EXTRA_PLAYER_NUMBER, playerNum);

                    startActivityForResult(getNames, REQUEST_PLAYER_NAMES);
                    break;
                }
                case REQUEST_PLAYER_NAMES: {
                    ArrayList<String> names = data.getStringArrayListExtra(EXTRA_PLAYER_NAMES);
                    Log.d(TAG, names.toString());
                    break;
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}

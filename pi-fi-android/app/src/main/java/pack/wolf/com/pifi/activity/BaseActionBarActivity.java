package pack.wolf.com.pifi.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActionBarActivity extends AppCompatActivity {

    private Context context;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.context = this;
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setElevation(0);

        ColorDrawable newColor = new ColorDrawable(getResources().getColor(0x000000));
        newColor.setAlpha(128);
        getSupportActionBar().setStackedBackgroundDrawable(newColor);
        getSupportActionBar().setBackgroundDrawable(newColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBackButtonVisibility(Boolean visibility) {

        actionBar.setDisplayHomeAsUpEnabled(visibility);
    }
}
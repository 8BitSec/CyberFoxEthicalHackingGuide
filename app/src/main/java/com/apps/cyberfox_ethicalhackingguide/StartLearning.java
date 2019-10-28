package com.apps.cyberfox_ethicalhackingguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartLearning extends AppCompatActivity {

    private int back = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_learning);

        loadFragment(new TopicsFragment());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void loadFragment(Fragment fragment){
        if( fragment != null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment).commit();
        }
    }

    public void load_topic(View view){
        Fragment fragment = null;

        switch( view.getId() ){
            case R.id.topic1:
                fragment = new Topic1Fragment();
                break;
            case R.id.topic2:
                fragment = new Topic2Fragment();
                break;
            case R.id.topic3:
                fragment = new Topic3Fragment();
                break;
            case R.id.topic4:
                fragment = new Topic4Fragment();
                break;
            case R.id.topic5:
                fragment = new Topic5Fragment();
                break;
        }
        back = 1;
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed(){

        if( back == 0 ) finish();
        else{
            loadFragment(new TopicsFragment());
            back = 0;
        }
    }
}

package comp3350.ims.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import comp3350.ims.R;


public class JobPosition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_position);
    }

    public void buttonCreateNewItemOnClick(View v) {
        Intent itemCreateIntent = new Intent(JobPosition.this, ItemCreateActivity.class);
        JobPosition.this.startActivity(itemCreateIntent);
    }

    public void buttonCategoryOnClick(View v) {
        Intent coursesIntent = new Intent(JobPosition.this, CategoryActivity.class);
        JobPosition.this.startActivity(coursesIntent);
    }

    public void buttonLocationOnClick(View v) {
        Intent coursesIntent = new Intent(JobPosition.this, LocationActivity.class);
        JobPosition.this.startActivity(coursesIntent);
    }

    public void buttonInventoryOnClick(View v) {
        Intent coursesIntent = new Intent(JobPosition.this, ActiveInventoryActivity.class);
        JobPosition.this.startActivity(coursesIntent);
    }

    @Override
    public void onBackPressed(){
        Intent coursesIntent = new Intent(this, HomeActivity.class);
        this.startActivity(coursesIntent);
    }
}
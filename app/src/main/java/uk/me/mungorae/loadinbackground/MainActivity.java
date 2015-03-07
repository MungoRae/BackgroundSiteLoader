package uk.me.mungorae.loadinbackground;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.mr_intro)
    TextView introductionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        introductionTextView.setText("Welcome to the test loading in background app.");
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.mr_test_button)
    public void onTestButtonClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.bbc.co.uk"));
        startActivity(intent);
    }
}

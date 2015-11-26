package si.dlabs.friexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Retrofit.Builder;
import si.dlabs.friexample.data.api.ApiService;
import si.dlabs.friexample.data.api.model.JokeResponse;
import si.dlabs.friexample.data.api.model.JokeValueResponse;

public class MainActivity extends AppCompatActivity implements OnClickListener, Callback<JokeResponse> {

    private LinearLayout layoutRoot;
    private EditText etName;
    private EditText etSurname;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        layoutRoot = (LinearLayout) findViewById(R.id.layout_root);
        etName = (EditText) findViewById(R.id.name);
        etSurname = (EditText) findViewById(R.id.surname);
        tvName = (TextView) findViewById(R.id.text);

        findViewById(R.id.send).setOnClickListener(this);
    }

    private ApiService provideApiService() {
        Retrofit retrofit = new Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    @Override public void onClick(View v) {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();

        Call<JokeResponse> randomJoke = provideApiService().getRandomJoke(name, surname);
        randomJoke.enqueue(this);
    }

    @Override public void onResponse(Response<JokeResponse> response, Retrofit retrofit) {
        if (response.isSuccess() && response.body() != null && response.body().getValue() != null) {
            JokeValueResponse value = response.body().getValue();

            if (!TextUtils.isEmpty(value.getJoke())) {
                Spanned text = Html.fromHtml(value.getJoke());
                tvName.setText(text);
            }
        }
    }

    @Override public void onFailure(Throwable t) {
        Snackbar.make(layoutRoot, R.string.api_error, Snackbar.LENGTH_SHORT).show();
    }
}

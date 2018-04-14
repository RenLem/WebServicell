package omy.boston.webservicell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import omy.boston.webservicell.models.Movie;
import omy.boston.webservicell.rest.IMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Movie> {

    private IMovies iMovies;
    private Button btnGetWebData;
    private EditText editText_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_movie = (EditText) findViewById(R.id.editText_movie);
        btnGetWebData = (Button) findViewById(R.id.button_getWebData);
        btnGetWebData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_movie.getText().toString().length() > 0){
                    getMovie(editText_movie.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "Type something", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setRestAdapter();
    }

    private void getMovie(String title){
        iMovies.getMovie(title).enqueue(this);
    }

    private void setRestAdapter(){
        Retrofit retrofitAdp = new Retrofit.Builder()
                .baseUrl(IMovies.ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iMovies = retrofitAdp.create(IMovies.class);
    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {
        Movie movie = response.body();
        StringBuilder msg = new StringBuilder();
        msg.append(movie.getTitle() + "\n");
        msg.append(movie.getYear() + "\n");
        msg.append(movie.getDirector() + "\n");
        msg.append(movie.getGenre() + "\n");
        msg.append(movie.getCountry());
        Toast.makeText(getApplicationContext(), msg.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Not found!", Toast.LENGTH_LONG).show();
    }
}

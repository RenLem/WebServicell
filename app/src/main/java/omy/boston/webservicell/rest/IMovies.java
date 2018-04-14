package omy.boston.webservicell.rest;

import omy.boston.webservicell.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LosFrancisco on 23-Mar-17.
 */

public interface IMovies {

    public static final String ENDPOINT_URL = "http://www.omdbapi.com";

    @GET("/")
    Call<Movie> getMovie(@Query("t") String movieName);
}


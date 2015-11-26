package si.dlabs.friexample.data.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import si.dlabs.friexample.data.api.model.JokeResponse;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public interface ApiService {

    @GET("jokes/random?limitTo=[nerdy]") Call<JokeResponse> getRandomJoke(
            @Query("firstName") String firstName,
            @Query("lastName") String lastName);
}

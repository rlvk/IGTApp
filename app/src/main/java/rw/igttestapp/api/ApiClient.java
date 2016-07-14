package rw.igttestapp.api;


import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import rw.igttestapp.BuildConfig;
import rw.igttestapp.datamodel.GameResponse;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class ApiClient {

    private static final String GAME_REQUEST_PATH = "u/49130683/nativeapp-test.json";

    private static ApiClient _apiClient;

    private final RequestQueue mQueue;

    private ApiClient(Context context) {

        mQueue = Volley.newRequestQueue(context);
    }

    /**
     * Get games available
     *
     * @param responseListener {@link Response.Listener<GameResponse>}
     * @param errorListener    {@link Response.ErrorListener}
     */
    public void getGames(Response.Listener<GameResponse> responseListener, Response.ErrorListener errorListener) {
        Uri.Builder builder = Uri.parse(BuildConfig.BASE_URL)
                .buildUpon().path(GAME_REQUEST_PATH);
        callAPI(builder.build().toString(), Request.Method.GET, GameResponse.class, responseListener, errorListener);
    }

    /**
     * Create base GsonRequest and add it to the queue.
     *
     * @param url              Request url
     * @param method           Request method [GET,POST,PUT,DELETE]
     * @param clazz            {@link Class<T>}
     * @param responseListener {@link Response.Listener}
     * @param errorListener    {@link Response.ErrorListener}
     */
    private <T> void callAPI(String url, int method, Class<T> clazz, Response.Listener responseListener, Response.ErrorListener errorListener) {
        GsonRequest gsonRequest = new GsonRequest(url, method, clazz, null, responseListener, errorListener);
        mQueue.add(gsonRequest);
    }

    /**
     * Cancels all requests associated with this class
     */
    public void cancelAllRequests() {
        mQueue.cancelAll(this);
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @param context Context
     * @return singleton instance
     */
    public static ApiClient getInstance(Context context) {
        if (_apiClient == null) {
            _apiClient = new ApiClient(context);
        }
        return _apiClient;
    }
}

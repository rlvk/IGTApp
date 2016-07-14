package rw.igttestapp.api;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.android.volley.Response.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class GsonRequest<T> extends Request<T> {

//    private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Listener<T> listener;

    /**
     * Constructor. Make a request and return a parsed object from JSON.
     *
     * @param method  Request Method
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, int method, Class<T> clazz, Map<String, String> headers,
                       Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

//    private static class DateDeserializer implements JsonDeserializer<Date> {
//
//        private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//
//        static {
//            format.setTimeZone(TimeZone.getTimeZone("UTC"));
//        }
//
//        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            try {
//                return format.parse(json.getAsString());
//            } catch (ParseException e) {
//                throw  new JsonParseException(json.getAsString(), e);
//            }
//        }
//    }
}

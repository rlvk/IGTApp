//package rw.igttestapp.api;
//
//import android.content.Context;
//import android.net.Uri;
//import android.support.v4.content.AsyncTaskLoader;
//import android.util.Log;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PushbackInputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.util.List;
//import java.util.zip.GZIPInputStream;
//
//import rw.igttestapp.BuildConfig;
//import rw.igttestapp.datamodel.GameResponse;
//
///**
// * Created by rafalwesolowski on 14/07/2016.
// */
//public class GamesAsyncTaskLoader extends AsyncTaskLoader<GameResponse> {
//
//    private static final String GAME_REQUEST_PATH = "u/49130683/nativeapp-test.json";
//
//    boolean isLoading = false;
//
//    public GamesAsyncTaskLoader(Context context) {
//        super(context);
//    }
//
//    @Override
//    public void forceLoad() {
//        if (!isLoading) {
//            super.forceLoad();
//            isLoading = !isLoading;
//        }
//    }
//
//    @Override
//    public GameResponse loadInBackground() {
//
////        Response<DATA_TYPE> result = new Response<DATA_TYPE>();
//
////        RequestBuilder.Request request = mRequestBuilder.build();
//
//        InputStream inputStream = null;
//
////        RequestError error = new RequestError();
////        Response<DATA_TYPE> errorResponse = new Response<DATA_TYPE>();
//
//        Uri.Builder builder = Uri.parse(BuildConfig.BASE_URL)
//                .buildUpon().path(GAME_REQUEST_PATH);
//
//        try {
//            URL url = new URL(builder.toString());
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000 /* milliseconds */);
//            conn.setConnectTimeout(15000 /* milliseconds */);
//            conn.setRequestMethod("GET");
//            conn.setDoInput(true);
//            // Starts the query
//            conn.connect();
//            int response = conn.getResponseCode();
//            Log.d(DEBUG_TAG, "The response is: " + response);
//            is = conn.getInputStream();
//
//            // Convert the InputStream into a string
//            String contentAsString = readIt(is, len);
//            return contentAsString;
//
//            // Makes sure that the InputStream is closed after the app is
//            // finished using it.
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
////        try {
////
////            HttpURLConnection httpURLConnection = (HttpURLConnection) request.getUrl().openConnection();
////            httpURLConnection.setUseCaches(false);
////            httpURLConnection.setRequestMethod(request.getRequestType().getValue());
////            httpURLConnection.setDoInput(true);
////            httpURLConnection.addRequestProperty("Accept-Encoding", "gzip");
////            //before requesting the service check the requests last access time and if there is a cached copy of the response.
////
////
////            if (!request.getRequestType().equals(RequestBuilder.RequestType.HTTP_GET)) {
////                OutputStream outputStream = httpURLConnection.getOutputStream();
////                httpURLConnection.setDoOutput(true);
////
////                List<NameValuePair> params = request.getEncodedParams();
////                UrlEncodedFormEntity parameters = new UrlEncodedFormEntity(params);
////                //Send request
////                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
////                wr.writeBytes(parameters.toString());
////                wr.flush();
////                wr.close();
////            }
////            httpURLConnection.connect();
////
////            int response = httpURLConnection.getResponseCode();
////            inputStream = httpURLConnection.getInputStream();
////
////            if (response == HttpURLConnection.HTTP_OK) {
////                try {
////                    String responseString = convertStreamToString(inputStream);
////
////                    if (!request.bypassCache()) {
////
////                        CacheData cacheData = new CacheData();
////                        cacheData.setRequestString(request.getUrl().toString());
////
////                        long timestamp = System.currentTimeMillis() / 1000;
////
////                        cacheData.setTimestampInSeconds(timestamp);
////                        Log.d("CACHE_OPERATION_SET", responseString);
////                        cacheData.setResponse(responseString);
////
////                        mSimpleCache.put(cacheData);
////                    }
////
////                    InputStream dataInputStream = new ByteArrayInputStream(responseString.getBytes());
////                    DATA_TYPE resultObject = mParser.parse(dataInputStream);
////
////                    result.setData(resultObject);
////                    return result;
////                } catch (Exception e) {
////                    error.setError(Constants.PARSE_ERROR_TYPE, e.getMessage());
////                    e.printStackTrace();
////                }
////            }
////
////        } catch (MalformedURLException e) {
////            error.setError(Constants.BAD_URL_ERROR_TYPE, e.getMessage());
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////            error.setError(Constants.IO_EXCEPTION_ERROR_TYPE, e.getMessage());
////        } finally {
////            if (inputStream != null) {
////                try {
////                    inputStream.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////
////        // If anything goes wrong AND there's still a cached version return it.
////        if (mCacheData != null) {
////
////            try {
////                Log.d("CACHE_STALE_GET", request.getUrl().toString());
////                return getCacheResponse(mCacheData);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////        }
////
////        errorResponse.setError(error);
////        return errorResponse;
//    }
//
//    /**
//     * Called when there is new data to deliver to the client.  The
//     * super class will take care of delivering it; the implementation
//     * here just adds a little more logic.
//     */
//    @Override
//    public void deliverResult(GameResponse data) {
//        if (isStarted()) {
//            super.deliverResult(data);
//        }
//    }
//
//    @Override
//    protected void onStartLoading() {
//        forceLoad();
//    }
//
//    private String convertStreamToString(InputStream in) {
//
//        InputStreamReader is = new InputStreamReader(in);
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = new BufferedReader(is);
//        String read = null;
//        try {
//            read = br.readLine();
//
//            while (read != null) {
//                //System.out.println(read);
//                sb.append(read);
//                read = br.readLine();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
//}

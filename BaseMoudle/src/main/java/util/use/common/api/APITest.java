package util.use.common.api;

/**
 * Created by Lyson on 15/8/29.
 */
public class APITest extends APIBase {
        public  static <T> void test(String url, MyResultCallback<T> callback){
            APIRESTClient.getAsyn(url, callback);
        }
}

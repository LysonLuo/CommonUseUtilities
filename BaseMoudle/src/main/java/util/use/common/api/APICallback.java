package util.use.common.api;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import util.use.common.model.APIError;

/**
 * Created by Lyson on 15/8/27.
 */
abstract public class APICallback<T> {
    protected Type mType;

    public APICallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    abstract public  void onFailure(APIError apiError);

    abstract public  void onSuccess(T result);
}

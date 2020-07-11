package co.sentinel.lite.util;

import java.io.IOException;
import java.lang.annotation.Annotation;

import co.sentinel.lite.network.client.WebClient;
import co.sentinel.lite.network.model.ApiError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ApiErrorUtils {
    public static ApiError parseGenericError(Response<?> iResponse) {
        Converter<ResponseBody, ApiError> aConverter = WebClient
                .getGenericClient()
                .responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError aError = new ApiError();
        try {
            if (iResponse.errorBody() != null)
                aError = aConverter.convert(iResponse.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }
        return aError;
    }
}
package com.saurabhjadhav.crudappjava.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    // todo change dtaatype and eveything according to need
    @GET("")
    Call<String> getFun();
}

package com.example.citypomsjava;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface myApi {
    @GET
    Call<ArrayList<Model>>modelCall();
}

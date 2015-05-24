package com.elsennovraditya.materialdesign;

import com.elsennovraditya.materialdesign.responses.NoteResponse;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by elsen on 4/8/15.
 */
public interface RetrofitService {

    String USER_ID = "f73e043e-cfb7-4eed-8603-63c14465f8e7";
    String ACCESS_TOKEN = "MTQzNWRiZjYtMGQzYy00ZmIxLTk0ZWItMDczMjEzMmYxZGUz";

    @Headers({"Authorization: Bearer " + ACCESS_TOKEN,
            "Content-Type: application/json",
            "X-SE3-Media-Type: se3.v1"})
    @GET("/notes/{id}")
    Observable<NoteResponse> getANote(@Path("id") String noteId);

}

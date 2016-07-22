package org.jbfavre.thot.API;

import java.util.List;

import org.jbfavre.thot.Model.ApiSuccessResponse;
import org.jbfavre.thot.Model.Article;
import org.jbfavre.thot.Model.Tag;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SelfossApi {

    @GET("login")
    Call<ApiSuccessResponse> login(@Query("username") String username, @Query("password") String password);

    @GET("items?type=unread")
    Call<List<Article>> getUnreads(@Query("items") int items, @Query("search") String search,
                                   @Query("tag") String tag, @Query("source") int source,
                                   @Query("offset") int offset, @Query("updatedsince") String updatedsince);

    @GET("tags")
    Call<List<Tag>> getTags();
}

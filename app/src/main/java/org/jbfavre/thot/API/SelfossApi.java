package org.jbfavre.thot.API;

import java.util.List;
import org.jbfavre.thot.Model.Tag;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SelfossApi {

    @GET("tags")
    Call<List<Tag>> getTags();
}

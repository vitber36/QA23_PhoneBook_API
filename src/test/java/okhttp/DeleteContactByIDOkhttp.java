package okhttp;

import com.google.gson.Gson;
import dto.DeleteByIDResponseDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDOkhttp {
    String id;
    @BeforeMethod
            public void preCondition(){
        //create contact
        //get id from message:"Contact was added! ID:123123-123123-132123 "
        //id=""
    }

    String token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidml0YmVyMzZAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MDk1NzEwMTcsImlhdCI6MTcwODk3MTAxN30.htdBFfnYhwCvlH1Byaic2p2HzqK3jFYwCof6qWnWacA";
    Gson gson=new Gson();
    OkHttpClient client=new OkHttpClient();

    @Test
    public void deleteContactByIDSuccess() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/bde3a291-4729-4b52-99a2-6ddd6aa61eb7") //+ id)
                .delete()
                .addHeader("Authorization", token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);
        DeleteByIDResponseDTO dto = gson.fromJson(response.body().string(), DeleteByIDResponseDTO.class);
        Assert.assertEquals(dto.getMessage(), "Contact was deleted!");
        System.out.println(dto.getMessage());

    }
}

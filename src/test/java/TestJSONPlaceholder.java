import client.Client;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;

public class TestJSONPlaceholder extends Client {

    Response response;
    ValidatableResponse vResponse;

    @Test
    public void testCommentsStatusCode() {
        response = get(comments());

        response.body().prettyPeek();
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void testCommentsLength() {
        response = get(comments());

        response.body().prettyPeek();
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("id", hasSize(500));
    }

    @Test
    public void testPostPosts() {
        HashMap<String, String> request = new HashMap<>();
        request.put("userId", "11");
        request.put("id", "101");
        request.put("title", "Sample Post");
        request.put("body", "We are performing a post operation on this endpoint");

        vResponse = post(posts(), request);

        vResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED);
    }

}

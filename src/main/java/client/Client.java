package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Client {

    Properties properties;
    String absPath = System.getProperty("user.dir");
    String relPath = File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";
    String configFilePath = absPath + relPath;
    String baseURL;
    private String postsResource;
    private String commentsResource;
    private String albumsResource;
    private String photosResource;
    private String todosResource;
    private String usersResource;

    @BeforeMethod
    public void loadProp() {
        loadConfig();

        baseURL = properties.getProperty("baseURL");
        postsResource = properties.getProperty("posts");
        commentsResource = properties.getProperty("comments");
        albumsResource = properties.getProperty("albums");
        photosResource = properties.getProperty("photos");
        todosResource = properties.getProperty("todos");
        usersResource = properties.getProperty("users");
    }

    // region Endpoints
    public String posts() {
        return baseURL + postsResource;
    }

    public String comments() {
        return baseURL + commentsResource;
    }

    public String albums() {
        return baseURL + albumsResource;
    }

    public String photos() {
        return baseURL + photosResource;
    }

    public String todos() {
        return baseURL + todosResource;
    }

    public String users() {
        return baseURL + usersResource;
    }

    // endregion

    // region Methods
    public Response get(String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public ValidatableResponse post(String url, HashMap jsonBody) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(url).then();
    }

    // endregion

    // region Helpers
    public void loadConfig() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // endregion

}

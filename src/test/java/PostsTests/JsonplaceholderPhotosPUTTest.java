package PostsTests;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPhotosPUTTest extends BaseTest {

    private static Faker faker;
    private Integer fakeAlbumId;
    private String fakeAlbumTitle;
    private String fakeWWW;
    private String fakePhoto;


    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }


    @BeforeEach
    public void beforeEach() {
        fakeAlbumId = faker.number().randomDigit();
        fakeAlbumTitle = faker.lorem().sentence();
        fakeWWW = faker.internet().url();
        fakePhoto = faker.internet().image();
    }


    @Test
    public void JsonplaceholderChangeAllInformationInFirstPhoto() {
        JSONObject photo = new JSONObject();
        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeAlbumTitle);
        photo.put("url", fakeWWW);
        photo.put("thumbnailUrl", fakePhoto);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .put(BASE_URL + "/" + PHOTOS + "/" + 1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeAlbumTitle, json.get("title"));
        assertEquals(fakeWWW, json.get("url"));
    }

    @Test
    public void JsonplaceholderChangeTitleOfFirstPhoto() {
        JSONObject photo = new JSONObject();
        photo.put("title", fakeAlbumTitle);


        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .put(BASE_URL + "/" + PHOTOS + "/" + 1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeAlbumTitle, json.get("title"));
    }
}

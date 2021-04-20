package PhotosTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPhotosGETTest extends BaseTest {

    @Test
    public void jsonplaceholderReadInformationOfAllPhotos() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + PHOTOS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> photos = json.getList("names");
        assertEquals(5000, photos.size());
        System.out.println(photos.size());
    }

    @Test
    public void jsonplaceholderReadInformationOfFirstPhoto() {
        Response response = given()
                .pathParam("postId", 1)
                .when()
                .get(BASE_URL + "/" + PHOTOS + "/{postId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        System.out.println(response.asString());
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", json.get("title"));
    }
}

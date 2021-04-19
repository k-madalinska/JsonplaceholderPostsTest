package PostsTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceholderPhotosDELETETest extends BaseTest {

    @Test
    public void JsonplaceholderDeletInformationOfFirstPhoto() {
        Response response = given()
                .when()
                .delete(BASE_URL + "/" + PHOTOS + "/" + 1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}

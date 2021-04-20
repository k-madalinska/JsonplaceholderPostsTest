package PhotosTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderUsersEmailTest extends BaseTest {

    @Test
    public void JsonplaceholderUsersEmailWithPLTest() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emailUsers = json.getList("email");
        Assertions.assertEquals(10, emailUsers.size());
   //     System.out.println(emailUsers.size());


        // using stream - filter
        emailUsers.stream()
                .filter(email -> email.endsWith(".pl"))
                .forEach(System.out::println);
        //System.out.println(emailUsers.toString());

        // using stream - anyMatch
       boolean anyMatchEndWithPl =  emailUsers.stream()
                .anyMatch(email -> email.endsWith(".pl"));
        System.out.println(anyMatchEndWithPl);


        //using loop: foreach
/*        for (String email : emailUsers) {
            if (email.endsWith(".pl"))
                System.out.println(email);
            else {
                System.out.println("No email");
            }
        }*/
    }
}

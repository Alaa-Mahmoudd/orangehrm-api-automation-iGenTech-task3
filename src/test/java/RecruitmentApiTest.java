import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecruitmentApiTest {

    // Note:
    // OrangeHRM demo environment requires an authenticated browser session.
    // Therefore, a valid browser session cookie is used instead of login API,
    // because login endpoint alone does not create a fully authorized API session.
    private String cookie = "orangehrm=n8dp74t1b5cjf0dkiufm92dnsj";

    // Add Candidate
    public int addCandidate(String firstName, String middleName, String lastName, String email) {

        String addBody = """
                {
                  "firstName":"%s",
                  "middleName":"%s",
                  "lastName":"%s",
                  "email":"%s"
                }
                """.formatted(firstName, middleName, lastName, email);

        Response addResponse = given()
                .header("Content-Type", "application/json")
                .header("Cookie", cookie)
                .body(addBody)
                .log().all()
                .when()
                .post("/web/index.php/api/v2/recruitment/candidates")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", notNullValue())
                .body("data.firstName", equalTo(firstName))
                .body("data.lastName", equalTo(lastName))
                .body("data.email", equalTo(email))
                .extract().response();

        int candidateId = addResponse.jsonPath().getInt("data.id");
        System.out.println("Candidate added successfully, ID = " + candidateId);

        return candidateId;
    }

    // Delete Candidate
    public void deleteCandidate(int candidateId) {

        String deleteBody = """
                {
                  "ids":[%s]
                }
                """.formatted(candidateId);

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", cookie)
                .body(deleteBody)
                .log().all()
                .when()
                .delete("/web/index.php/api/v2/recruitment/candidates")
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("Candidate deleted successfully, ID = " + candidateId);
    }

    // Add and Delete Candidate
    @Test
    public void testAddAndDeleteCandidate() {

        RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com";

        // Generate unique email for each execution
        String email = "alaa" + System.currentTimeMillis() + "@gmail.com";

        // Add candidate
        int candidateId = addCandidate("Alaa", "", "Mahmoud", email);

        // Delete candidate
        deleteCandidate(candidateId);
    }
}
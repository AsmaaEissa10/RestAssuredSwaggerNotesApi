package tests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
public class NotesApisTest extends BaseTest{
    String currentTime = String.valueOf(System.currentTimeMillis());
    String token;
    String  id;
    JsonPath jsonPath;
    @Test(priority = 1)
    public void Register() throws FileNotFoundException {
        String response = usersSwaggerApis.register(registerDataJson.getTestData("name"),
                registerDataJson.getTestData("email")+currentTime+"@gmail.com"
                        ,registerDataJson.getTestData("password"))
                .then()
                .log()
                .all()
                .statusCode(201).extract().response().asString();

  }
    @Test(priority = 2)
    public void Login() throws FileNotFoundException {
        String response = usersSwaggerApis.login(registerDataJson.getTestData("email")+currentTime+"@gmail.com",
                registerDataJson.getTestData("password"))
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

         jsonPath = new JsonPath(response);
         token = jsonPath.get("data.token");
         System.out.println(token);
    }

    @Test(priority = 3)
    public void CreateNote() throws FileNotFoundException {
        String response = notesSwaggersApis.createNote(createNoteDataJson.getTestData("title"),
                        createNoteDataJson.getTestData("description"),
                        createNoteDataJson.getTestData("category"),token)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        id = jsonPath.get("data.id");

        String title = jsonPath.getString("data.title");
        Assert.assertEquals(createNoteDataJson.getTestData("title"),title);
    }

    @Test(priority = 4)
    public void UpdateNoteById() throws FileNotFoundException {
        String response = notesSwaggersApis.putNote(putNoteJson.getTestData("title"),
                        putNoteJson.getTestData("description"),
                        Boolean.parseBoolean(putNoteJson.getTestData("completed")),
                        putNoteJson.getTestData("category"),token,id)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("Note successfully Updated"));

        String title = jsonPath.getString("data.title");
        Assert.assertEquals(putNoteJson.getTestData("title"),title);

        String description = jsonPath.getString("data.description");
        Assert.assertEquals(putNoteJson.getTestData("description"),description);
    }

    @Test(priority = 5)
    public void CompleteUpdateById() throws FileNotFoundException {
        String response = notesSwaggersApis.patchNote(Boolean.parseBoolean(patchNoteJson.getTestData("completed"))
                        , token, id)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }

    @Test(priority = 6)
    public void DeleteNote(){
        String response = notesSwaggersApis.deleteNote(id,token)
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }
}

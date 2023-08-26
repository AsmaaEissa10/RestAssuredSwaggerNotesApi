package tests;

import api.NotesSwaggersApis;
import api.UsersSwaggerApis;
import data.ReadJsonData;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import java.io.FileNotFoundException;

public class BaseTest {
    UsersSwaggerApis usersSwaggerApis;
    NotesSwaggersApis notesSwaggersApis;
    ReadJsonData registerDataJson;
    ReadJsonData updateDataJson;
    ReadJsonData newPasswordJson;
    ReadJsonData createNoteDataJson;
    ReadJsonData patchNoteJson;
    ReadJsonData putNoteJson;

    @BeforeMethod
    public void setData() throws FileNotFoundException {
        usersSwaggerApis = new UsersSwaggerApis();
        notesSwaggersApis = new NotesSwaggersApis();
        RestAssured.baseURI = "https://practice.expandtesting.com/notes/api";

        registerDataJson = new ReadJsonData("src/test/resources/testData/Users/registerUserData.json");
        updateDataJson = new ReadJsonData("src/test/resources/testData/Users/updateUserData.json");
        newPasswordJson = new ReadJsonData("src/test/resources/testData/Users/newPasswordData.json");

        createNoteDataJson = new ReadJsonData("src/test/resources/testData/Notes/createNoteData.json");
        patchNoteJson = new ReadJsonData("src/test/resources/testData/Notes/patchNoteData.json");
        putNoteJson = new ReadJsonData("src/test/resources/testData/Notes/putNoteData.json");

    }
}

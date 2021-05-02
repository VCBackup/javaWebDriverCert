package challenge9;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class challenge9 {

    @AfterSuite
    public void stopSuite() {
        System.out.println("All done!!!");
    }

    // Full test
    @Test()
    public void fullChallenge9() throws IOException {
        Logger logger = Logger.getLogger("WebResponseLog");
        FileHandler fh;
        fh = new FileHandler("C:/temp/MyLogFile.log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        dndGetResponse(logger);
    }

    public void dndGetResponse(Logger logger) {
        RestAssured.baseURI = "https://www.dnd5eapi.co/api";
        RequestSpecification httpRequest = RestAssured.given().contentType("text/html");
        Response response = httpRequest.get("/spells");

        ResponseBody body = response.getBody();

        System.out.println("The response body is: " + body.asString());
        DndSpells elements = body.as(DndSpells.class);
        //System.out.println("The first class is: " + elements.results.get(0).index);
        //logger.info("The first class is: " + elements.results.get(0).index);
    }

}



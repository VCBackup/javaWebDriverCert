package challenge8;

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

public class challenge8 {

    @AfterSuite
    public void stopSuite() {
        System.out.println("All done!!!");
    }

    // Full test
    @Test()
    public void fullChallenge8() throws IOException {
        Logger logger = Logger.getLogger("WebResponseLog");
        FileHandler fh;
        fh = new FileHandler("C:/temp/MyLogFile.log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        int i = 0;
        // Setup 12 different search parameters
        String[] carSearches = {
                "/?query=toyota%20camry",
                "/?query=subaru%20outback",
                "/?query=ford%20f-250",
                "/?query=chevrolet%20cruze",
                "/?query=honda%20civic",
                "/?query=chevrolet%20camaro",
                "/?query=ford%20expedition",
                "/?query=buick%20le%20sabre",
                "/?query=ford%forester",
                "/?query=nissan%20skyline",
                "/?query=honda%20accord",
                "/?query=ford%20explorer"
        };
        while (i < carSearches.length - 1){
            copartPostResponse(logger, carSearches[i]);
            i++;
        }

        //dndGetResponse(logger);
    }

    public void dndGetResponse(Logger logger) {
        RestAssured.baseURI = "https://www.dnd5eapi.co/api";
        RequestSpecification httpRequest = RestAssured.given().contentType("text/html");
        Response response = httpRequest.get("/classes");

        ResponseBody body = response.getBody();

        System.out.println("The response body is: " + body.asString());
        DndClasses elements = body.as(DndClasses.class);
        System.out.println("The first class is: " + elements.results.get(0).index);
        logger.info("The first class is: " + elements.results.get(0).index);
    }

    // Use copart web service to search for toyota camry
    public void copartPostResponse(Logger logger, String query) {
        RestAssured.baseURI = "https://www.copart.com/public/lots/search";
        RequestSpecification httpRequest = RestAssured.given().contentType("text/html");
        Response response = httpRequest.post(query);

        ResponseBody body = response.getBody();

        CopartCarData carData = body.as(CopartCarData.class);
        System.out.println("The response body is: " + carData.TotalElements);
        System.out.println("My query of " + query + " returned this result: " + carData.TotalElements);
        // Output the value of totalElements to a log file
        logger.info("My query of " + query + " returned this result: " + carData.TotalElements);
    }
}



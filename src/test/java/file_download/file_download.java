package file_download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class file_download {

    RequestSpecification httpRequest;
    Response response;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://api.oip.tmrnd.com.my/t/opendata.oip.tm.com.my";
        RestAssured.basePath = "/met/1.0.0/static/images/satelit-latest.gif";
        Header acceptHeader = new Header("Accept", "application/json");
        httpRequest =  RestAssured.given().auth().oauth2("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlpEazFaRFl6TlRFNFpERmlOMlJqTURRd05qazJNMlkxTWpnMVltRTJaakprTlRNM05tVm1abU14T0dWbVltSXdPV1ZoT0daaFl6WmlOREV4TVRBMk53In0.eyJhdWQiOiJodHRwOlwvXC9vcmcud3NvMi5hcGltZ3RcL2dhdGV3YXkiLCJzdWIiOiJzYWlmc21zOTFAZ21haWwuY29tIiwiYXBwbGljYXRpb24iOnsib3duZXIiOiJzYWlmc21zOTFAZ21haWwuY29tIiwidGllclF1b3RhVHlwZSI6InJlcXVlc3RDb3VudCIsInRpZXIiOiJVbmxpbWl0ZWQiLCJuYW1lIjoiRGVmYXVsdEFwcGxpY2F0aW9uIiwiaWQiOjYwNywidXVpZCI6bnVsbH0sInNjb3BlIjoiYW1fYXBwbGljYXRpb25fc2NvcGUgZGVmYXVsdCIsImlzcyI6Imh0dHBzOlwvXC9pZG0ub2lwLnRtcm5kLmNvbS5teTo0NDNcL29hdXRoMlwvdG9rZW4iLCJ0aWVySW5mbyI6eyJGcmVlIjp7InRpZXJRdW90YVR5cGUiOiJyZXF1ZXN0Q291bnQiLCJzdG9wT25RdW90YVJlYWNoIjp0cnVlLCJzcGlrZUFycmVzdExpbWl0IjotMSwic3Bpa2VBcnJlc3RVbml0IjoiTkEifX0sImtleXR5cGUiOiJTQU5EQk9YIiwic3Vic2NyaWJlZEFQSXMiOlt7InN1YnNjcmliZXJUZW5hbnREb21haW4iOiJnbWFpbC5jb20iLCJuYW1lIjoiTWV0ZW9yb2xvZ2ljYWwtTWFsYXlzaWEiLCJjb250ZXh0IjoiXC90XC9vcGVuZGF0YS5vaXAudG0uY29tLm15XC9tZXRcLzEuMC4wIiwicHVibGlzaGVyIjoiYWRtaW5Ab3BlbmRhdGEub2lwLnRtLmNvbS5teSIsInZlcnNpb24iOiIxLjAuMCIsInN1YnNjcmlwdGlvblRpZXIiOiJGcmVlIn1dLCJjb25zdW1lcktleSI6InMxVHVfTThhN1JyUHZ0OFg4Vmg5anJkeXdjRWEiLCJleHAiOjE2NzE4ODU1ODcsImlhdCI6MTY3MTg4MTk4NywianRpIjoiYjQ0YTc2NjItMDljMC00NjgxLWE1OTctMDM5YjkxNDc3ZWEyIn0.T_MmxpRI1ZIbnwXI7Gzk9MkqcIMKHvToTjrvL7w0PWJB3g9kb84I1NuNgUtWtTfAXLwQKc2sQEwuXyPg0dJglXd7VwsgZpKYvidDMM1ZKctsaW1qJnDtPspj0eSZw5vIi9t6ige0Bxj8xZTAvJGAhMjRYjvIwgFYg0f-TN--aU2qFuF9pIfTJ0I5_3RKILtDlUWSaRtzLVCAmZX1WnWiqeUvBzByeDESy_iSL6l0zqEs2ySGSlxZ95p3LM03T-gISaMZVOauTBFlHHPJOkiEK6cQKI9NeiGK7HJqDg6QtoC4TbHtekdYwI2joWa5C3QIH2zzoYeUxesoTYF75tRTbg");
        response = httpRequest
                //.queryParam("name", "Yey.gif")
                .when()
                .get()
                .andReturn();
    }

    @Test(enabled = false)
    public void downloadFileUsingByteArray() throws IOException {

        // Store the file into Byte Array

        byte[] downloadedFileBytes = response.asByteArray();

        // Using FileOutputStream write the Byte Array into Physical File and close FileOutputStream Object

        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/file_download/Test.gif");

        fos.write(downloadedFileBytes);

        fos.close();
    }

    @Test(enabled = false)
    public void downloadFileUsingByteArrayTryWithResource() throws FileNotFoundException, IOException {

        // Store the file into Byte Array

        byte[] downloadedFileBytes = response.asByteArray();

        // Using FileOutputStream write the Byte Array into Physical File and close FileOutputStream Object

        try(FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/file_download/Test.gif")){
            fos.write(downloadedFileBytes);
        }


    }

    @Test(enabled = false)
    public void downloadFileUsingInputStream() throws IOException {

        // Store the file into Input Stream

        InputStream downloadedFileIS = response.asInputStream();

        // Create the File Object
        File targetFile = new File(System.getProperty("user.dir") + "/src/test/java/file_download/Test.gif");

        // Using Files copy the InputStream to target file path and close InputStream Object

        Files.copy(downloadedFileIS, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        downloadedFileIS.close();
    }

    @Test(enabled = true)
    public void downloadFileUsingInputStreamTryWithResource() throws IOException {

        // Store the file into Input Stream

        try(InputStream downloadedFileIS = response.asInputStream()){
            // Create the File Object
            File targetFile = new File(System.getProperty("user.dir") + "/src/test/java/file_download/Test.gif");

            // Using Files copy the InputStream to target file path and close InputStream Object

            Files.copy(downloadedFileIS, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterMethod
    public void checkHeader() {
        response.then().header("Transfer-Encoding", equalTo("chunked"));
    }

}
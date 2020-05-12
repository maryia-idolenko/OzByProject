package tests;

import org.testng.annotations.Test;

public class UploadPersonalPhotoTest extends BaseTest {

    @Test(description = "Upload Personal Photo")
    public void uploadPhoto() {
        String image = "src/test/resources/photo.jpg";
        personalSteps.goToPersonalData();
        infoSteps.uploadImg(image);
    }
}
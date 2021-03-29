package cz.zcu.kiv.eitm.facecomparisonbe.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import cz.zcu.kiv.eitm.facecomparisonbe.model.Result;
import cz.zcu.kiv.eitm.facecomparisonbe.model.ResultItem;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthoriseServiceImpl implements AuthoriseService {
    @Override
    public Result authorise(User user, User userDb) {
        Float similarityThreshold = 70F;
        String sourceImage = "img1.png";
        String targetImage = "img2.png";
        ByteBuffer sourceImageBytes = null;
        ByteBuffer targetImageBytes = null;

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        //Load source and target images and create input parameters
        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile("classpath:img1.png"))) {
            sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (Exception e) {
            System.out.println("Failed to load source image " + sourceImage);
            System.exit(1);
        }
        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile("classpath:img2.png"))) {
            targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (Exception e) {
            System.out.println("Failed to load target images: " + targetImage);
            System.exit(1);
        }

        Image source = new Image()
                .withBytes(sourceImageBytes);
        Image target = new Image()
                .withBytes(targetImageBytes);

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        // Call operation
        CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);


        // Display results
        List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
        if ( faceDetails.size() >= 1) {

            ComparedFace face = faceDetails.get(0).getFace();
            BoundingBox position = face.getBoundingBox();

            List<ResultItem> resultItems = new ArrayList<>();
            resultItems.add(new ResultItem("numberOfMatches", String.valueOf(faceDetails.size())));
            resultItems.add(new ResultItem("confidence", faceDetails.get(0).getSimilarity().toString()));
            resultItems.add(new ResultItem("position", position.getLeft().toString() +","+ position.getTop()));
            return new Result("login", true, resultItems);
        } else {
            return new Result("login", false);
        }


    }
}

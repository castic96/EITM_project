package cz.zcu.kiv.eitm.facecomparisonbe.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;

public class AWSRekognitionUtil {

    private final Logger LOG = LoggerFactory.getLogger(AWSRekognitionUtil.class);

    private final Float SIMILARITY_THRESHOLD = 70F;
    private final AmazonRekognition rekognitionClient;

    private final Float CONFIDENCE_MIN = 0.8F;

    public AWSRekognitionUtil() {
        this.rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
    }

    public boolean compareImages(ByteBuffer sourceImageBytes, ByteBuffer targetImageBytes) {
        Image source = new Image()
                .withBytes(sourceImageBytes);
        Image target = new Image()
                .withBytes(targetImageBytes);

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(SIMILARITY_THRESHOLD);

        // Call operation
        CompareFacesResult compareFacesResult;
        try {
            compareFacesResult = rekognitionClient.compareFaces(request);
        } catch (AmazonServiceException e) {
            return false;
        }

        // Display results
        List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
        if (faceDetails.size() < 1) return false;

        ComparedFace face = faceDetails.get(0).getFace();
        BoundingBox position = face.getBoundingBox();

        Float confidence = faceDetails.get(0).getSimilarity();

        LOG.info("numberOfMatches = " + faceDetails.size());
        LOG.info("confidence = " + confidence);
        LOG.info("position = " + position.getLeft() + "," + position.getTop());

        return confidence > CONFIDENCE_MIN;
    }

}

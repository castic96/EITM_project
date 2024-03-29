package cz.zcu.kiv.eitm.facecomparisonbe.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

public class AWSRekognitionUtil {

    private final Logger LOG = LoggerFactory.getLogger(AWSRekognitionUtil.class);

    /** Similarity threshold for face comparison. */
    private final Float SIMILARITY_THRESHOLD = 70F;
    /** AmazonRekognition client. */
    private final AmazonRekognition rekognitionClient;

    /** Minimum confidence value to declare that two faces are same. */
    public static final Float CONFIDENCE_MIN = 90.0F;

    public AWSRekognitionUtil() {
        this.rekognitionClient = AmazonRekognitionClient.builder()
                .withRegion("eu-central-1")
                .withCredentials(new AWSStaticCredentialsProvider(new AWSCredentials() {
                    @Override
                    public String getAWSAccessKeyId() {
                        return "AKIAWC2HSJOPYHOV2PMD";
                    }

                    @Override
                    public String getAWSSecretKey() {
                        return "TetZO+VqJ2tqfdlI8q02aaivSQMVV+j6kEI0O+bC";
                    }
                })).build();
    }

    /**
     * Compares two images with faces.
     *
     * @param sourceImageBytes source image
     * @param targetImageBytes target image
     * @return confidence or -1 (when error)
     */
    public Float compareImages(ByteBuffer sourceImageBytes, ByteBuffer targetImageBytes) {
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
            return -1.0f;
        }

        // Display results
        List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
        if (faceDetails.size() < 1) return -1.0f;

        ComparedFace face = faceDetails.get(0).getFace();
        BoundingBox position = face.getBoundingBox();

        Float confidence = faceDetails.get(0).getSimilarity();

        LOG.info("numberOfMatches = " + faceDetails.size());
        LOG.info("confidence = " + confidence);
        LOG.info("position = " + position.getLeft() + "," + position.getTop());

        return confidence;
    }

    /**
     * Checks if image contains a face.
     *
     * @param image image
     * @return true if image does contain a face; false otherwise
     */
    public boolean imageContainsFace(String image) {
        ByteBuffer byteBufferImage = ByteBuffer.wrap(Base64.getDecoder().decode(image));
        Float confidence = compareImages(byteBufferImage, byteBufferImage);
        return confidence >= 0.0F;
    }

}

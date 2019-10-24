package br.com.fernando.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadObjectFile {

    public static void main(String[] args) throws IOException {
        Regions clientRegion = Regions.US_EAST_1;
        String bucketName = "www.sensoriamento-mobile-hml";
        String fileObjKeyName = UUID.randomUUID().toString();
        String fileName = "index.html";

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();


        // Upload a file as a new object with ContentType and title specified.
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        PutObjectRequest request =
                new PutObjectRequest(bucketName,
                        "Fefe/"+f.getName(), f );
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("plain/text");
        metadata.addUserMetadata("x-amz-meta-title", "someTitle");
        request.setMetadata(metadata);
        s3Client.putObject(request);
    }
}


package br.com.fernando.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UploadDiretorioBig {

    public static void main(String[] args) throws IOException {
        String bucketName = "www.sensoriamento-mobile-hml";


        ArrayList<File> files = new ArrayList<File>();
        for (String path : Collections.singletonList("/Users/fernandopimenta/intelig/teste.txt")) {
            files.add(new File(path));
        }

        TransferManager xfer_mgr = TransferManagerBuilder.standard().build();
        try {
            MultipleFileUpload xfer = xfer_mgr.uploadFileList(bucketName,
                    "teste", new File("."), files);

            MultipleFileUpload upload = xfer_mgr.uploadDirectory(bucketName,
                    "", new File("/Users/fernandopimenta/intelig/reactjs"), true);

            xfer.waitForCompletion();
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        xfer_mgr.shutdownNow();

    }
}


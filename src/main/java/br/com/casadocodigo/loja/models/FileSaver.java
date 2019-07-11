package br.com.casadocodigo.loja.models;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile multipartFile) {
		AmazonS3Client s3 = client();
		try {
			s3.putObject("casadocodigo", multipartFile.getOriginalFilename(), multipartFile.getInputStream(),
					new ObjectMetadata());
			return "https://s3.amazonaws.com/casadocodigo/" + multipartFile.getOriginalFilename();
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private AmazonS3Client client() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE",
				"wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		AmazonS3Client newClient = new AmazonS3Client(credentials, new ClientConfiguration());
		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		// nova linha
		newClient.setEndpoint("http://localhost:9444/s3");
		return newClient;
	}

}

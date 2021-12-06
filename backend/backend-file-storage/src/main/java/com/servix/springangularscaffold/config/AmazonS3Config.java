package com.servix.springangularscaffold.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.SetBucketPolicyRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Value("${storage.aws.credentials.access-key}")
    private String accessKey;

    @Value("${storage.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${storage.aws.s3.bucket-name}")
    private String bucketName;

    @Value("${storage.aws.s3.endpoint}")
    private String endpoint;

    @Value("${storage.aws.s3.region}")
    private String region;

    @Value("${storage.aws.s3.public-resource}")
    private String publicResource;

    @Bean
    public AmazonS3 amazonS3() {
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .enableForceGlobalBucketAccess()
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        setupBucket(amazonS3);

        return amazonS3;
    }

    private void setupBucket(AmazonS3 amazonS3) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }

        setPublicAccessForPublicPrefix(amazonS3);
    }

    private void setPublicAccessForPublicPrefix(AmazonS3 amazonS3) {
        //allow public access to the folder /public
        final Statement statement = new Statement(Statement.Effect.Allow)
                .withId("public_access_statement")
                .withPrincipals(Principal.All)
                .withActions(S3Actions.GetObject)
                .withResources(new Resource(publicResource));

        final Policy policy = new Policy("policy1").withStatements(statement);
        final String policyJson = policy.toJson();
        final SetBucketPolicyRequest setBucketPolicyRequest = new SetBucketPolicyRequest(bucketName, policyJson);

        amazonS3.setBucketPolicy(setBucketPolicyRequest);
    }
}

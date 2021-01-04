package com.smartagri;

import com.smartagri.fileupload.property.FileStorageProperties;
import com.smartagri.fileupload.property.URLProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class,
        URLProperties.class
})
public class SmartagriApplication {

	public static void main(String[] args) {
	    SpringApplication.run(SmartagriApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/raws").allowedOrigins("http://localhost:4205");
                //registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("http://localhost:4205").allowedHeaders("*");
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*").allowedHeaders("*");
            }
        };
    }
    @Bean
    public RestTemplate getRestTemplate() {
      
        return new RestTemplate();

        /*
        //Create resttemplate
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

        return restTemplate;
		*/

		/*
		RestTemplate restTemplate = new RestTemplate();

		final int proxyPortNum = Integer.parseInt(proxyPort);
		try {
			final String proxyUsername = AESencrp.decrypt(proxyUser);
			final String proxyPasswd = AESencrp.decrypt(proxyPassword);

			final CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(proxyHost, proxyPortNum),
					new UsernamePasswordCredentials(proxyUsername, proxyPasswd));
			HttpClientBuilder clientBuilder = HttpClientBuilder.create();

			clientBuilder.useSystemProperties();
			clientBuilder.setProxy(new HttpHost(proxyHost, proxyPortNum));
			clientBuilder.setDefaultCredentialsProvider(credsProvider);
			clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

			final CloseableHttpClient client = clientBuilder.build();

			final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setHttpClient(client);

			restTemplate.setRequestFactory(factory);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return restTemplate;
		*/
    }

    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(10_000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(10_000);
        return clientHttpRequestFactory;
    }


}

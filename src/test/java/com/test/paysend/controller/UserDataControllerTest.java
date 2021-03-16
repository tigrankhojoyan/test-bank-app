package com.test.paysend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.test.paysend.config.TestConfig;
import com.test.paysend.dto.RegistrationResponse;
import com.test.paysend.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = "classpath:schema.sql")
class UserDataControllerTest {

    XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    int randomServerPort;

    @Test
    void registerUserTest() throws IOException, URISyntaxException {
        File resourceCreateMenu = new ClassPathResource(
                "requests/registerUser.xml").getFile();
        UserDTO registrationRequest = xmlMapper.readValue(resourceCreateMenu, UserDTO.class);
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/register";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/xml");

        HttpEntity<UserDTO> request = new HttpEntity<>(registrationRequest, headers);
        RestTemplate customTemplate = restTemplateBuilder.build();

        ResponseEntity<RegistrationResponse> result = customTemplate.postForEntity(uri, request, RegistrationResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertEquals(0, result.getBody().getResultCode());
    }

    @Test
    void checkBalance() {
    }
}
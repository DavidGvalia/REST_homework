package ru.netology.REST_homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestHomeworkApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Container
	private static final GenericContainer<?> testContDev = new GenericContainer<>("devapp")
			.withExposedPorts(8080);
	@Container
	private static final GenericContainer<?> testContProd = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@Test
	void contextLoadsDev() {
		int devContainerPort = testContDev.getMappedPort(8080);
		ResponseEntity<String> forDevEntity = restTemplate.getForEntity("http://localhost:" + devContainerPort + "/profile", String.class);
		String devExpected = forDevEntity.getBody();
		Assertions.assertEquals(devExpected, "Current profile is dev");
	}

	@Test
	void contextLoadsProd() {
		int prodContainerPort = testContProd.getMappedPort(8081);
		ResponseEntity<String> forProdEntity = restTemplate.getForEntity("http://localhost:" + prodContainerPort + "/profile", String.class);
		String prodExpected = forProdEntity.getBody();
		Assertions.assertEquals(prodExpected, "Current profile is production");
	}
}
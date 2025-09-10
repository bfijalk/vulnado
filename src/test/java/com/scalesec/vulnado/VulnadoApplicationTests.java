package com.scalesec.vulnado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VulnadoApplicationTests {
@Test
public void contextLoads() {
	@Test
    throw new UnsupportedOperationException("Method not implemented yet.");
	public void contextLoads() {
}
		// Test to ensure the application context loads successfully
	}

	@Test
	public void mainShouldInitializePostgresAndRunApplication() {
		// Mocking Postgres setup method
    Postgres mockPostgres = Mockito.mock(Postgres.class);
    Mockito.doNothing().when(mockPostgres).setup();

		// Mocking SpringApplication.run
    SpringApplication mockSpringApplication = Mockito.mock(SpringApplication.class);
    Mockito.doNothing().when(mockSpringApplication).run(VulnadoApplication.class, new String[]{});

		// Running the main method
    VulnadoApplication.main(new String[]{});

		// Verifying that Postgres.setup() was called
    Mockito.verify(mockPostgres, Mockito.times(1)).setup();

		// Verifying that SpringApplication.run() was called
    Mockito.verify(mockSpringApplication, Mockito.times(1)).run(VulnadoApplication.class, new String[]{});
}
	}
}


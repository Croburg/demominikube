package de.mlinac.versandservice;

import de.mlinac.versandservice.domain.*;
import de.mlinac.versandservice.repository.VersandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class VersandServiceApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(VersandServiceApplication.class, args);
	}

}

package com.avaliacao;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.avaliacao.domain.model.RedeMt;
import com.avaliacao.domain.model.SubEstacao;
import com.avaliacao.domain.repository.SubEstacaoRepository;
import com.avaliacao.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class ApiIntegrationTest {
	
	@LocalServerPort
	private int porta;
	
	@Autowired
	private SubEstacaoRepository subEstacaoRepository;
	
	private SubEstacao subestacaoGet;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();//exibe os passos do log no console para ajudar a identificar o erro
		RestAssured.port = porta;
		RestAssured.basePath = "/subestacoes";
		
		databaseCleaner.clearTables();
		
		subestacaoGet = new SubEstacao();
		subestacaoGet.setCodigo("TST");
		subestacaoGet.setNome("Teste Integracao");
		subestacaoGet.setLatitude(new BigDecimal(-23.091737753889));
		subestacaoGet.setLongitude(new BigDecimal(-48.92416175522699));
		subestacaoGet.setRedesMt(new ArrayList<>());
		
		RedeMt rede = new RedeMt();
		rede.setCodigo("TST01");
		rede.setNome("Teste Rede MT");
		rede.setTensao_nominal(new BigDecimal(127));
		rede.setSubEstacao(subestacaoGet);
			
		subestacaoGet.getRedesMt().add(rede);
		
		subEstacaoRepository.save(subestacaoGet);
	    
	}
	
	@Test
	public void testarRetornoStatus200GetS() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
}

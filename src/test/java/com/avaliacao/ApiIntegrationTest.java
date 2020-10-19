package com.avaliacao;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import com.avaliacao.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
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
	
	private String jsonSubEstacaoCorreta;
	private String jsonSubEstacaoCorretaAlter;
	private String jsonSubEstacaoInvalido;
	private String nomeAlter = "Subestacao Teste Alteracao";
	String codigoSubEstacaoInexistente = "XXX";

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
		
		jsonSubEstacaoCorreta = ResourceUtils.getContentFromResource("/json/json-correto.json");
		jsonSubEstacaoCorretaAlter = ResourceUtils.getContentFromResource("/json/json-correto-alter.json");
		jsonSubEstacaoInvalido = ResourceUtils.getContentFromResource("/json/json-invalido.json");
		
		subEstacaoRepository.save(subestacaoGet);
	    
	}
	
	@Test
	public void a_testarRetornoStatus200GetS() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void b_deveRetornarResposta200_QuandoConsultarSubEstacaoExistente() {
		RestAssured.given()
			.pathParam("codigoSubEstacao", subestacaoGet.getCodigo())
			.accept(ContentType.JSON)
		.when()
			.get("/{codigoSubEstacao}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void c_deveRetornarStatus201_QuandoCadastrarSubEstacao() {
		RestAssured.given()
			.body(jsonSubEstacaoCorreta)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void ca_deveRetornarStatus400_QuandoCadastrarSubEstacaoJsonInvalido() {
		RestAssured.given()
			.body(jsonSubEstacaoInvalido)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void d_deveRetornarStatus200_QuandoAlterarSubEstacao() {
		
		RestAssured.given()
			.pathParam("codigoSubEstacao", subestacaoGet.getCodigo())
			.body(jsonSubEstacaoCorretaAlter)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{codigoSubEstacao}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo(nomeAlter));
	}
	
	@Test
	public void da_deveRetornarStatus404_QuandoAlterarSubEstacaoInexistente() {
		
		RestAssured.given()
			.pathParam("codigoSubEstacao", codigoSubEstacaoInexistente)
			.body(jsonSubEstacaoCorretaAlter)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{codigoSubEstacao}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void e_deveRetornarResposta204_QuandoExcluirSubEstacao() {
		RestAssured.given()
			.pathParam("codigoSubEstacao", subestacaoGet.getCodigo())
			.accept(ContentType.JSON)
		.when()
			.delete("/{codigoSubEstacao}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void ea_deveRetornarResposta404_QuandoExcluirSubEstacaoInexistente() {		
		RestAssured.given()
			.pathParam("codigoSubEstacao",codigoSubEstacaoInexistente)
			.accept(ContentType.JSON)
		.when()
			.delete("/{codigoSubEstacao}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}

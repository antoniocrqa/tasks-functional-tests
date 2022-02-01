package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2024");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String messagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("", messagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2024");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String messagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", messagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String messagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", messagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String messagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", messagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
}
package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.148:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.148:8001/tasks");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descrição
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
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
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

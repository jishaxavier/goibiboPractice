package stepDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePg {
	WebDriver flights = Preconditions.driver;
	int monthNumbr;
	public static Properties prop;
	By flight= By.partialLinkText("flight");
	By roundTrip = By.id("roundTrip");
	By multiCity = By.id("multiCity");
	By sourceLoc = By.cssSelector("input[placeholder=From]");
	By destLoc = By.cssSelector("input[placeholder=Destination");
	By adultBox = By.id("adultPaxBox");
	By adultPlus = By.id("adultPaxPlus");
	By adultMinus = By.id("adultPaxMinus");
	By childBox = By.id("childPaxBox");
	By childPlus = By.id("childPaxPlus");
	By childMinus = By.id("childPaxMinus");
	By infantBox = By.id("infantPaxBox");
	By infantPlus = By.id("infantPaxPlus");
	By infantMinus = By.id("infantPaxMinus");
	By travelClass = By.id("gi_class"); 
	By searchBtn = By.id("gi_search_btn");	
	By passengers = By.id("pax_link_common");
	
	
	
	@Given("^properties are opened$")
    public void properties_are_opened() throws IOException {
       callProperties();
    }
	
	@Given("^user is in homepg$")
    public void user_in_homepg() {
		flights.get(prop.getProperty("homeurl"));
        String checkurl = flights.getCurrentUrl();
        if(checkurl.equals(prop.getProperty("homeurl"))) {
        	System.out.println("You are in the Home page");
        }
    }
	@When("^user input (.+) and (.+) locations$")
    public void user_input_locations(String from, String to) throws InterruptedException {
		flights.findElement(sourceLoc).click();
		flights.findElement(sourceLoc).sendKeys(from);
		Thread.sleep(3000);
		flights.findElement(destLoc).click();
		flights.findElement(destLoc).sendKeys(to);
		Thread.sleep(3000);
		
    }

    @Then("^verify you have search results$")
    public void verify_you_have_search_results() {
        System.out.println("searched");
    }

    @And("^user clicked round trip button$")
    public void user_clicked_round_trip_button() {
    	flights.findElement(roundTrip).click();
    }

    @And("^user gives (.+) and (.+)$")
    public void user_gives_and(String departdate, String returndate) throws InterruptedException {
        System.out.println("travel dates");
        //departure date
        String depDateSplit[] = departdate.split(" ");
        String depmonthNam = depDateSplit[0];
        int depdateNumber = Integer.parseInt(depDateSplit[1]);
        int depMonthNumbr = monthNumber(depmonthNam);
        
        //return date
        String retDateSplit[] = returndate.split(" ");
        String retmonthNam = retDateSplit[0];
        int retdateNumber = Integer.parseInt(retDateSplit[1]);
        int retMonthNumbr = monthNumber(retmonthNam);
        
        selectDate(depmonthNam,depMonthNumbr,depdateNumber);
        selectDate(retmonthNam,retMonthNumbr,retdateNumber);
        
    }
    private int monthNumber(String month) {
        
        monthNumbr = Month.valueOf((month).toUpperCase()).getValue();
    	return monthNumbr;
    }
   
    
    @And("^user selects (.+) (.+) (.+) and (.+)$")
    public void user_selects_and(String adultcount, String childcount, String infantcount, String travelclass) throws InterruptedException {
      flights.findElement(passengers).click();
      Thread.sleep(3000);
      Integer adult = Integer.parseInt(adultcount);
//      String adultstr = flights.findElement(adultBox).getText();
//      System.out.println(adultstr);
//      Integer adultCnt = Integer.parseInt(flights.findElement(adultBox).getText());
//      System.out.println(adultCnt);
      Integer child = Integer.parseInt(childcount);
//      Integer childCnt = Integer.parseInt(flights.findElement(childBox).getText());
//      System.out.println(childCnt);
      Integer infant = Integer.parseInt(infantcount);
//      Integer infantCnt = Integer.parseInt(flights.findElement(infantBox).getText());
//      System.out.println(infantCnt);
      int acnt = 0,ccnt = 0, icnt=0;
      while(acnt< adult) {
    		flights.findElement(adultPlus).click();
    		acnt++;
      }
      
      while(ccnt< child) {
    	  flights.findElement(childPlus).click();
    	  ccnt++;
      }
      while(icnt<=infant) {
    	  flights.findElement(infantPlus).click();
    	  icnt++;
      }
      Select travClas = new Select(flights.findElement(travelClass));
      travClas.selectByVisibleText(travelclass);
    }

    @And("^clicks on search button$")
    public void clicks_on_search_button() {
        flights.findElement(searchBtn).click();
    }
    
    public void selectDate(String monthName, int monthNum, int dateNum) throws InterruptedException {
		flights.findElement(By.id("departureCalendar")).click();
		By dateNxtBtn = By.cssSelector("span.DayPicker-NavButton.DayPicker-NavButton--next");
		By depMonthName = By.cssSelector("div.DayPicker-Caption");
		String monthCapName;
		while(true) {
			monthCapName = flights.findElement(depMonthName).getText();
			if(monthCapName.contains(monthName)) {
				Thread.sleep(2000);
				String num = "fare_2021"+monthNum+dateNum;
				flights.findElement(By.id(num)).click();
				Thread.sleep(2000);
				break;
			}else {
				flights.findElement(dateNxtBtn).click();
			}
		}
//		int dateVal;
//		WebElement datePicker;
//		int i=0; //counter
//		while(true) {
//			months= flights.findElements(By.cssSelector("div.uitk-date-picker-month h2"));
//			dateTable=flights.findElements(By.cssSelector("table.uitk-date-picker-weeks"));
//			if(months.get(i).getText().contains(monthName)) {
//				Thread.sleep(2000);
//				datePicker = dateTable.get(i);
//				dateBtn=datePicker.findElements(By.cssSelector("tbody tr td button"));
//				for(int j=0; j<dateBtn.size(); j++) {
//					dateVal = Integer.parseInt(dateBtn.get(j).getAttribute("data-day"));
//					if(dateVal==dateNum) {
//						dateBtn.get(j).click();
//						Thread.sleep(3000);
//						break;
//					}
//				}
//				break;
//			}
//			i++;
//			if(i==2) {
//				btnClick=flights.findElements(By.xpath("//div[@class='uitk-calendar']/div/button"));
//				//for(int m=0;m<btnClick.size();m++) {
//					btnClick.get(1).click();
//					Thread.sleep(1000);
//					btnClick.get(1).click();
//				i=0;
//			}
//					
//				
//		}
	}
    
    public static void callProperties() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(".//Resources//config.properties");
		prop.load(fis);

	}
}

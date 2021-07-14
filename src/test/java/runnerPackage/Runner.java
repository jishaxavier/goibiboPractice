package runnerPackage;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/Features/homepg.feature"},
		//tags = {"@login","@smoke"},// --->or
		//tags = {"@login"} , {"@smoke"} //--->and
		//tags = {"@tag"},
		//dryRun = false,
		//monochrome = true,
		//strict = true, 
				
		//plugin ={"pretty" , "html:ReportsLogin/html/result.html"},
//		plugin = {"pretty" ,"html:ReportsLogin/html/result.html" ,
//                        "json:ReportsLogin/json/cucumber.json" ,
//                        "junit:ReportsLogin/xml/cucumber.xml"},

		glue = {"stepDefinition"}
		
		)
public class Runner {

}

package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.LastPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private LastPage lastPage = LastPage.getInstance();

    @GetMapping("/")
    public String welcome() {
        if (lastPage.getLastPagePath().equals("/owners/find")) {
            Toggle.logFindOwnerData("Visited Welcome Page\n");
        }
        Toggle.logWelcomePageVetData("Visited Welcome Page\n");
        lastPage.setLastPagePath("/");
        logger.info("Homepage accessed, Welcome has been returned");

        return Toggle.getWelcomePageVetToggle() ? "welcomePageVetOn" : "welcome";
    }

    @GetMapping("/welcomePageVetToggle")
    public RedirectView redirectAfterToggle() {
        Toggle.toggleWelcomePageVet();
        return new RedirectView("/");
    }

    @GetMapping("/welcomePageVetAnalysis")
    public RedirectView redirectAfterAnalysis() throws IOException {
        Toggle.logWelcomePageVetData("Analyzing Data. Null Hypothesis for welcomePageVetToggle: Toggle ON will generate the same number of clicks to Vet button as Toggle OFF.\n");

        double visitedHomepageToggleOn = 0;
        double visitedHomepageToggleOff = 0;
        double visitedVetToggleOn = 0;
        double visitedVetToggleOff = 0;

        BufferedReader logReader;
        String line;

        logReader = new BufferedReader(new FileReader("src/main/resources/logging/newWelcomePageVet.txt"));
        line = logReader.readLine();
        while ((line != null)) {
            if(line.equals("Visited Welcome Page")){
                visitedHomepageToggleOn++;
            } else if(line.equals("Visited Vet Page")){
                visitedVetToggleOn++;
            }

            line = logReader.readLine();
        }

        logReader = new BufferedReader(new FileReader("src/main/resources/logging/oldWelcomePageVet.txt"));
        line = logReader.readLine();
        while ((line != null)) {
            if(line.equals("Visited Welcome Page")){
                visitedHomepageToggleOff++;
            } else if(line.equals("Visited Vet Page")){
                visitedVetToggleOff++;
            }

            line = logReader.readLine();
        }

        System.out.println("===== Analyzing Welcome Page to Vet Page Toggle... =====");
        System.out.println("WITH TOGGLE DISABLED:");
        System.out.println("users that visited welcome page = " + visitedHomepageToggleOff);
        System.out.println("users that visited vet page = " + visitedVetToggleOff);
        System.out.println("calculated conversion ratio = " + (visitedVetToggleOff/visitedHomepageToggleOff));
        System.out.println("WITH TOGGLE ENABLED:");
        System.out.println("users that visited welcome page = " + visitedHomepageToggleOn);
        System.out.println("users that visited vet page = " + visitedVetToggleOn);
        System.out.println("calculated conversion ratio = " + (visitedVetToggleOff/visitedHomepageToggleOn));

        return new RedirectView("/");
    }
}

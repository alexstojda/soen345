package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.LastPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private LastPage lastPage = LastPage.getInstance();

    @GetMapping("/")
    public String welcome() {
        if (lastPage.getLastPagePath().equals("/owners/find")) {
            Toggle.logData("Visited Welcome Page\n");
        }
        lastPage.setLastPagePath("/");
        logger.info("Homepage accessed, Welcome has been returned");
        return "welcome";
    }
}

package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.LastPage;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

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

    @GetMapping("/toggle")
    public RedirectView redirectWithUsingRedirectView() {
        Toggle.toggleWelcomePageVet();
        return new RedirectView("/");
    }
}

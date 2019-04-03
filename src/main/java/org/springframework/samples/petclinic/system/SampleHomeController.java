package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(name = "feature.toggles.homePageCentered", havingValue = "true")
public class SampleHomeController {

    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/home")
    public String home() {

        logger.info("New Sample homepage accessed, Home has been returned");
        return "home";
    }
}

package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")
    public String welcome() {

        if (logger.isInfoEnabled()) {
            logger.info("Logging info level enabled and Welcome has been executed");
        }

        return "welcome";
    }
}

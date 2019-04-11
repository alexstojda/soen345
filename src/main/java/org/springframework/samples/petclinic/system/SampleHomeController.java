package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleHomeController {

    private Logger logger = LoggerFactory.getLogger(SampleHomeController.class);

    @GetMapping("newHome")
    public String welcome() {

        logger.info("New Sample homepage accessed, Home has been returned");
        return "home";
    }
}

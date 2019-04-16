/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.LastPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 * <p/>
 * Also see how a view that resolves to "error" has been added ("error.html").
 */
@Controller
class CrashController {


    private Logger logger = LoggerFactory.getLogger(CrashController.class);
    private LastPage lastPage = LastPage.getInstance();

    @GetMapping("/oups")
    public String triggerException() {
        Toggle.logWelcomeData("END " + System.currentTimeMillis() + "\n");
        if (lastPage.getLastPagePath().equals("/owners/find")) {
            Toggle.logOwnerData("Visited Error Page\n");
        }
        logger.info("Error page returned");
        throw new RuntimeException("Expected: controller used to showcase what "
            + "happens when an exception is thrown");
    }

}

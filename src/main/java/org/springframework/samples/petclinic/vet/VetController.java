/*
 * Copyright 2012-2018 the original author or authors.
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
package org.springframework.samples.petclinic.vet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.LastPage;
import org.springframework.samples.petclinic.system.PaymentAnalyser;
import org.springframework.samples.petclinic.system.Toggle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class VetController {

    private final VetRepository vets;
    private Logger logger = LoggerFactory.getLogger(VetController.class);
    private LastPage lastPage = LastPage.getInstance();
    private PaymentAnalyser paymentAnalyser;

    public VetController(VetRepository clinicService) {
        this.vets = clinicService;
        this.paymentAnalyser = new PaymentAnalyser();
    }

    @GetMapping("/vets.html")
    public String showVetList(Map<String, Object> model) {
        Toggle.logWelcomeData("END " + System.currentTimeMillis() + "\n");
        if (lastPage.getLastPagePath().equals("/owners/find")) {
            Toggle.logOwnerData("Visited Vet Page\n");
        }
        lastPage.setLastPagePath("/vets.html");
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        model.put("vets", vets);
        logger.info("Vets page returned");
        if(!Toggle.getPaymentToggle())
            return "vets/vetList";
        else{
            paymentAnalyser.logPayment(2);
            return "vets/vetList2";
        }

    }

    @GetMapping("/payment")
    public String test(Map<String, Object> model){
        if(!Toggle.getPaymentToggle())
            return "redirect:/";
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        model.put("vets", vets);
        paymentAnalyser.logPayment(1);
        return "vets/payVets";
    }

    @PostMapping("/payment")
    public String success(){

        if(!Toggle.getPaymentToggle())
            return "redirect:/";
       paymentAnalyser.logPayment(0);
        return "vets/success";
    }

    @GetMapping("/payment/toggle")
    public String togglePayment(Map<String, Object> model){
        Toggle.togglePayment();
        return "redirect:/";
    }

    @GetMapping("/payment/calculate")
    public String calculateValue(){
        if(Toggle.getPaymentToggle())
            paymentAnalyser.generateReport();
        return "redirect:/";
    }

    @GetMapping("/payment/cheat")
    public String cheat(){
        if(Toggle.getPaymentToggle())
            this.paymentAnalyser = new PaymentAnalyser(200, 215, 210);
        return "redirect:/";
    }


    @GetMapping({ "/vets" })
    public @ResponseBody Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        logger.info("Vets List returned");
        return vets;
    }

}

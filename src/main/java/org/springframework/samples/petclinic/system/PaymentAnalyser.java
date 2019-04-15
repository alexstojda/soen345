package org.springframework.samples.petclinic.system;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class PaymentAnalyser {
    private int completedPayments;
    private int possibleInteractions;
    private int startedPayments;

    //I guess this can be used if we ever need to resume after reseting the system. i don't know where this would happen.
    //but planning for the future.
    public PaymentAnalyser(int completedPayments, int possibleInteractions, int startedPayments) {
        this.completedPayments = completedPayments;
        this.possibleInteractions = possibleInteractions;
        this.startedPayments = startedPayments;
    }

    public PaymentAnalyser() {
        this.completedPayments = 0;
        this.possibleInteractions = 0;
        this.startedPayments = 0;
    }

    public void logPayment(int status) {
        if (status == 0) {
            incrementCompleted();
        } else if (status == 1) {
            incrementStarted();
        } else if (status == 2) {
            incrementPossible();
        }
    }

    private void incrementCompleted() {
        this.completedPayments = completedPayments + 1;
    }

    private void incrementStarted() {
        this.possibleInteractions = possibleInteractions + 1;
    }

    private void incrementPossible() {
        this.startedPayments = startedPayments + 1;
    }

    public void generateReport() {
        double complete = completedPayments;
        double interacted = possibleInteractions;
        double started = startedPayments;
        DecimalFormat df2 = new DecimalFormat(".##");
        String data = "";
        if(possibleInteractions < 100){
            data = "We do not have enough data to reject our hypothesis\n" +
                "Null Hypothesis: users do not want to make early payments";
        }
        else {
            data ="Null Hypothesis: users do not want to make early payments\n";
            double efficiencyRatio = complete / interacted;
            double completionRatio = complete / started;
            double startRatio = started/interacted;

            data = data+df2.format(startRatio)+ " of users who see the possbile payment" +
                "start filling up transaction\n";
            data = data+df2.format(completionRatio)+ " of users who start a payment" +
                "complete the transaction\n";
            data = data+df2.format(efficiencyRatio)+ " of users who see the possbile " +
                "early payment button complete the transaction\n";
            efficiencyRatio = efficiencyRatio*100;
            if (efficiencyRatio > 90){
                data = data+df2.format(efficiencyRatio)+"% of users make payments in advance " +
                    "and this is enough to disprove our null hypothesis.";
            } else {
                data = data+df2.format(efficiencyRatio)+"% of users make payments in advance " +
                    "and this is not enough to disprove our null hypothesis.";
            }
        }
        FileWriter fileWriter = null;
        try {
            final String path = "src/main/resources/logging/";
            fileWriter = new FileWriter(path + "paymentSystemLog.txt", false);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

}

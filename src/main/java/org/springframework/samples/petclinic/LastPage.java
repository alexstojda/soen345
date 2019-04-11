package org.springframework.samples.petclinic;

public class LastPage {
    private static LastPage lastPage = null;
    private String lastPagePath;

    private LastPage() {
        lastPagePath = "";
    }

    public static LastPage getInstance() {
        if (lastPage == null) {
            lastPage = new LastPage();
        }
        return lastPage;
    }

    public String getLastPagePath() {
        return lastPagePath;
    }

    public void setLastPagePath(String s) {
        lastPagePath = s;
    }
}

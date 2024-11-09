package com.example.sprint1_main.model;

public class ApplicationManagerModel {
    private static volatile ApplicationManagerModel applicationManager;

    private UserModel currentUser;
    private DestinationModel currentDestination;


    private ApplicationManagerModel() {
        this.currentUser = new UserModel("email", "number", "name", 10, "tempUser", "password");
        this.currentDestination = null;
    }

    public static ApplicationManagerModel getInstance() {
        if (applicationManager == null) {
            synchronized (ApplicationManagerModel.class) {
                if (applicationManager == null) {
                    applicationManager = new ApplicationManagerModel();
                }
            }
        }
        return applicationManager;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public DestinationModel getCurrentDestination() {
        return currentDestination;
    }

    public void setCurrentDestination(DestinationModel currentDestination) {
        this.currentDestination = currentDestination;
    }
}

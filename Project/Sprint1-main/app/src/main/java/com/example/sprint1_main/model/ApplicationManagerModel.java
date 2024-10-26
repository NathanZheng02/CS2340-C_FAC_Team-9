package com.example.sprint1_main.model;

public class ApplicationManagerModel {
    private volatile static ApplicationManagerModel applicationManager;

    private UserModel currentUser;
    private DestinationModel currentDestination;


    private ApplicationManagerModel() {
        this.currentUser = null;
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

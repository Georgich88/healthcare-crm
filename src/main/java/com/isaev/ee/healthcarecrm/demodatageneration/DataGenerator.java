package com.isaev.ee.healthcarecrm.demodatageneration;

import java.util.List;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;


public class DataGenerator {

    public static Building generateDemoBuilding() {
    	
        Building mainBuilding = new Building("Main building");
        
        mainBuilding.addNewRoom("F1 - 101");
        mainBuilding.addNewRoom("F1 - 102");
        mainBuilding.addNewRoom("F2 - 201");
        mainBuilding.addNewRoom("F2 - 202");
        mainBuilding.addNewRoom("F2 - 203");
        
        return mainBuilding;
        
    }

}

package com.isaev.ee.healthcarecrm.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.facilities.BuildingDao;
import com.isaev.ee.healthcarecrm.demodatageneration.DataGenerator;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;

@Component
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
    private BuildingDao buildingDao;

    @Override
    public void run(String... args) throws Exception {

        Building mainBuilding = DataGenerator.generateDemoBuilding();        
        this.buildingDao.save(mainBuilding);

    }

}

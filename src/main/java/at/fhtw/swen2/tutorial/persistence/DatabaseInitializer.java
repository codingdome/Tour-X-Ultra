package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.PersonRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PersonEntity> personList = new ArrayList<>();
        List<TourEntity> tourList = new ArrayList<>();
        TourEntity tourEntity = TourEntity.builder().name("Geile Tour").description("Die Tour ist einfach der Hammer").type("test").origin("Wien").to("Putin").distance("test").time("time").information("test").build();

        personList.add(PersonEntity.builder().id(5L).name("John").isEmployed(true).build());
        personList.add(PersonEntity.builder().id(7L).name("Albert").isEmployed(true).build());
        personList.add(PersonEntity.builder().id(11L).name("Monica").isEmployed(true).build());

        personRepository.saveAll(personList);

        tourRepository.save(tourEntity);

//        List<TourEntity> tourList = new ArrayList<>();
//        tourList.add(TourEntity.builder().id(19L).name("testName").description("testDescription").type("testType").from("fromCity").to("toCity").distance("100km").build());
//
//        tourRepository.saveAll(tourList);
//
//
//        System.out.println(tourRepository.findAll());
    }
}

package at.fhtw.swen2.tourxultra.persistence;

import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.persistence.repositories.LogRepository;
import at.fhtw.swen2.tourxultra.persistence.repositories.TourRepository;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private LogRepository logRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        List<TourEntity> tourEntityList = new ArrayList<>();
        List<LogEntity> logEntityList = new ArrayList<>();

        tourEntityList.add(TourEntity.builder().id(1L).name("Wald Spaziergang").description("Eine schöne kleine Runde durch den Wald!").departure("Prater").arrival("Floridsdorf").transport("Laufen").distance(4000).duration(60).build());
        tourEntityList.add(TourEntity.builder().name("Stadt Radtour").description("Eine schöne Tour durch die Innenstadt mit dem Fahrrad!").departure("Schwedenplatz").arrival("Karlsplatz").transport("Fahrrad").distance(6000).duration(90).build());
        tourEntityList.add(TourEntity.builder().name("Wassersporttag").description("Ein Tag voller Spaß auf dem Wasser!").departure("Donauinsel").arrival("Alte Donau").transport("Kajak").distance(8000).duration(240).build());
        tourEntityList.add(TourEntity.builder().name("Bergwanderung").description("Eine anspruchsvolle Wanderung durch die Berge!").departure("Innsbruck").arrival("Garmisch-Partenkirchen").transport("Wandern").distance(15000).duration(480).build());
        tourEntityList.add(TourEntity.builder().name("Klettertour").description("Eine Klettertour auf einem atemberaubenden Felsen!").departure("Grinzing").arrival("Leopoldsberg").transport("Klettern").distance(3000).duration(120).build());
        tourEntityList.add(TourEntity.builder().name("Segway Tour").description("Eine lustige Segway-Tour durch die Stadt!").departure("Hauptbahnhof").arrival("Praterstern").transport("Segway").distance(10000).duration(150).build());
        tourEntityList.add(TourEntity.builder().name("Schneeschuhwanderung").description("Eine Winterwanderung durch die verschneite Landschaft!").departure("Seefeld").arrival("Mittenwald").transport("Schneeschuhwandern").distance(8000).duration(180).build());
        tourEntityList.add(TourEntity.builder().name("City Skateboard Tour").description("Eine coole Tour durch die Stadt auf dem Skateboard!").departure("Museumsquartier").arrival("Rathausplatz").transport("Skateboard").distance(5000).duration(120).build());
        tourEntityList.add(TourEntity.builder().name("Wien Rundfahrt").description("Eine ausgedehnte Tour durch die Stadt mit dem Auto!").departure("Stadtpark").arrival("Schloss Schönbrunn").transport("Auto").distance(25000).duration(360).build());
        tourEntityList.add(TourEntity.builder().name("Schnorchel-Abenteuer").description("Eine aufregende Schnorcheltour im Mittelmeer!").departure("Ibiza").arrival("Formentera").transport("Boot").distance(10000).duration(300).build());
        tourEntityList.add(TourEntity.builder().name("Golf Tag").description("Ein Tag voller Golfspaß auf dem Platz!").departure("Klosterneuburg").arrival("Tullnerfeld").transport("Golf-Cart").distance(6000).duration(300).build());
        tourEntityList.add(TourEntity.builder().name("Frankfurt Rundfahrt").description("Eine ausgedehnte Tour durch die Stadt mit dem Auto!").departure("Frankfurt Bahnhof").arrival("Schloss Frankfurt").transport("Auto").distance(25000).duration(360).build());
        
        logRepository.saveAll(logEntityList);
        tourRepository.saveAll(tourEntityList);

    }
}

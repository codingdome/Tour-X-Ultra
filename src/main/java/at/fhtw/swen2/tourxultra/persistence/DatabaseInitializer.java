package at.fhtw.swen2.tourxultra.persistence;

import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.persistence.repositories.LogRepository;
import at.fhtw.swen2.tourxultra.persistence.repositories.TourRepository;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.io.MapQuestApiAssistant;
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

    private MapQuestApiAssistant mapQuestApiAssistant = new MapQuestApiAssistant();


    @Override
    public void afterPropertiesSet() throws Exception {

        logRepository.deleteAll();
        tourRepository.deleteAll();

        List<TourEntity> tourEntityList = new ArrayList<>();
        List<LogEntity> logEntityList = new ArrayList<>();

        tourEntityList.add(TourEntity.builder().name("Wien Salzburg Tour").description("Eine schöne Tour mit dem Fahrrad von Wien nach Salzbug").departure("Wien").arrival("Salzburg").transport("Fahrrad").distance(30000).duration(500).build());

        tourEntityList.add(TourEntity.builder().name("Geile Mountainbike Tour").description("Bock auf Action mit dem Mountainbike?").departure("Linz").arrival("Krems").transport("Mountainbike").distance(3000).duration(240).build());

        tourEntityList.add(TourEntity.builder().name("München Berlin Tour").description("Fahrradtour von München nach Berlin").departure("München").arrival("Berlin").transport("Fahrrad").distance(60000).duration(1000).build());

        tourEntityList.add(TourEntity.builder().name("Romantische Rhein Tour").description("Entdecke den romantischen Rhein").departure("Köln").arrival("Mainz").transport("Schiff").distance(20000).duration(360).build());

        tourEntityList.add(TourEntity.builder().name("Alpenwanderung").description("Erkunde die majestätischen Alpen").departure("Innsbruck").arrival("Garmisch").transport("Wandern").distance(15000).duration(720).build());

        tourEntityList.add(TourEntity.builder().name("Mosel Radtour").description("Entlang der Mosel mit dem Fahrrad").departure("Trier").arrival("Koblenz").transport("Fahrrad").distance(10000).duration(300).build());

        tourEntityList.add(TourEntity.builder().name("Bayerischer Wald Trekking").description("Trekkingabenteuer im Bayerischen Wald").departure("Regensburg").arrival("Passau").transport("Wandern").distance(20000).duration(480).build());

        tourEntityList.add(TourEntity.builder().name("Spreewald Paddeltour").description("Erkunde den Spreewald mit dem Kanu").departure("Berlin").arrival("Lübbenau").transport("Kanu").distance(5000).duration(120).build());

        tourEntityList.add(TourEntity.builder().name("Neuschwanstein Radtour").description("Fahrradtour zum Schloss Neuschwanstein").departure("Füssen").arrival("Schwangau").transport("Fahrrad").distance(8000).duration(180).build());

        tourEntityList.add(TourEntity.builder().name("Bodensee Rundfahrt").description("Rund um den Bodensee mit dem Fahrrad").departure("Konstanz").arrival("Lindau").transport("Fahrrad").distance(26000).duration(720).build());

        tourEntityList.add(TourEntity.builder().name("Schwarzwald Motorradtour").description("Motorradabenteuer im Schwarzwald").departure("Freiburg").arrival("Baden-Baden").transport("Motorrad").distance(15000).duration(360).build());

        tourEntityList.add(TourEntity.builder().name("Berchtesgadener Land Wanderung").description("Entdecke das Berchtesgadener Land zu Fuß").departure("Berchtesgaden").arrival("Königssee").transport("Wandern").distance(10000).duration(300).build());

        tourEntityList.add(TourEntity.builder().name("Wien Salzburg Tour").description("Eine schöne Tour mit dem Fahrrad von Wien nach Salzburg, entlang malerischer Llt an charmanten Weingütern.").departure("Wien").arrival("Salzburg").transport("Fahrrad").distance(30000).duration(500).build());

        tourEntityList.add(TourEntity.builder().name("Geile Mountainbike Tour").description("Bock auf Action mit dem Mountainbike? ussicht auf die Donau und die Weinberge.").departure("Linz").arrival("Krems").transport("Mountainbike").distance(3000).duration(240).build());

        tourEntityList.add(TourEntity.builder().name("München Berlin Tour").description("Entdecken Sie Deutschland allische Landschaften und besuchen Sie berühmte Sehenswürdigkeiten entlang des Weges.").departure("München").arrival("Berlin").transport("Fahrrad").distance(60000).duration(1000).build());

        tourEntityList.add(TourEntity.builder().name("Romantische Rhein Tour").description("Tauchen Sie ein in die romantische Atmos an Bord, probieren Sie lokale Weine und erleben Sie unvergessliche Sonnenuntergänge.").departure("Köln").arrival("Mainz").transport("Schiff").distance(20000).duration(360).build());

        tourEntityList.add(TourEntity.builder().name("Alpenwanderung").description("Erleben Sie die majestätischen Alpen bei einer Flora und Fauna und lassen Sie sich von der Ruhe und Schönheit der Natur inspirieren.").departure("Innsbruck").arrival("Garmisch").transport("Wandern").distance(15000).duration(720).build());

        tourEntityList.add(TourEntity.builder().name("Mosel Radtour").description("Erkunden Sie die malerische einer Schifffahrt auf der Mosel und lassen Sie sich von der Schönheit der Natur verzaubern.").departure("Trier").arrival("Koblenz").transport("Fahrrad").distance(10000).duration(300).build());

        tourEntityList.add(TourEntity.builder().name("Bayerischer Wald Trekking").description("Tauchen Sie ein in die raubenden Ausblicken. Übernachten Sie in gemütlichen Berghütten, genießen Sie rege die Stille und Magie der Natur.").departure("Regensburg").arrival("Passau").transport("Wandern").distance(20000).duration(480).build());

        tourEntityList.add(TourEntity.builder().name("Spreewald Paddeltour").description("Entdecken Sie den einzigle der Natur, beobachten Sie seltene Vogelarten und lassen Sie sich von der Magie des Spreewalds verzaubern.").departure("Berlin").arrival("Lübbenau").transport("Kanu").distance(5000).duration(120).build());

        tourEntityList.add(TourEntity.builder().name("Neuschwanstein Radtour").description("Erleben Sie eine faszinierende Fahrradtour zum berühmten Schlossfte Schloss, erkunden Sie die umliegenden Seen und lassen Sie sich von der Romantik des bayerischen Alpenvorlandes begeistern.").departure("Füssen").arrival("Schwangau").transport("Fahrrad").distance(8000).duration(180).build());

        tourEntityList.add(TourEntity.builder().name("Bodensee Rundfahrt").description("Unternehmen Sie eine abwechslungsreiche Fahrradrundfahrt um den traumhafund glitzernden Gewässern. Erleben Sie die  genießen Sie die wunderbare Aussicht auf die Alpen.").departure("Konstanz").arrival("Lindau").transport("Fahrrad").distance(26000).duration(720).build());

        tourEntityList.forEach(tourEntity -> {
            tourEntity.setImgUrl(mapQuestApiAssistant.returnImgUrl(tourEntity.getDeparture(), tourEntity.getArrival()));
        });

        tourRepository.saveAll(tourEntityList);

        logRepository.saveAll(logEntityList);


    }
}

package at.fhtw.swen2.tourxultra.presentation;

import at.fhtw.swen2.tourxultra.TourXUltraApplicationBoot;
import at.fhtw.swen2.tourxultra.presentation.events.ApplicationStartupEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TourXUltraApplication extends Application {

    private Logger logger = LoggerFactory.getLogger(TourXUltraApplication.class);
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) throws Exception {
        logger.debug("Starting TutorialApplication");

        // Create the root node
        Group root = new Group();

        // Create the scene with the root node
        Scene scene = new Scene(root);

        // Set the background color
        scene.setFill(Color.web("#ffffff"));

        // Set the scene to the primary stage
        stage.setScene(scene);

        applicationContext.publishEvent(new ApplicationStartupEvent(this, stage));
    }

    @Override
    public void init() {
        logger.debug("Initializing Spring ApplicationContext");

        applicationContext = new SpringApplicationBuilder(TourXUltraApplicationBoot.class)
                .sources(TourXUltraApplicationBoot.class)
                .initializers(initializers())
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void stop() throws Exception {
        logger.debug("Stopping TutorialApplication");

        applicationContext.close();
        Platform.exit();
    }

    ApplicationContextInitializer<GenericApplicationContext> initializers() {
        return ac -> {
            ac.registerBean(Parameters.class, this::getParameters);
            ac.registerBean(HostServices.class, this::getHostServices);
        };
    }
}

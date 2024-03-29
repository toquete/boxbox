```mermaid
%%{init: {'theme':'dark'}}%%
flowchart LR
    APP[:app] ==> UI
    APP ==> COMMON
    APP ==> MODEL
    APP ==> TESTING
    APP ==> PREFERENCES
    APP ==> DRIVER_STANDINGS
    APP ==> DRIVER_IMAGES
    APP ==> CONSTRUCTOR_STANDINGS
    APP ==> CONSTRUCTOR_IMAGES
    APP ==> COUNTRIES
    APP ==> STANDINGS
    APP ==> SETTINGS
    APP ==> RACES
    APP ==> CIRCUIT_IMAGES
    APP ==> RACES_FEATURE
    subgraph :core
        direction LR
        MODEL[:model]
        TESTING[:testing]
        UI[:ui]
        COMMON[:common]
        PREFERENCES[:preferences]
    end
    subgraph :data
        DRIVER_STANDINGS[:driverstandings]
        DRIVER_IMAGES[:driverimages]
        CONSTRUCTOR_STANDINGS[:constructorstandings]
        CONSTRUCTOR_IMAGES[:constructorimages]
        COUNTRIES[:countries]
        RACES[:races]
        CIRCUIT_IMAGES[:circuitimages]
    end
    subgraph :feature
        STANDINGS[:standings]
        SETTINGS[:settings]
        RACES_FEATURE[:races]
    end
```

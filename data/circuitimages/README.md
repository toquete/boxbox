```mermaid
%%{init: {'theme':'dark'}}%%
flowchart LR
    CIRCUIT_IMAGES ==> DATABASE
    CIRCUIT_IMAGES ==> COMMON
    CIRCUIT_IMAGES ==> MODEL
    CIRCUIT_IMAGES ==> TESTING
    CIRCUIT_IMAGES ==> NETWORK
    subgraph :core
        direction LR
        MODEL[:model]
        TESTING[:testing]
        DATABASE[:database]
        COMMON[:common]
        NETWORK[:network]
    end
    subgraph :data
        CIRCUIT_IMAGES[:circuitimages]
    end
```

```mermaid
%%{init: {'theme':'dark'}}%%
flowchart LR
    CONSTRUCTOR_IMAGES ==> DATABASE
    CONSTRUCTOR_IMAGES ==> COMMON
    CONSTRUCTOR_IMAGES ==> MODEL
    CONSTRUCTOR_IMAGES ==> TESTING
    CONSTRUCTOR_IMAGES ==> NETWORK
    subgraph :core
        direction LR
        MODEL[:model]
        TESTING[:testing]
        DATABASE[:database]
        COMMON[:common]
        NETWORK[:network]
    end
    subgraph :data
        CONSTRUCTOR_IMAGES[:constructorimages]
    end
```

```mermaid
%%{init: {'theme':'dark'}}%%
flowchart LR
    DATABASE ==> MODEL
    DATABASE ==> TESTING
    subgraph :core
        direction LR
        MODEL[:model]
        TESTING[:testing]
        DATABASE[:database]
    end
```

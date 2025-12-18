# Neural Network Framework - Rapport de Projet

Ce document présente les réponses aux questions de conception, d'implémentation et de test, ainsi que des propositions d'amélioration pour le framework de réseau de neurones.

## 1. Conception (Design Patterns)

La conception du framework repose sur plusieurs patrons de conception pour assurer modularité et extensibilité, conformément au cahier des charges :

*   **Composite / Structure Objet** : Le modèle est structuré hiérarchiquement.
    *   `NeuralNetwork` est composé de `Layer`s.
    *   `Layer` est composé de `Neuron`s.
    *   `Neuron` possède des `Connection`s entrantes.
*   **Strategy Pattern** : Utilisé pour rendre les fonctions d'activation interchangeables au niveau de chaque neurone.
    *   Interface : `ActivationFunction`.
    *   Implémentations concrètes : `Sigmoid`, `Softmax`, `Identity`.
    *   *Intérêt* : Permet de changer la logique d'activation sans modifier la classe `Neuron`.
*   **Adapter Pattern** : Utilisé pour intégrer des composants legacy.
    *   `ActivationAdapter` permet d'utiliser la classe `OldActivationImpl` (qui a une signature différente) comme une `ActivationFunction` standard.
    *   *Intérêt* : Réutilisation de code existant sans casser l'interface du framework.
*   **State Pattern** : Gère le cycle de vie du réseau.
    *   États : `ConstructionState`, `TrainingState`, `TrainedState`.
    *   Le contexte `NeuralNetwork` délègue les comportements (`train`, `fit`, `predict`) à l'état courant.
    *   *Intérêt* : Assure que certaines actions (comme `predict`) ne sont exécutables que dans l'état approprié (Trained).
*   **Observer Pattern** : Permet le monitoring.
    *   `NeuralNetwork` notifie les `NetworkObserver` (ex: `Dashboard`) lors des changements d'état.
    *   *Intérêt* : Découplage entre le moteur de calcul et l'interface de visualisation ou de log.

## 2. Code et Structure (Aspects Techniques)

Le projet est implémenté en **Java** et utilise **Maven** pour la gestion de projet.

### Structure des Packages
*   `ma.enset.glsid.neural` : Cour du domaine (`Neuron`, `Layer`, `NeuralNetwork`).
*   `ma.enset.glsid.activation` : Stratégies et adaptateurs d'activation.
*   `ma.enset.glsid.state` : Classes du pattern State.
*   `ma.enset.glsid.observer` : Interfaces et implémentations des observateurs.
*   `ma.enset.glsid.aspect` : Aspects AOP.

### Programmation Orientée Aspect (AOP)
Nous utilisons **AspectJ** pour séparer les préoccupations transversales :
*   `LoggingAspect` : Intercepte toutes les méthodes de `NeuralNetwork` pour tracer l'exécution (`@Before`).
*   `SecurityAspect` : Sécurité via `@SecuredByAspect` pour vérifier les droits d'accès avant exécution (`@Around`), simulant une couche de sécurité.

## 3. Tests et Vérification

La classe `Main.java` sert de scénario de test principal et démontre :
1.  **Construction** : Création d'un réseau, ajout de couches, configuration des stratégies (y compris via l'Adaptateur).
2.  **Transitions d'État** : Passage de `Construction` -> `Training` -> `Trained`.
3.  **Validation des Contraintes** : Tentative de prédiction avant entraînement (qui échoue comme prévu) puis après entraînement (qui réussit).
4.  **AOP en Action** : Les logs apparaissent dans la console et la "sécurité" valide l'accès lors de la prédiction.

Pour exécuter les tests : `mvn exec:java`

---

## 4. Améliorations et Propositions (Design Patterns Supplémentaires)

Pour faire évoluer le framework, nous proposons l'ajout des patterns suivants :

### A. Builder Pattern (Construction)
Actuellement, la construction du réseau se fait par appels successifs à `addLayer`. Pour des réseaux complexes, un **Builder** fluidifierait la création :
```java
NeuralNetwork nn = new NeuralNetworkBuilder()
    .addInputLayer(3)
    .addHiddenLayer(5, new Sigmoid())
    .addOutputLayer(2, new Softmax())
    .build();
```

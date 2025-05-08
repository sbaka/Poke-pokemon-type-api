# Poke-pokemon-type-api

## Description
Bienvenue dans **Poke-pokemon-type-api** ! Ce projet est une API dédiée aux types de Pokémon, conçue pour fournir des informations détaillées et des fonctionnalités spécifiques basées sur les types des Pokémon.

## Technologies Utilisées
- **Java** : Le langage principal utilisé pour développer cette API, représentant 98,1 % du code.
- **Docker** : Utilisé pour la conteneurisation et le déploiement, représentant 1,9 % du projet.

## Fonctionnalités
- Fournir des informations sur les types de Pokémon.
- Gestion des relations entre différents types (faiblesses, résistances, immunités).
- Facilement intégrable dans d'autres projets ou applications Pokémon.

## Prérequis
- **Java** installé sur votre système.
- **Docker** (facultatif) pour exécuter l'API dans un environnement conteneurisé.

## Installation et Utilisation
1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/sbaka/Poke-pokemon-type-api.git
   ```
2. Naviguez dans le répertoire :
   ```bash
   cd Poke-pokemon-type-api
   ```
3. Compilez et exécutez le projet:
   ```bash
   ./gradlew bootRun
   ```
4. (Optionnel) Exécutez via Docker :
   ```bash
   docker build -t poke-pokemon-type-api .
   docker run -p 8080:8080 poke-pokemon-type-api
   ```

## Contribution
Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une pull request ou à signaler un problème.

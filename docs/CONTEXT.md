# Contesto tecnico — Student Management API

Aggiornato: 2026-09-08

## Obiettivo
API REST persistente per studenti, usata per dimostrare architettura a livelli, JDBC Template, validazione, transazioni ed error handling.

## Stato osservato
- Java 21, Spring Boot 4.1, Web MVC, JDBC Template, MySQL e Jakarta Validation.
- CRUD studenti completo.
- DTO di richiesta, gestione centralizzata degli errori e matricola univoca.
- Configurazione database prevista tramite variabili d'ambiente.

## Limiti / possibili evoluzioni
Il README indica test automatici, OpenAPI e Spring Security come sviluppi successivi. Non considerarli lavoro attivo finché il repository non viene esplicitamente ripreso.

## Vincolo architetturale
La scelta caratterizzante di questo progetto è JDBC Template con SQL esplicito; una migrazione a JPA cambierebbe lo scopo del repository.
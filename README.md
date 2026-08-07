# Student Management API

REST API per la gestione di studenti, sviluppata con Spring Boot, JDBC Template e MySQL. Il progetto applica una struttura a livelli, validazione delle richieste e gestione centralizzata degli errori.

## Funzionalità

- creazione, lettura, aggiornamento ed eliminazione degli studenti;
- persistenza su MySQL tramite `JdbcTemplate`;
- recupero dell'identificativo generato dal database;
- DTO di input separato dal modello;
- validazione Jakarta Bean Validation;
- rifiuto delle proprietà JSON sconosciute;
- gestione centralizzata degli errori `400`, `404`, `409` e `500`;
- transazione sulla creazione dello studente;
- gestione della matricola duplicata tramite vincolo del database.

## Tecnologie

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring JDBC Template
- Jakarta Validation
- MySQL
- Maven Wrapper

## Architettura

```text
controller → service → repository → MySQL
                ↓
         exception handler
```

- `StudentController`: espone gli endpoint HTTP;
- `StudentService`: contiene la logica applicativa e coordina le operazioni;
- `StudentRepository`: esegue query SQL parametrizzate con `JdbcTemplate`;
- `StudentRequest`: definisce e valida il payload in ingresso;
- `GlobalExceptionHandler`: restituisce risposte di errore coerenti.

## Endpoint

| Metodo | Endpoint | Descrizione | Risposta |
|---|---|---|---|
| `GET` | `/api/v1/students` | Elenca tutti gli studenti | `200 OK` |
| `GET` | `/api/v1/students/{id}` | Recupera uno studente | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/students` | Crea uno studente | `201 Created` |
| `PUT` | `/api/v1/students/{id}` | Sostituisce i dati di uno studente | `204 No Content` |
| `DELETE` | `/api/v1/students/{id}` | Elimina uno studente | `204 No Content` |

### Payload di esempio

```json
{
  "firstName": "Mario",
  "lastName": "Rossi",
  "matricola": "12345678",
  "age": 24,
  "university": "Sapienza Università di Roma"
}
```

Regole principali: nome, cognome e università sono obbligatori; la matricola deve contenere almeno otto cifre; l'età deve essere almeno `18`.

## Database

Schema minimo compatibile con le query del repository:

```sql
CREATE DATABASE gestionale_studenti;
USE gestionale_studenti;

CREATE TABLE studenti (
    idStudent BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    matricola VARCHAR(50) NOT NULL UNIQUE,
    age INT NOT NULL,
    university VARCHAR(150) NOT NULL,
    CONSTRAINT chk_student_age CHECK (age >= 18)
);
```

## Configurazione e avvio

### Requisiti

- JDK 21
- MySQL

Configura la connessione in `src/main/resources/application.properties` usando credenziali locali. Prima di rendere pubblico il repository, sostituisci i valori personali con variabili d'ambiente o un profilo di esempio.

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/gestionale_studenti}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
```

Avvio su Linux o macOS:

```bash
./mvnw spring-boot:run
```

Avvio su Windows:

```powershell
mvnw.cmd spring-boot:run
```

Il servizio sarà disponibile su `http://localhost:8080`.

## Stato e prossimi passi

Il progetto è un esercizio backend completo sul flusso JDBC. Evoluzioni previste: test automatici, documentazione OpenAPI, Spring Security e autenticazione.

## Autore

Sviluppato da [Fabio Zagaria](https://github.com/fabiozagaria).

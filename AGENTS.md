# Istruzioni per assistenti AI

## Scopo
REST API didattica per la gestione persistente di studenti con Spring Boot, JDBC Template e MySQL.

## Regole operative
- Leggere `README.md` e `docs/CONTEXT.md` prima di modifiche ampie.
- Preservare la scelta JDBC Template: non migrare a JPA salvo richiesta esplicita.
- Mantenere separati controller, service e repository.
- Usare query parametrizzate e non costruire SQL concatenando input.
- Prima di dichiarare corretta una modifica, verificare comportamento HTTP, validazione e persistenza pertinenti.

## Fonti di verità
- Il codice è la verità tecnica.
- `README.md` documenta contratto e limiti pubblici.
- `docs/CONTEXT.md` sintetizza stato e possibili evoluzioni.
- Roadmap didattica, voti e ripassi restano in Notion.

## Sicurezza
Non inserire credenziali database reali, token o segreti nel repository.
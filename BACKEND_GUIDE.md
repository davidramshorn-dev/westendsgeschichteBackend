1. Architektur: Drei Schichten (Controller, Service, Repository)

    Controller (Web Layer)

        Aufgabe: HTTP‑Requests empfangen, Validierung, Response zurückgeben.

        Typische Annotationen: @RestController, @RequestMapping, @GetMapping, @PostMapping.

        Beispiel: PlaceController mit Endpoints /api/places.

    Service (Business Logic Layer)

        Aufgabe: Geschäftslogik, Transaktionen, Koordination zwischen Repositories.

        Annotation: @Service.

        Beispielmethoden: addPlace(Place p), getAllPlaces().

    Repository (Data Access Layer)

        Aufgabe: Persistenz, CRUD.

        Spring Data JPA: interface PlaceRepository extends JpaRepository<Place, String>.

        Keine Geschäftslogik hier, nur DB‑Operationen.

Warum diese Trennung?

    Testbarkeit, klare Verantwortlichkeiten, leichter Austausch von Implementierungen, saubere Fehlerbehandlung.

2. Typische Klassen / Beispiele (Code‑Skizzen)

Entity
java

@Entity
public class Place {
  @Id private String id;
  private String name;
  private String description;
  // Getter/Setter
}

Repository
java

public interface PlaceRepository extends JpaRepository<Place, String> {}

Service
java

@Service
public class PlaceService {
  private final PlaceRepository repo;
  public PlaceService(PlaceRepository repo) { this.repo = repo; }
  public Place addPlace(Place p) { return repo.save(p); }
  public List<Place> getAll() { return repo.findAll(); }
}

Controller
java

@RestController
@RequestMapping("/api/places")
public class PlaceController {
  private final PlaceService service;
  @PostMapping public Place add(@RequestBody Place p) { return service.addPlace(p); }
  @GetMapping public List<Place> getAll() { return service.getAll(); }
}

3. Wichtige Commands / Workflows beim Entwickeln

    Build & Run lokal
    bash

    ./mvnw clean package
    java -jar target/*.jar

    Datenbank testen (curl)
    bash

    curl -X POST http://localhost:8080/api/test -H "Content-Type: application/json" -d '{"id":"1","message":"hi"}'
    curl http://localhost:8080/api/test

    DB‑Migrations / Schema

        spring.jpa.hibernate.ddl-auto=update (für Entwicklung). Für Produktion: Flyway oder Liquibase verwenden.

    Logs & Debug

        spring.jpa.show-sql=true zeigt SQL in Logs.

        Render Logs prüfen für Hikari/DB/Port Fehler.

4. Integration Frontend ↔ Backend ↔ Database (Kurz)

    Frontend → Backend: HTTP(S) Requests (fetch / axios). Backend stellt REST API bereit. CORS beachten.

    Backend → Database: Spring Data JPA / Hibernate; DataSource konfiguriert via JDBC URL + Credentials.

    Transaktionen: @Transactional im Service, wenn mehrere DB‑Operationen atomar sein müssen.

5. Tipps für produktiven Workflow

    Nutze Env Vars für Secrets (Render, Supabase).

    Verwende Session Pooler (Supabase) für stabile Verbindungen von Render.

    Lokale Entwicklung: .env oder application-local.properties (nicht committen).

    Backups: regelmäßige Exporte aus Supabase (automatisch oder manuell).

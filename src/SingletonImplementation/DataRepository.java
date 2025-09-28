
package product; // ATTENTION TEAM: THIS PACKAGE WILL CHANGED DEPENDING ON *YOUR* DIRECTORY //

import java.util.HashMap;
import java.util.Map;

// ############################### //
//     SINGLETON IMPLEMENTATION 
// ############################### //

public class DataRepository {
   // --- Singleton instance ---
   // The single, shared instance of DataRepository
   private static DataRepository instance;

   // In-memory storage maps for the race system
   private Map<String, String> racers = new HashMap<>();
   private Map<String, String> races = new HashMap<>();
   private Map<String, String> registrations = new HashMap<>();
   private Map<String, String> results = new HashMap<>();

   // --- Private constructor ---
   // Prevents instantiation from outside this class
   private DataRepository() {
   }

   // --- Global access point (Singleton core) ---
   // Ensures only one instance of DataRepository is ever created.
   // The 'synchronized' keyword makes it thread-safe.
   public static synchronized DataRepository getInstance() {
      if (instance == null) {
         instance = new DataRepository(); // Lazy initialization
      }
      return instance;
   }





   // ########################## //
   //    NON SINGLETON METHODS 
   // ########################## //

   // --- Domain-specific methods ---
   // Manage Racers
   public void addRacer(String racerId, String racerName) {
      this.racers.put(racerId, racerName);
   }

   public String getRacer(String racerId) {
      return this.racers.get(racerId);
   }

   // Manage Races
   public void addRace(String raceId, String raceName) {
      this.races.put(raceId, raceName);
   }

   public String getRace(String raceId) {
      return this.races.get(raceId);
   }

   // Manage Registrations
   public void registerRacer(String registrationId, String racerId) {
      this.registrations.put(registrationId, racerId);
   }

   public String getRegistration(String registrationId) {
      return this.registrations.get(registrationId);
   }

   // Manage Results
   public void addResult(String raceId, String podiumInfo) {
      this.results.put(raceId, podiumInfo);
   }

   public String getResult(String raceId) {
      return this.results.get(raceId);
   }
}

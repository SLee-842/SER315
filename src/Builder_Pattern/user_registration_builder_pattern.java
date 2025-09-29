import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// User entity
public class User {
    private final String username;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final UserRole role;
    private final String creditCardInfo;
    private final License license;
    private final Integer category; // 5-1, where 5 is beginner
    private final LocalDate registrationDate;
    private final List<String> notifications;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
        this.creditCardInfo = builder.creditCardInfo;
        this.license = builder.license;
        this.category = builder.category;
        this.registrationDate = builder.registrationDate;
        this.notifications = builder.notifications;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public UserRole getRole() { return role; }
    public String getCreditCardInfo() { return creditCardInfo; }
    public License getLicense() { return license; }
    public Integer getCategory() { return category; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public List<String> getNotifications() { return notifications; }

    // Builder class
    public static class UserBuilder {
        private final String username;
        private final String email;
        private final String password;
        private String firstName;
        private String lastName;
        private UserRole role = UserRole.RACER; // Default role
        private String creditCardInfo;
        private License license;
        private Integer category = 5; // Default category (beginner)
        private LocalDate registrationDate = LocalDate.now();
        private List<String> notifications = new ArrayList<>();

        public UserBuilder(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder creditCardInfo(String creditCardInfo) {
            this.creditCardInfo = creditCardInfo;
            return this;
        }

        public UserBuilder license(License license) {
            this.license = license;
            return this;
        }

        public UserBuilder category(Integer category) {
            if (category < 1 || category > 5) {
                throw new IllegalArgumentException("Category must be between 1 and 5");
            }
            this.category = category;
            return this;
        }

        public UserBuilder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder notifications(List<String> notifications) {
            this.notifications = notifications;
            return this;
        }

        public User build() {
            // Validation
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalStateException("Username cannot be null or empty");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalStateException("Email cannot be null or empty");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalStateException("Password cannot be null or empty");
            }
            
            // For racers, credit card info is required
            if (role == UserRole.RACER && (creditCardInfo == null || creditCardInfo.trim().isEmpty())) {
                throw new IllegalStateException("Credit card information is required for racers");
            }

            return new User(this);
        }
    }
}

// License entity
class License {
    private final String licenseId;
    private final LocalDate issueDate;
    private final LocalDate expirationDate;
    private final Integer category;
    private final boolean active;

    public License(String licenseId, Integer category) {
        this.licenseId = licenseId;
        this.category = category;
        this.issueDate = LocalDate.now();
        this.expirationDate = issueDate.plusYears(1);
        this.active = true;
    }

    public boolean isValid() {
        return active && LocalDate.now().isBefore(expirationDate);
    }

    // Getters
    public String getLicenseId() { return licenseId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public Integer getCategory() { return category; }
    public boolean isActive() { return active; }
}

// User roles enum
enum UserRole {
    ORGANIZER, RACER, ADMINISTRATOR
}

// Registration service
class UserRegistrationService {
    public User registerRacer(String username, String email, String password, 
                             String firstName, String lastName, String creditCardInfo) {
        
        return new User.UserBuilder(username, email, password)
                .firstName(firstName)
                .lastName(lastName)
                .role(UserRole.RACER)
                .creditCardInfo(creditCardInfo)
                .category(5) // Beginner category
                .build();
    }

    public User registerRacerWithLicense(String username, String email, String password,
                                        String firstName, String lastName, 
                                        String creditCardInfo, String licenseId) {
        
        License license = new License(licenseId, 5); // License for category 5
        
        return new User.UserBuilder(username, email, password)
                .firstName(firstName)
                .lastName(lastName)
                .role(UserRole.RACER)
                .creditCardInfo(creditCardInfo)
                .license(license)
                .category(5)
                .build();
    }

    public User registerOrganizer(String username, String email, String password,
                                 String firstName, String lastName) {
        
        return new User.UserBuilder(username, email, password)
                .firstName(firstName)
                .lastName(lastName)
                .role(UserRole.ORGANIZER)
                .build();
    }
}

// Usage example
public class BikeRacingApp {
    public static void main(String[] args) {
        UserRegistrationService registrationService = new UserRegistrationService();
        
        // Register a racer without license
        User racer1 = registrationService.registerRacer(
            "john_racer", "john@example.com", "password123",
            "John", "Doe", "4111111111111111"
        );
        
        // Register a racer with license
        User racer2 = registrationService.registerRacerWithLicense(
            "jane_racer", "jane@example.com", "password456",
            "Jane", "Smith", "4222222222222222", "LIC-2024-001"
        );
        
        // Register an organizer
        User organizer = registrationService.registerOrganizer(
            "bob_organizer", "bob@example.com", "password789",
            "Bob", "Johnson"
        );
        
        // Custom builder usage
        User customRacer = new User.UserBuilder("custom_racer", "custom@example.com", "pass123")
                .firstName("Custom")
                .lastName("Racer")
                .creditCardInfo("4333333333333333")
                .category(4) // Already at category 4
                .build();
    }
}
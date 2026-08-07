# Travel Agency Application

A comprehensive travel agency platform that simplifies travel planning and booking. Built collaboratively with Java, this application allows users to search flights, book hotels, buses, manage visa applications, and explore travel packages all in one place.

## Features

- **Flight Booking**: Search and book flights with real-time pricing
- **Hotel Reservations**: Browse and book hotels with detailed information
- **Bus Tickets**: Compare and purchase bus tickets for domestic travel
- **Travel Packages**: Explore pre-designed travel packages to popular destinations
- **Visa Management**: Track visa applications and confirm booking
- **User Registration**: Secure user accounts with profile management
- **Booking Management**: View and manage all bookings and reservations
- **Responsive UI**: Clean and user-friendly interface built with Java Swing

## Tech Stack

- **Language**: Java
- **Framework**: Java FX (GUI)
- **Database**: MySQL (MariaDB via XAMPP)
- **Build Tool**: Apache Ant
- **IDE**: NetBeans

## Database Schema

The application uses 7 main tables:

1. **registration** - User accounts and login information
2. **booking** - All travel bookings and reservations
3. **flight** - Flight information and pricing
4. **hotel** - Hotel details and nightly rates
5. **bus** - Bus routes and ticket prices
6. **package** - Pre-designed travel packages
7. **visa** - Visa applications and status tracking

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- XAMPP (includes MySQL/MariaDB and phpMyAdmin)
- NetBeans IDE (or any Java IDE)
- Apache Ant (for building)

### Installation

#### Step 1: Clone the Repository

```bash
git clone https://github.com/kanz154/Travel-Agency-Application.git
cd Travel-Agency-Application
```

#### Step 2: Start XAMPP and Set Up Database

1. **Start XAMPP**:
   - Open XAMPP Control Panel
   - Click **"Start"** for Apache (optional for web)
   - Click **"Start"** for MySQL

2. **Open phpMyAdmin**:
   - Go to: `http://localhost/phpmyadmin/`
   - Or click "Admin" button next to MySQL in XAMPP

3. **Create the Database**:
   - Click **"New"** on the left sidebar
   - Database name: `travel_agency`
   - Collation: `utf8mb4_general_ci`
   - Click **"Create"**

4. **Import the SQL File**:
   - Select the `travel_agency` database (click on it)
   - Click the **"Import"** tab
   - Click **"Choose File"**
   - Select: `sql/travel_agency__1_.sql` from your project folder
   - Click **"Import"** button
   - Wait for success message 

#### Step 3: Configure Database Connection

1. Open the project in NetBeans
2. Find the database connection file (usually in `src/DatabaseLayer/`)
3. Update the connection details:
DB_HOST = localhost
DB_PORT = 3306
DB_NAME = travel_agency
DB_USER = root
DB_PASSWORD = (leave empty for XAMPP default)
#### Step 4: Build and Run

**Using NetBeans:**
- Right-click the project → Select `Run`

**Using Command Line:**
```bash
ant build
ant run
```

## Project Structure
Travel-Agency-Application/
├── src/
│ ├── ui/ # GUI screens and forms
│ ├── Core_Services/ # Business logic
│ ├── DatabaseLayer/ # Database operations
│ └── travelagencyapplication/
├── sql/
│ └── travel_agency__1_.sql # Database dump
├── build/ # Compiled classes
├── nbproject/ # NetBeans configuration
├── build.xml # Apache Ant build file
└── manifest.mf # JAR manifest
## Usage

1. **Start XAMPP MySQL** - Open XAMPP and click Start for MySQL
2. **Launch the application** - Run the main class in NetBeans
3. **Register/Login** - Create an account or sign in
4. **Browse Services** - Search flights, hotels, buses, packages
5. **Make Bookings** - Add items and proceed to checkout
6. **Manage Bookings** - View all bookings and reservations
7. **Apply for Visa** - Track visa applications

## Testing

### Sample Login Credentials
Email: ali@test.com
Password: password123

or

Email: abc@gmail.com
Password: 123


### Sample Data

The database comes pre-populated with:
- 28 user accounts
- 22 bookings
- 6 flights
- 7 hotels
- 3 bus routes
- Travel packages and visa applications

## Troubleshooting
### MySQL Connection Error in XAMPP
- Make sure XAMPP MySQL is running (green "Running" status)
- Go to phpMyAdmin: `http://localhost/phpmyadmin/`
- Verify the `travel_agency` database exists

### Database Not Connecting from Java
- Check that MySQL port is 3306 (default for XAMPP)
- Verify username is `root` and password is empty (XAMPP default)
- Make sure `travel_agency` database is created and has tables

### Application Won't Start
- Verify Java is installed: `java -version`
- Clean the project: `ant clean`
- Rebuild: `ant build`
- Check NetBeans console for error messages

## Contributing

1. Create a new branch for your feature
2. Make your changes
3. Test thoroughly with XAMPP running
4. Push to your fork
5. Create a pull request

## License

This project is open source and available for educational purposes.

## Future Enhancements

- Mobile app version
- Payment gateway integration
- Real-time flight availability
- User reviews and ratings
- Loyalty rewards program
- Multi-language support
- Email notifications for bookings
- integration of AI (like chatbots)

Happy Travels! 

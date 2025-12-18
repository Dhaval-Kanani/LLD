package org.modules.SystemDesign.BookMyShow2;

import org.modules.SystemDesign.BookMyShow2.factory.MovieFactory;
import org.modules.SystemDesign.BookMyShow2.factory.SeatFactory;
import org.modules.SystemDesign.BookMyShow2.seats.Seat;

import java.util.*;

public class BookMyShow {
    private MovieController movieController;

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();
        bookMyShow.runDemo();
    }

    public void initialize(){
        List<Movie> movieList = getMovies();
        List<Seat> seatList = getSeats();
        Map<City, List<Theater>> cityToTheaterMap = getTheaters();

        Map<City, Map<Movie, List<Show>>> cityToShowMap = getShows(movieList, seatList, cityToTheaterMap);
        movieController = MovieController.getInstance( movieList, cityToShowMap);
    }

    public void runDemo(){
        System.out.println("=== BookMyShow Demo ===\n");
        BookingService bookingService = BookingService.getInstance();

        // Step 1: Create user
        User user = new User("USER001");
        System.out.println("User created: " + user.getUserId());

        // Step 2: Get all available movies
        System.out.println("\nAvailable Movies:");
        List<Movie> movies = movieController.getMovies();
        for(Movie movie : movies) {
            System.out.println("- " + movie.getMovieName() + " (" + movie.getDuration() + " mins)");
        }

        // Step 3: Select city and movie
        City selectedCity = City.BANGALORE;
        Movie selectedMovie = movies.get(0);
        System.out.println("\nUser selected: " + selectedMovie.getMovieName() + " in " + selectedCity);

        // Step 4: Find available show
        Show show = movieController.findShow(selectedCity, selectedMovie);
        if(show == null) {
            System.out.println("No shows available!");
            return;
        }

        System.out.println("\nShow Details:");
        System.out.println("- Theater: " + show.getScreen().getTheater().getTheaterId());
        System.out.println("- Screen: " + show.getScreen().getScreenId());
        System.out.println("- Start Time: " + new Date(show.getStartTime()));

        // Step 5: Display available seats
        System.out.println("\nAvailable Seats:");
        List<Seat> availableSeats = bookingService.getAvailableSeats(show);
        System.out.println("Total available seats: " + availableSeats.size());
        if(!availableSeats.isEmpty()) {
            System.out.println("Silver seats (1-30): ₹" + availableSeats.get(0).getPrice());
            System.out.println("Gold seats (31-70): ₹" + availableSeats.get(40).getPrice());
        }

        // Step 6: User selects seats
        List<Seat> selectedSeats = Arrays.asList(
                availableSeats.get(14),
                availableSeats.get(15)
        );
        System.out.println("\nUser selected seats:");
        for(Seat seat : selectedSeats) {
            System.out.println("- Seat " + seat.getSeatNo() + " (" + seat.getSeatType() + ") - ₹" + seat.getPrice());
        }

        // Step 7: Book seats with locking mechanism
        Ticket ticket = bookingService.bookSeats(user, show, selectedSeats);

        if(ticket == null) {
            System.out.println("\nBooking failed! Seats already taken.");
            return;
        }

        System.out.println("\n=== Booking Confirmed ===");
        System.out.println("Ticket ID: " + ticket.getShow().getMovie().getMovieName());
        System.out.println("User: " + user.getUserId());
        System.out.println("Seats: " + ticket.getBookedSeats().stream().map(Seat::getSeatNo).toList());
        System.out.println("Total: ₹" + ticket.getPrice());
        System.out.println("\nRemaining available seats: " + bookingService.getAvailableSeats(show).size());
        System.out.println("Booking successful! Enjoy your movie!");
    }


    private List<Movie> getMovies(){
        List<Movie> movieList = new ArrayList<>();
        int duration = 180;
        for(MovieEnum movieEnum: MovieEnum.values()){
            movieList.add(MovieFactory.getMovie(movieEnum, duration));
        }
        return movieList;
    }

    private List<Seat> getSeats(){
        List<Seat> seatList = new ArrayList<>();
        int silverPrice = 200;
        int goldSeatPrice = 300;
        for(int i=1; i<=30; i++){
            seatList.add(SeatFactory.getSeat(i, silverPrice));
        }
        for(int i=31; i<=70; i++){
            seatList.add(SeatFactory.getSeat(i, goldSeatPrice));
        }
        return seatList;
    }

    private Map<City, List<Theater>> getTheaters(){
        Map<City, List<Theater>> cityToTheaterMap = new HashMap<>();
        for(City city: City.values()){
            cityToTheaterMap.put(city, List.of(new Theater(UUID.randomUUID().toString(), city)));
        }
        return cityToTheaterMap;
    }

    private Map<City, Map<Movie, List<Show>>> getShows(List<Movie> movieList, List<Seat> seatList, Map<City, List<Theater>> cityToTheaterMap){
        Map<City, Map<Movie, List<Show>>> cityToShowMap = new HashMap<>();

        for(City city: City.values()){
            List<Theater> theaterList = cityToTheaterMap.get(city);
            Map<Movie, List<Show>> movieToShowMap = cityToShowMap.getOrDefault(city, new HashMap<>());
            for(Theater theater: theaterList){
                Screen screen = new Screen(UUID.randomUUID().toString(), theater);
                for(int i=0; i<movieList.size(); i++){
                    List<Show> showList = movieToShowMap.getOrDefault(movieList.get(i), new ArrayList<>());
                    showList.add(new Show(UUID.randomUUID().toString(), movieList.get(i), seatList, theater, screen, System.currentTimeMillis() + 1000*60*60));
                    movieToShowMap.put(movieList.get(i), showList);
                }
            }
            cityToShowMap.put(city, movieToShowMap);
        }

        return cityToShowMap;
    }
}

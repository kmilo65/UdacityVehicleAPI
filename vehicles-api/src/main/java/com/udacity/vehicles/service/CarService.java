package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.Price;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    //private final WebClient mapsWebClient;
   // private final WebClient pricingWebClient;
    private PriceClient priceClient;
    private MapsClient mapsClient;

    @Value("${maps.endpoint}")
    private String mapsPriceEndpoint;
   /** public CarService(CarRepository repository, @Qualifier("maps") WebClient mapsWebClient,
                      @Qualifier("pricing") WebClient pricingWebClient) {**/
        /**
         * TODO: Add the Maps and Pricing Web Clients you create
         *   in `VehiclesApiApplication` as arguments and set them here.
         */
     /**   this.repository = repository;
        this.mapsWebClient = mapsWebClient;        // Assign the Maps WebClient
        this.pricingWebClient = pricingWebClient;  // Assign the Pricing WebClient

    }**/

     private CarService(CarRepository repository, MapsClient mapsClient,PriceClient priceClient){
             this.repository=repository;
             this.priceClient=priceClient;
             this.mapsClient=mapsClient;
     }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {

        List<Car> cars = repository.findAll();

        for(Car car: cars){
             car.setPrice(priceClient.getPrice(car.getId()));
             car.setLocation(mapsClient.getAddress(car.getLocation()));
        }
        return cars;
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   Remove the below code as part of your implementation.
         */
        Car car = new Car();
       // Car car=repository.findById(id).orElseThrow(CarNotFoundException::new);


        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         * TODO: Set the price of the car
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         */
       // car.setPrice(priceClient.getPrice(id));

        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */


        //Location address = mapsClient.getAddress(car.getLocation());

        //car.setLocation(address);
        //Car car;
        Optional<Car> optionalCar = repository.findById(id);
        if(optionalCar.isPresent()){
            car=optionalCar.get();
        }else{
            throw new CarNotFoundException();
        }
        car.setPrice(priceClient.getPrice(id));

        car.setLocation(mapsClient.getAddress(car.getLocation()));
        return car;

    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
  /**  public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        carToBeUpdated.setPrice(priceClient.getPrice(car.getId()));
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        } else {
            return repository.save(car);
        }

    }**/
  public Car save(Car car) {
      if (car.getId() != null) {
          return repository.findById(car.getId())
                  .map(carToBeUpdated -> {
                      carToBeUpdated.setDetails(car.getDetails());
                      carToBeUpdated.setLocation(car.getLocation());
                      carToBeUpdated.setCondition(car.getCondition());
                      return repository.save(carToBeUpdated);
                  }).orElseThrow(CarNotFoundException::new);
      }

      return repository.save(car);
  }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         */
        Car car=repository.findById(id).orElseThrow(CarNotFoundException::new);

        /**
         * TODO: Delete the car from the repository.
         */
        repository.delete(car);
    }

    /**

    public Mono<Location> getAddress(Double lat, Double lon) {
        return mapsWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .build())
                .retrieve()
                .bodyToMono(Location.class);
    }

    public String getPrice(Long carId) {

        return pricingWebClient.get()
                .uri(uriBuilder ->uriBuilder.path(mapsPriceEndpoint+"/services/price?vehicleId=" + carId).build())
                .retrieve()
                .bodyToMono(Price.class)
                .map(price -> price.toString()).block();
    }
**/

}

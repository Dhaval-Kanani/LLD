package org.modules.SystemDesign.VehicleRentalSystemv2;

import org.modules.SystemDesign.VehicleRentalSystemv2.NotificationService.NotificationService;
import org.modules.SystemDesign.VehicleRentalSystemv2.paymentService.PaymentResult;
import org.modules.SystemDesign.VehicleRentalSystemv2.paymentService.PaymentService;
import org.modules.SystemDesign.VehicleRentalSystemv2.paymentService.PaymentStatus;
import org.modules.SystemDesign.VehicleRentalSystemv2.strategy.FeeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Store {
    private String storeId;
    private City city;
    private VehicleManager vehicleManager;
    private Map<String, Reservation> activeReservations;
    private FeeStrategy feeStrategy;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public Store(String storeId, City city, FeeStrategy feeStrategy) {
        this.storeId = storeId;
        this.city = city;
        this.vehicleManager = new VehicleManager(UUID.randomUUID().toString());
        this.activeReservations = new HashMap<>();
        this.feeStrategy = feeStrategy;
        this.paymentService = new PaymentService();
        this.notificationService = new NotificationService();
    }

    public Vehicle getAvailableVehicle(VehicleType vehicleType){
        return vehicleManager.findAvailableVehicle(vehicleType);
    }

    public Reservation reserveVehicle(Vehicle vehicle, double amountPaid, User user){
        vehicleManager.reserveVehicle(vehicle);
        Reservation reservation  = new Reservation(UUID.randomUUID().toString(), vehicle, amountPaid);
        activeReservations.put(vehicle.getVehicleId(), reservation);

        notificationService.notifyReservationConfirmed(user.getUserId(), reservation.getReservationId());
        System.out.println("Successfully reserved the vehicle.");
        return reservation;
    }

    public Boolean completeBooking(Vehicle vehicle, User user){
        if(!activeReservations.containsKey(vehicle.getVehicleId())) {
            System.out.println("No active reservation present");
            return false;
        }
        vehicleManager.unReserveVehicle(vehicle);
        Reservation reservation = activeReservations.get(vehicle.getVehicleId());
        reservation.setBookingEndTime(System.currentTimeMillis() + 1000*60*60*3);
        double charge = feeStrategy.calculateCharge(reservation);

        PaymentResult paymentResult = paymentService.processPayment(reservation, charge);

        System.out.println(paymentResult.getMessage());

        if(paymentResult.getStatus().equals(PaymentStatus.INSUFFICIENT)){
            notificationService.notifyPaymentDue(user.getUserId(), reservation.getReservationId(), paymentResult.getAmount());
        } else if(paymentResult.getStatus().equals(PaymentStatus.REFUND_DUE)){
            notificationService.notifyRefundProcessed(user.getUserId(), reservation.getReservationId(), paymentResult.getAmount());
        }

        reservation.setBooking(BookingStatus.COMPLETED);
        notificationService.notifyBookingCompleted(user.getUserId(), reservation.getReservationId());
        activeReservations.remove(vehicle.getVehicleId());
        System.out.println("Successfully returned the vehicle.");
        return true;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleManager.addVehicleMap(vehicle);
    }
}

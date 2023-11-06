package dev.oblac.voidkt.hotel

/**

Grouping the functions.

*/

// ROOM TYPES, AVAILABLE ROOMS
val findAvailableRoomTypes: (DateRange) -> List<RoomType> = TODO()
// ROOMS
val reserveUnoccupiedRoom: (RoomType) -> Room

// BOOKING
val createReservation: (RoomType, DateRange) -> Reservation
val storeNewBookingForReservation: (Customer, Reservation, Amount) -> Booking
val createBooking: (Customer, Reservation) -> Booking
val findBooking: (Customer) -> Booking


// PRICING
val findDayPricesForRoomType: (RoomType, DateRange) -> List<DayPrice>
// PAYMENT
val authorizePayment: (Customer, Amount) -> Boolean
val findCancellationFee: () -> Amount

// CHECKIN, ACTIVE BOOKING
val pickRoom: (Booking) -> ActiveBooking
val storeActiveBooking: (Booking, Room) -> ActiveBooking
val findAllActiveBookings: () -> List<ActiveBooking>
val deleteActiveBooking: (ActiveBooking) -> Unit

// CUSTOMER
val storeCustomer: (UserDetails) -> Customer
val findCustomer: (UserDetails) -> Customer

// GENERAL SERVICES
val sendEmail: (Customer, Booking) -> Unit
val triggerEachDay: () -> Unit
val printTheBill: (ActiveBooking) -> Unit



// (UI)
val search: (DateRange) -> List<BookingChoice>
val book: (UserDetails, RoomType, DateRange) -> Booking
val authorizeBooking: (Booking) -> Unit
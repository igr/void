package dev.oblac.voidkt.hotel

/**

Grouping the functions.

*/

// ROOM TYPES, AVAILABLE ROOMS, ROOMS
val findAvailableRoomTypes: (DateRange) -> List<RoomType> = TODO()
val reserveUnoccupiedRoom: (RoomType) -> Room = TODO()

// RESERVATION (ROOM PRICES) - _maybe_ part of the same service as above
val findDayPricesForRoomType: (RoomType, DateRange) -> List<DayPrice> = TODO()
val findReservationPrices: (Reservation) -> List<DayPrice> = TODO()
val createReservation: (RoomType, DateRange) -> Reservation = TODO()

// BOOKING
val createBooking: (Customer, Reservation) -> Booking = TODO()
val findBooking: (Customer) -> Booking = TODO()

// PAYMENT
val authorizePayment: (Customer, Amount) -> Boolean = TODO()
val findCancellationFee: () -> Amount = TODO()

// CHECKIN, ACTIVE BOOKING
val pickRoom: (Booking) -> ActiveBooking = TODO()
val storeActiveBooking: (Booking, Room) -> ActiveBooking = TODO()
val findAllActiveBookings: () -> List<ActiveBooking> = TODO()
val deleteActiveBooking: (ActiveBooking) -> Unit = TODO()

// CUSTOMER
val storeCustomer: (UserDetails) -> Customer = TODO()
val findCustomer: (UserDetails) -> Customer = TODO()

// GENERAL SERVICES
val sendEmail: (Customer, Booking) -> Unit = TODO()
val triggerEachDay: () -> Unit = TODO()
val printTheBill: (ActiveBooking) -> Unit = TODO()



// (UI)
val search: (DateRange) -> List<BookingChoice> = TODO()
val book: (UserDetails, RoomType, DateRange) -> Booking = TODO()
val authorizeBooking: (Booking) -> Unit = TODO()
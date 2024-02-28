flatpickr('#reservationDate', {
    locale: "ja",
    altInput: true,
    minDate: 'today',
    dateFormat: "Y-m-d",
});

flatpickr('#reservationTime', {
  enableTime: true,
  noCalendar: true,
  dateFormat: 'H:i',
  time_24hr: false,
  minTime: "10:00",
  maxTime: "23:00"
});
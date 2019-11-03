# building-info

Rejestr produktu, sprintu, itd. jest [tutaj](https://docs.google.com/spreadsheets/d/1aDTQCKbAcoE8keDuq2KcZH5aJ1E5YINCDQJ0HzN18_k/edit?usp=sharing)

Struktura danych:
• Lokacja to budynek, poziom, lub pomieszczenie
• Budynek może składać się z poziomów a te z pomieszczeń
• Każda lokalizacja jest charakteryzowana przez:
    o id – unikalny identyfikator
   o name – opcjonalna nazwa lokalizacji
• Pomieszczenie dodatkowo jest charakteryzowane przez:
   o area = powierzchnia w m^2
   o cube = kubatura pomieszczenia w m^3
   o heating = poziom zużycia energii ogrzewania (float)
   o light – łączna moc oświetlenia

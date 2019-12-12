# building-info

![Build status](https://travis-ci.org/Dominos1234/building-info.svg?branch=develop)
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

## Request JSON Structure

```json
{
    "buildings": [
        {
            "id": 11,
            "floors": [
                {
                    "id": 21,
					"rooms": [
						{
							"id": 31,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 22,
					"rooms": [
						{
							"id": 32,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        },

        {
            "id": 12,
            "floors": [
                {
                    "id": 23,
					"rooms": [
						{
							"id": 33,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 24,
					"rooms": [
						{
							"id": 34,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        }	
        
]
}
```
# building-info

![Build status](https://travis-ci.org/Dominos1234/building-info.svg?branch=develop)

Rejestr produktu, sprintu, itd. jest [tutaj](https://docs.google.com/spreadsheets/d/1aDTQCKbAcoE8keDuq2KcZH5aJ1E5YINCDQJ0HzN18_k/edit?usp=sharing)
Punktowanie sprintu #2 [formatka](https://docs.google.com/spreadsheets/d/1SjnSpxvapOiACIVtu05h164Pw2II1XiufaiFosTgg2w/edit?usp=sharing)

Struktura danych:
* Lokacja to budynek, poziom, lub pomieszczenie
* Budynek może składać się z poziomów a te z pomieszczeń
* Każda lokalizacja jest charakteryzowana przez:
   * id – unikalny identyfikator
   * name – opcjonalna nazwa lokalizacji
* Pomieszczenie dodatkowo jest charakteryzowane przez:
   * area - powierzchnia w m^2
   * cube - kubatura pomieszczenia w m^3
   * heating - poziom zużycia energii ogrzewania (float)
   * light – łączna moc oświetlenia

## Request JSON Structure

```json
{
  "buildings": [
    {
      "id": 1,
      "floors": [
        {
          "id": 2,
           "rooms": [
              {
                "id": 3,
                "area": 10.0,
                "volume": 30.0,
                "heating": 50.0,
                "light": 70.0
              }
           ]
        },
        {
          "id": 4,
           "rooms": [
              {
                "id": 5,
                "area": 20.0,
                "volume": 40.0,
                "heating": 60.0,
                "light": 80.0
              }
           ]
        }
      ]
    },
    {
      "id": 6,
      "floors": [
        {
          "id": 7,
           "rooms": [
              {
                "id": 8,
                "area": 10.0,
                "volume": 30.0,
                "heating": 50.0,
                "light": 70.0
              }
           ]
        },
        {
          "id": 9,
           "rooms": [
              {
                "id": 10,
                "area": 20.0,
                "volume": 40.0,
                "heating": 60.0,
                "light": 80.0
              }
           ]
        }
      ]
    }
  ]
}
```

# Building Info

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
							"x": 1.0,
							"y": 2.0,
							"height": 3.0,
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
							"x": 6.0,
							"y": 7.0,
							"height": 8.0,
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
							"x": 1.0,
							"y": 2.0,
							"height": 3.0,
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
							"x": 6.0,
							"y": 7.0,
							"height": 8.0,
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
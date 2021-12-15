# Mash
MusicMasher service 

# Description
Aggregating(mashing) data from 4 REST APIs and starting a new REST API. The APIs used are:
- MusicBrainz (http://musicbrainz.org/ws/2)
- Cover Art Archive (http://coverartarchive.org/)
- Wikidata (https://www.wikidata.org/w/api.php)
- Wikipedia (https://en.wikipedia.org/w/api.php)

# Use instructions
Make use of Maven
```
$ mvn install
$ mvn spring-boot:run
$ curl http://localhost:8080/5b11f4ce-a62d-471e-81fc-a69a8278c7da
...
```
One can also package the application as a java jar and run locally
```
$ mvn package
$ java -jar target\artist-api-0.0.1-SNAPSHOT.jar

``` 
Or make use of the service accessing https://mash.uppeimolnen.se

## mbid:s
Access musicbrainz.org to fetch mbids for your favorite artist

| Artist            | mbid                                  |
|-------------------|---------------------------------------|
| Nirvana           | 5b11f4ce-a62d-471e-81fc-a69a8278c7da  |
| Robyn             | 5a8e07d5-d932-4484-a7f7-e700793a9c94  |

# Example

Request:
`localhost:8080/f27ec8db-af05-4f36-916e-3d57f91ecf5e`

Response : 
```
{
  "mbid": "5b11f4ce-a62d-471e-81fc-a69a8278c7da",
  "description": "<p><b>Nirvana</b> was an American rock band....</p>",
  "albums": [
    {
      "title": "Bleach",
      "id": "f1afec0b-26dd-3db5-9aa1-c91229a74a24",
      "image": "http://coverartarchive.org/release/7d166a44-cfb5-4b08-aacb-6863bbe677d6/1247101964.jpg"
    },
    {
      "title": "Nevermind",
      "id": "1b022e01-4da6-387b-8658-8678046e4cef",
      "image": "http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605.jpg"
    }
  ]
}
```

# Known issues
The CoverArtArchive API is slow and the error handling can be improved


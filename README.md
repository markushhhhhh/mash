# Mash
MusicMasher REST API service 

## Description
The application is accepting GET requests from clients and returning aggregated(mashed) information about an artist. The client needs to provide an 'mbid' in the URL which acts as an identifier for the artists when retieving information.
The 4 APIs used for fetching data about the specific artist are:
- MusicBrainz (http://musicbrainz.org/ws/2)
- Cover Art Archive (http://coverartarchive.org/)
- Wikidata (https://www.wikidata.org/w/api.php)
- Wikipedia (https://en.wikipedia.org/w/api.php)

## Usage instructions
Runs on [Java 11](https://www.oracle.com/java/technologies/downloads/#java11).

Using https://maven.apache.org/, execute following comands in the project root folder:
```
$ mvn install
$ mvn spring-boot:run
```
One can also package the application as a java jar and run locally by executing the following commands in the projects root folder:
```
$ mvn package
$ java -jar target\artist-api-0.0.1-SNAPSHOT.jar

``` 
Or make use of the service accessing https://mash.uppeimolnen.se

## Mbid:s
Access musicbrainz.org to fetch mbids for your favorite artist

* Nirvana - 5b11f4ce-a62d-471e-81fc-a69a8278c7da
* Robyn   - 5a8e07d5-d932-4484-a7f7-e700793a9c94
* Meshuggah - cf8b3b8c-118e-4136-8d1d-c37091173413
* Tommy Körberg - 3d142b46-bf4b-4f71-97ee-1c1ae4dbf2a0

## Example

Request:
`localhost:8080/5b11f4ce-a62d-471e-81fc-a69a8278c7da`
or
`https://mash.uppeimolnen.se/5b11f4ce-a62d-471e-81fc-a69a8278c7da`

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

## Known issues
* The CoverArtArchive API is quite slow which in turn slows down the response time for the Mash service REST API and in some cases the service fails to fetch the URL for the album picture.


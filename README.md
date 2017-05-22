  # wort
Rearranging and speaking your words since May 2017.

# TODO
## Front end
Web page with text box and submit that fetches audio and let's people download/play.

### Implementation
HTML page from S3

## Backend for front end
Forms audio snippets for a user generated sentence.

### Implementation
Clojure function in AWS Lambda talks to S3.

## Ingestion pipeline
Given a URL for a Youtube video create audio snippets of words and save in the database.

### Implemenation
Clojure function in AWS Lambda talks to S3. Maybe not accessible as a web endpoint.

## Data store
S3
person -> word -> audio file

## Deployment
1. code (Github)
2. build: compile, test it, package it
3. stage build artifact in testing
4. run some integration tests
5. if good, push to production
6. test again (roll back on failure)

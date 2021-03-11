## Requirements
- Java and JDK >= 11
- docker

## Getting Started

### Build docker image

```./gradlew bootBuildImage --imageName=tb/spring/gender-detection```

### Run docker container

```docker run -p 8082:8082 tb/spring/gender-detection```

### Available endpoints

#### Guess gender with given single tokens
```GET /guess/single/Jan%20Anna%20Maria```

#### Guess gender with given multiple tokens
```GET /guess/multi/Jan%20Anna%20Maria```

#### Get all available name tokens
```GET /name/get-all```







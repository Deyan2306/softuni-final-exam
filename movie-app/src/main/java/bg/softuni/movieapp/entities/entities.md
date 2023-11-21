### 1. User
- Attributes:
    - `id`: Unique identifier
    - `username`: User's username
    - `password`: User's password (encrypted)
    - `roles`: User roles (user, administrator)
    - `avatarUrl`: URL for user's avatar
    - `real_name`: The real name of the user
    - `bio`: Bio for the user profile
    - `location`: location of the user
    - `watched_movies`: List of movies, that have been watched by the user
    - `total_watched_movies`: Number, showing the total watched movies
    - `watched_tv_series`: List of watched TV series, each containing the current episode
    - `total_watched_tv_series`: Number, showing the total watched TV Series
    - `discord_username`: Username in discord

### 2. Movie
- Attributes:
    - `id`: Unique identifier
    - `title`: Movie title
    - `genre`: Genre of the movie
    - `releaseDate`: Release date of the movie
    - `director`: The director of the movie
    - `length`: The length of the movie
    - `cast`: List of Actors
    - `comments`: Comments for the movie forum
    - `storyline`: The storyline of the movie
    - `quotes`: List containing the best quotes of the movie

### 3. TV Series
### 4. Actor

### 3. Review
- Attributes:
    - `id`: Unique identifier
    - `text`: Review text
    - `rating`: Rating given by the user
    - `timestamp`: Timestamp of the review
    - Relationship with `User` and `Movie`

### 4. Comment
- Attributes:
    - `id`: Unique identifier
    - `text`: Comment text
    - `timestamp`: Timestamp of the comment
    - Relationship with `User` and `Review`

### 6. Genre 
- Attributes:
    - `id`: Unique identifier
    - `name`: Genre name
    - `description`: Description of the genre
    - Relationship with `Movie`

### 7. Director
- Attributes:
    - `id`: Unique identifier
    - `name`: Director's name
    - `bio`: Biography or information about the director
    - Relationship with `Movie`

### 8. Rating
- Attributes:
    - `id`: Unique identifier
    - `criticRating`: Rating given by critics
    - `communityRating`: Rating given by the community/users
    - Relationship with `Movie`
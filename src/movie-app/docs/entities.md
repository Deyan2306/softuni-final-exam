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
    - `title_picture`: The title picture of the movie page 
    - `genre`: Genre of the movie
    - `pg_rating`: Rating of the movie (Enum)
    - `short_description`: Short description of the movie
    - `ratings`: List of user ratings
    - `watched_by`: Count of people, who watched the movie
    - `trailer`: Url for youtube trailer
    - `releaseDate`: Release date of the movie
    - `director`: The director of the movie
    - `length`: The length of the movie
    - `cast`: List of Actors
    - `storyline`: The storyline of the movie
    - `quotes`: List containing the best quotes of the movie
    - `more_like_this`: List of movies with similar genres
    - `studio`: Studio, which made the movie

### 3. TV Series
- Attributes:
    - `id`: Unique identifier
    - `title`: Title of whole series
    - `title_picture`: The title picture of the tv series page 
    - `episodes`: List of episodes related to the tv series
    - `genre`: The genre of the series
    - `pg_rating`: The PG rating of the series
    - `short_description`: Short description of the TV series
    - `ratings`: List of user ratings
    - `completed_by`: List of people, who completed the TV series
    - `trailer`: YouTube link for the movie trailer
    - `release_date`: Release date of the tv series
    - `end_date`: End date of the tv series
    - `director`: The director of the movie
    - `length`: Length of a single episode
    - `cast`: List of actors
    - `storyline`: The storyline of the tv series
    - `quotes`: Cool quotes from the tv series
    - `more_like_this`: List of movies and tv series with similar genres
    - `studio`: The studio which made the movie

### 4. TV Series episode
- Attributes:
    - `id`: Unique identifier
    - `series`: Relation to the tv series
    - `episode_number`: Episode
    - `season_number`: Season
    - `summary`: Quick summary of the episode
    - `release_date`: Release date of the episode
    - `title_image`: The title image of the tv series
    - `ratings`: List of user ratings
    - `comments`: List of comments related to the movie

### 5. Actor
- Attributes:
    - `id`: Unique identifier
    - `credits`: What he is doing at work
    - `first_name`: First name
    - `last_name`: Last name
    - `born_date`: The date of birth
    - `death_date`: The date of death
    - `personal_photo`: Photo of the actor
    - `personal_video`: Video showcasing the actor
    - `biography`: The biography of the actor
    - `played_at: List of movies, that the actors played at
    - `height`: Height in cm
    - `alternative_name`: Alternative names of the author
    - `comments`: List of comments related to the actor

### 6. Review
- Attributes:
    - `id`: Unique identifier
    - `text`: Review text
    - `rating`: Rating given by the user
    - `timestamp`: Timestamp of the review
    - Relationship with `User` and `Movie` / `TV series`

### 7. Comment
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
    - Relationship with `Movie` or `TV series`

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
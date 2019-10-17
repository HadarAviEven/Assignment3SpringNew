package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyWebServices {
	
	static List<Movie> movies = new ArrayList<Movie>();
	
    static String actorsInMovie1 = "Kristen bell, Idina Menzel, Jonathan Groff, Josh Gad, Santino Fontana";
    static String actorsInMovie2 = "Andrew Jacobs, Carlos Pratts, Richard Cabral, Kimberly Ables Jindra, Eddie J. Fernandez";
    static String actorsInMovie3 = "Ian McKellen, Martin Freeman, Richard Armitage, Benedict Cumberbatch, Evangeline Lilly";
    static String actorsInMovie4 = "Leonardo DiCaprio, Jonah Hill, Margot Robbie, Matthew McConaughey, Kyle Chandler";
    static String actorsInMovie5 = "Jennifer Lawrence, Bradley Cooper, Christian Bale, Amy Adams, Jeremy Renner";
    static String actorsInMovie6 = "Will Ferrell, Steve Carell, Paul Rudd, David Koechner, Christina Applegate";
    
    static String urlImageForMovie1 = "https://i2.wp.com/media.comicbook.com/wp-content/uploads/2013/09/Frozen-movie-poster.jpg";
    static String urlImageForMovie2 = "https://nieuwdezeweek.nl//media/cache/df/49/df494c0f9fb6a5aeadc25c4e5b992ab2.jpg";
    static String urlImageForMovie3 = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2013/06/10/16/Hobbit-poster.jpg";
    static String urlImageForMovie4 = "https://lowrihafcooke.files.wordpress.com/2014/01/the-wolf-of-wall-street.jpg";
    static String urlImageForMovie5 = "https://image.tmdb.org/t/p/original/5goB4tSepYuH8f2Ap5voTAnIjry.jpg";
    static String urlImageForMovie6 = "https://image.tmdb.org/t/p/original/qN0QBalixMoAwePsUQX9U9C9r9v.jpg";
    
	static {
		movies.add(new Movie("Frozen",
	            89,
	            actorsInMovie1, urlImageForMovie1));
		movies.add(new Movie("Paranormal Activity: The Marked Ones",
	            39,
	            actorsInMovie2, urlImageForMovie2));
		movies.add(new Movie("The Hobbit: The Desolation of Smaug",
	            75,
	            actorsInMovie3, urlImageForMovie3));
		movies.add(new Movie("The Wolf of Wall Street",
	            76,
	            actorsInMovie4, urlImageForMovie4));
		movies.add(new Movie("American Hustle",
	            93,
	            actorsInMovie5, urlImageForMovie5));
		movies.add(new Movie("Anchroman 2: The Legend Continues",
	            75,
	            actorsInMovie6, urlImageForMovie6));
	}

	@RequestMapping( value = "/movie", method = RequestMethod.GET)
	public List<Movie> doGetMovies()
	{
		return movies;
	}
	
	@RequestMapping( value = "/movie/{name}", method = RequestMethod.GET)
	public Movie doGetMovieByName(@PathVariable("name") String name)
	{
		for(Movie m : movies)
		{
			if (m.getName().equals(name))
			{
				return m;
			}
		}
		return null;
	}	
	
	
	@RequestMapping( value = "/movie", method = RequestMethod.POST)
	public String doPostMovies(@RequestBody Movie m)
	{
		movies.add(m);
		return "Success!";
	}
	
	@RequestMapping( value = "/movie/{name}", method = RequestMethod.PUT)
	public String doPutMovies(@RequestBody Movie sent, @PathVariable("name") String name)
	{
		for(Movie m : movies)
		{
			if (m.getName().equals(name))
			{
				m.setScore( sent.getScore());
				m.setActors( sent.getActors());
				return "UPDATED!";
			}
		}
		return "NOT FOUND!";
	}
	
	@RequestMapping( value = "/movie/{name}", method = RequestMethod.DELETE)
	public String doDeleteMovies(@PathVariable("name") String name)
	{
		Movie del = null;
		for(Movie m : movies)
		{
			if (m.getName().equals(name))
			{
				del = m;
			}
		}
		if (del != null)
		{
			movies.remove(del);
			return "Movie deleted!";
		}
		return "Movie not found!";
	}

}

package in.lowes.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.lowes.urlshortner.model.Url;

//@RepositoryRestResource(collectionResourceRel = "shortner", path = "shortner")
@Repository
public interface UrlShortenerRepository extends JpaRepository<Url, Long> {

	@Query("Select count(shortUrl) > 1 From Url where shortUrl = ?1")
	boolean isShortUrlAlreadyPresent(String shortUrl);

	Url findByShortUrl(String shortUrl);
	
	Url findByOriginalUrl(String originalUrl);
}

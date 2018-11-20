package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.FreelancerDriverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverJpaRepository extends CrudRepository<FreelancerDriverEntity, Long> {


    @Query("SELECT COUNT(x) from FreelancerDriverEntity x  WHERE  ( x.rating > 0 and x.rating <= 1) ")
    public Long countFreelancerDriverRatingOneStar();

    @Query("SELECT COUNT(x) from FreelancerDriverEntity x  WHERE  ( x.rating > 1 and x.rating <= 2) ")
    public Long countFreelancerDriverRatingTwoStars();

    @Query("SELECT COUNT(x) from FreelancerDriverEntity x  WHERE  ( x.rating > 2 and x.rating <= 3) ")
    public Long countFreelancerDriverRatingThreeStars();

    @Query("SELECT COUNT(x) from FreelancerDriverEntity x  WHERE  ( x.rating > 3 and x.rating <= 4) ")
    public Long countFreelancerDriverRatingFourStars();

    @Query("SELECT COUNT(x) from FreelancerDriverEntity x  WHERE  ( x.rating > 4 and x.rating <= 5) ")
    public Long countFreelancerDriverRatingFiveStars();

    Long countAllByStatus(String status);


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventim.teamessen.repos;

import eventim.teamessen.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface RateRepository extends JpaRepository<Rate, Long>{
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventim.teamessen.repos;

import eventim.teamessen.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface OptionRepository extends JpaRepository<Option, Long>{
    
}
